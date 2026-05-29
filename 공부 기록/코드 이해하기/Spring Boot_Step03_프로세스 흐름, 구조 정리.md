아래 프로젝트는 한마디로 **로그인한 사용자만 접근 가능한 자유게시판 + 댓글 기능을 Spring Boot MVC, JSP, JPA로 구현한 예제**입니다.

큰 흐름은 이렇습니다.

```text
사용자 브라우저
-> Controller
-> Service
-> Repository
-> JPA Entity
-> MySQL DB
-> 다시 View(JSP)로 응답
```

**1. 프로젝트 전체 구조**
주요 위치는 다음과 같습니다.

- `pom.xml`: Spring Boot, JPA, MySQL, JSP, JSTL, QueryDSL, ModelMapper 의존성 설정
- `application.properties`: 서버 포트, JSP 경로, DB 연결, JPA 설정
- `controller`: 요청 URL을 받는 계층
- `service`: 실제 비즈니스 로직
- `repository`: DB 접근 계층
- `domain`: JPA Entity
- `dto`: 화면 전달용 객체
- `views`: JSP 화면

서버는 `server.port=9000` 이므로 실행 후 기본 접속 주소는 보통 `http://localhost:9000` 입니다.


---
**2. 핵심 기능**
이 프로젝트가 제공하는 기능은 크게 네 가지입니다.

1. 로그인 / 로그아웃
2. 게시글 목록 조회
3. 게시글 등록, 상세 조회, 수정, 삭제
4. 게시글 댓글 등록, 삭제

게시판 기능은 로그인하지 않으면 접근할 수 없도록 AOP로 막아두었습니다.


---
**3. 시작 흐름**
애플리케이션 시작점은 `Step02UserBoardReplyApplication.java` 입니다.

```java
@SpringBootApplication
public class Step02UserBoardReplyApplication {
	public static void main(String[] args) {
		SpringApplication.run(Step02UserBoardReplyApplication.class, args);
	}
}
```

Spring Boot가 실행되면 컴포넌트 스캔을 통해 `Controller`, `Service`, `Repository`, `@Configuration`, `@Aspect` 등을 `Bean`으로 등록합니다.

설정 파일에서는 JSP 뷰 경로가 이렇게 잡혀 있습니다.

```properties
spring.mvc.view.prefix=/WEB-INF/views/
spring.mvc.view.suffix=.jsp
```

그래서 컨트롤러에서 `"index"` 를 반환하면 실제로는 다음 JSP가 열립니다.

```text
/WEB-INF/views/index.jsp
```


---
**4. 로그인 흐름**
로그인 화면은 `/user/login` 으로 접근합니다. `UserController.java`의 이 메서드 때문입니다.

```java
@GetMapping("/{url}")
public void url(){}
```

`/user/login` 요청이 들어오면 메서드가 `void` 이므로 Spring MVC는 요청 경로를 그대로 뷰 이름으로 사용합니다.

```text
/user/login
-> /WEB-INF/views/user/login.jsp
```

로그인 폼에서 아이디와 비밀번호를 입력하면 `/user/loginCheck` 로 POST 요청을 보냅니다.

```text
login.jsp
	-> POST /user/loginCheck
	-> UserController.login()
	-> UserService.loginCheck()
	-> UserRepository.findById()
	-> users 테이블 조회
```

`UserServiceImpl.java`에서는 다음 순서로 검사합니다.

1. `userId`로 DB에서 사용자 조회
2. 없으면 `NOTFOUND_ID` 예외
3. 비밀번호가 다르면 `WRONG_PASS` 예외
4. 맞으면 `User` 객체 반환

성공하면 세션에 로그인 정보를 저장합니다.

```java
session.setAttribute("loginUser", dbUser);
```

즉, 로그인 성공 후에는 JSP에서 다음처럼 접근할 수 있습니다.

```java
${sessionScope.loginUser}
${loginUser.name}
```

로그아웃은 `/user/logout` 이고, 내부에서 `session.invalidate()` 를 호출해 세션 전체를 무효화합니다.


---
**5. 게시판 접근 제어 흐름**
이 프로젝트에서 중요한 부분이 `SessionCheckAdvice.java` 입니다.

```java
@Before("execution(public * web.mvc.controller.FreeBoardController.*(..))")
public void before() {
	HttpSession session = request2.getSession();
	
	if(session == null || session.getAttribute("loginUser") == null) {
		throw new BasicException(ErrorCode.ACCESS_DENIED);
	}
}
```

이 코드는 `FreeBoardController`의 모든 public 메서드가 실행되기 전에 먼저 실행됩니다.
즉, 아래 게시판 요청들은 전부 로그인 검사를 통과해야 합니다.

```text
/board/list
/board/write
/board/insert
/board/read/{bno}
/board/updateForm
/board/update
/board/delete
```

로그인하지 않은 상태로 게시판에 접근하면 `ACCESS_DENIED` 예외가 발생하고, `GlobalExceptionAdvice.java`가 받아서 에러 JSP로 이동시킵니다.

```text
BasicException 발생
	-> GlobalExceptionAdvice
	-> /WEB-INF/views/error/errorView.jsp
```


---
**6. 게시글 목록 조회 흐름**
게시글 목록은 `/board/list` 입니다. `FreeBoardController.java`에서 처리합니다.

```text
GET /board/list?nowPage=1
	-> FreeBoardController.list()
	-> FreeBoardService.selectAll(pageable)
	-> FreeBoardRepository.join05(pageable)
	-> board/list.jsp
```

목록 조회는 페이징 처리가 들어가 있습니다.

```java
Pageable pageable =
	PageRequest.of((nowPage - 1), PAGE_COUNT, Sort.Direction.DESC, "bno");
```

`PAGE_COUNT`는 `application.properties`에 있습니다.

```properties
PAGE_COUNT=5
```

즉, 한 페이지에 게시글 5개씩 보여줍니다.
페이지 버튼은 `BLOCK_COUNT = 4` 기준으로 4개씩 끊어 보여줍니다.

```text
[1] [2] [3] [4] NEXT
PREV [5] [6] [7] [8] NEXT
```

목록 조회 Repository는 단순 `findAll()`이 아니라 `join05()`를 씁니다.

**FreeBoardRepository.java**
```java
@Query(
	value = "select distinct f from FreeBoard f left join fetch f.repliesList",
	countQuery = "select count(distinct f.bno) from FreeBoard f left join f.repliesList")
Page<FreeBoard> join05(Pageable page);
```

이유는 게시글 목록에서 댓글 개수도 보여주기 때문입니다.

```jsp
${board.subject} / Reply count :
<b style="color:red"> ${board.repliesList.size() }</b>
```

만약 게시글만 먼저 조회하고 각 게시글마다 댓글을 따로 조회하면 `N+1 문제`가 생길 수 있습니다. 이 프로젝트는 `fetch join + countQuery`로 그 문제를 줄이려는 구조입니다.


---
**7. Entity 관계 구조**
도메인은 세 개입니다.

**User.java**
```text
User - userId: PK - pwd - name
```

**FreeBoard.java**
```text
FreeBoard
- bno: PK, auto increment
- subject
- writer
- content
- password
- readnum
- insertDate
- updateDate
- repliesList
```

**Reply.java**
```text
Reply
- rno: PK, auto increment
- content
- insertDate
- freeBoard
```

게시글과 댓글 관계는 다음과 같습니다.

```text
FreeBoard 1개
	-> Reply 여러 개
```

코드로는 이렇게 표현되어 있습니다.

```java
// FreeBoard
@OneToMany(mappedBy = "freeBoard", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
private List<Reply> repliesList;
```

```java
// Reply
@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "free_bno")
private FreeBoard freeBoard;
```

DB 관점에서는 `reply.free_bno`가 `free_board.bno`를 참조하는 FK입니다.


---
**8. 게시글 등록 흐름**
게시글 작성 화면은 `/board/write` 입니다. `FreeBoardController`의 이 메서드가 처리합니다.

```java
@GetMapping("/{url}")
public void url(@PathVariable String url) {}
```

그래서 `/board/write` 요청은 자동으로 다음 JSP로 이동합니다.

```text
/WEB-INF/views/board/write.jsp
```

작성 폼을 제출하면 `/board/insert` 로 POST 요청이 갑니다.

```text
board/write.jsp
	-> POST /board/insert
	-> FreeBoardController.insert()
	-> DTO를 Entity로 변환
	-> FreeBoardService.insert()
	-> FreeBoardRepository.save()
	-> redirect:/board/list
```

여기서 DTO를 Entity로 바꾸는 데 `Appconfig.java`에 등록된 `ModelMapper`를 사용합니다.

```java
FreeBoard freeBoard = modelMapper.map(freeBoardDTO, FreeBoard.class);
```

등록 후에는 바로 JSP로 forward하지 않고 `redirect:/board/list`를 사용합니다. 이 패턴은 `PRG(Post-Redirect-Get)`라고 부릅니다. 새로고침 시 같은 글이 중복 등록되는 문제를 줄이는 방식입니다.


---
**9. 게시글 상세 조회 흐름**
목록에서 제목을 클릭하면 다음 URL로 이동합니다.

```text
/board/read/{bno}
```

예를 들어 10번 게시글이면:

```text
/board/read/10
```

흐름은 다음과 같습니다.

```text
GET /board/read/10
	-> FreeBoardController.read()
	-> FreeBoardService.selectBy(10, true)
	-> FreeBoardRepository.findById(10)
	-> 조회수 +1
	-> board/read.jsp
```
``
`read()` 메서드에서 핵심은 이 부분입니다.

```java
boolean state = flag == null;
FreeBoard board = freeBoardService.selectBy(Long.parseLong(bno), state);
```

`flag`가 없으면 조회수를 증가시킵니다.
상세 페이지에서 수정 후 다시 상세 페이지로 돌아오거나 댓글 등록 후 돌아올 때는 다음처럼 `?flag`를 붙입니다.

```text
/board/read/10?flag
```

이 경우 `flag`가 null이 아니므로 조회수를 올리지 않습니다. 즉, “진짜 사용자가 목록에서 글을 클릭해서 읽은 경우”에만 조회수를 올리려는 의도입니다.


---
**10. 게시글 수정 흐름**
상세 화면 `read.jsp`에는 수정 버튼이 있습니다.
수정 버튼을 누르면 JavaScript가 form action을 `/board/updateForm`으로 바꿔서 POST 요청을 보냅니다.

```text
read.jsp
	-> POST /board/updateForm
	-> FreeBoardController.updateForm()
	-> 기존 게시글 조회
	-> board/update.jsp
```

수정 화면에서 제목, 내용, 비밀번호를 입력하고 제출하면:

```text
POST /board/update
	-> FreeBoardController.update()
	-> FreeBoardService.update()
	-> 기존 게시글 조회
	-> 비밀번호 비교
	-> 제목/내용 변경
	-> redirect:/board/read/{bno}?flag
```

`FreeBoardServiceImpl.java`의 수정 로직은 다음 순서입니다.

1. `bno`로 기존 게시글 조회
2. 게시글이 없으면 `FAILED_UPDATE`
3. 비밀번호가 다르면 `FAILED_UPDATE`
4. 맞으면 `subject`, `content` 변경
5. 트랜잭션 종료 시 JPA dirty checking으로 update SQL 실행

직접 `save()`를 다시 호출하지 않아도 되는 이유는 조회한 Entity가 영속 상태이기 때문입니다.


---
**11. 게시글 삭제 흐름**
상세 화면에서 삭제 버튼을 누르면 prompt로 비밀번호를 입력받습니다.

```text
read.jsp
	-> password prompt
	-> POST /board/delete
	-> FreeBoardController.delete()
	-> FreeBoardService.delete()
```

서비스에서는 다음 순서로 처리합니다.

1. `bno`로 게시글 조회
2. 없으면 `FAILED_DELETE`
3. 비밀번호 불일치면 `FAILED_DELETE`
4. 일치하면 `freeBoardRepository.delete(board)`

`FreeBoard`에는 댓글과의 관계에 `cascade = CascadeType.ALL`이 설정되어 있습니다.

```java
@OneToMany(mappedBy = "freeBoard", cascade = CascadeType.ALL)
private List<Reply> repliesList;
```

따라서 게시글 삭제 시 연관된 댓글도 함께 삭제될 가능성이 높은 구조입니다. 단, 실제 DB FK 제약 조건과 JPA 동작 방식에 따라 세부 동작은 DB 설정도 같이 확인해야 합니다.


---
**12. 댓글 등록 흐름**
상세 화면에서 댓글 작성 버튼을 누르면 `/reply/writeForm`으로 POST 요청을 보냅니다.

```text
read.jsp
	-> POST /reply/writeForm
	-> ReplyController.writeForm()
	-> reply/write.jsp
```

댓글 작성 화면에는 부모 게시글 번호 `bno`가 hidden으로 들어갑니다.

```jsp
<input type="hidden" name="bno" value="${bno}" />
```

댓글을 제출하면:

```text
POST /reply/insert
	-> ReplyController.insertForm()
	-> ReplyDTO를 Reply Entity로 변환
	-> reply.setFreeBoard(new FreeBoard(bno))
	-> ReplyService.insert()
	-> ReplyRepository.save()
	-> redirect:/board/read/{bno}?flag
```

여기서 흥미로운 부분은 이 코드입니다.

```java
reply.setFreeBoard(new FreeBoard(bno));
```

댓글을 저장할 때 부모 게시글 전체를 다시 조회하지 않고, `bno`만 가진 `FreeBoard` 객체를 만들어 FK 연결용으로 사용합니다. 즉, `reply.free_bno = bno`가 되도록 하는 구조입니다.


---
**13. 댓글 삭제 흐름**
상세 화면에서 댓글 옆 삭제 링크를 누르면:

```text
GET /reply/delete/{rno}/{bno}
```

예:

```text
/reply/delete/3/10
```

처리 흐름은 단순합니다.

```text
ReplyController.delete()
	-> ReplyService.delete(rno)
	-> ReplyRepository.deleteById(rno)
	-> redirect:/board/read/{bno}?flag
```

삭제 후 다시 상세 페이지로 돌아가지만 `?flag`를 붙이기 때문에 조회수는 증가하지 않습니다.


---
**14. 예외 처리 구조**
예외 코드는 `ErrorCode.java`에 모여 있습니다.
대표적으로:

```text
ACCESS_DENIED: 로그인 필요
NOTFOUND_ID: 없는 ID
WRONG_PASS: 비밀번호 오류
FAILED_DETAIL: 상세 보기 실패
FAILED_UPDATE: 수정 실패
FAILED_DELETE: 삭제 실패
```

서비스나 AOP에서 문제가 생기면 `BasicException`을 던집니다.

```java
throw new BasicException(ErrorCode.WRONG_PASS);
```

이 예외는 `GlobalExceptionAdvice.java`가 공통으로 잡습니다.

```java
@ExceptionHandler(BasicException.class)
public ModelAndView error(BasicException e) {
	ModelAndView mv = new ModelAndView("error/errorView");
	mv.addObject("errMessage", e.getErrorCode().getMsg());
	mv.addObject("errStatus", e.getErrorCode().getStatus());
	return mv;
}
```

그래서 각 컨트롤러마다 try-catch를 반복하지 않아도 됩니다.


---
**15. 트랜잭션 구조**
서비스 구현체에는 트랜잭션이 걸려 있습니다.

```java
@Service
@Transactional
public class FreeBoardServiceImpl implements FreeBoardService
```

```java
@Service
@Transactional
public class ReplyServiceImpl implements ReplyService
```

게시글 등록, 수정, 삭제, 댓글 등록, 삭제는 DB 변경 작업이므로 트랜잭션 안에서 처리됩니다.
로그인 조회는 읽기 전용입니다.

```java
@Transactional(readOnly = true)
public User loginCheck(User user)
```


---
**16. DTO와 Entity를 분리한 이유**
컨트롤러에서는 주로 `FreeBoardDTO`, `ReplyDTO`를 사용하고, DB 저장에는 `FreeBoard`, `Reply` Entity를 사용합니다.

```text
JSP form 데이터
  -> DTO
  -> ModelMapper
  -> Entity
  -> Repository
```

Entity를 화면에 그대로 노출하지 않고 DTO로 바꾸면 화면 전달용 데이터와 DB 관리용 객체를 분리할 수 있습니다. 이 프로젝트에서는 ModelMapper를 써서 변환 코드를 줄이고 있습니다.


---
**17. 테스트 코드의 목적**
테스트 파일들을 보면 단순 기능 테스트보다는 JPA 학습용 성격이 강합니다.
특히 `FreeBoardJoinFetchTests.java`는 다음을 실험합니다.

- `findAll()` 사용 시 N+1 문제
- 단순 join
- fetch join
- EntityGraph
- fetch join + paging
- fetch join + countQuery

즉, 이 프로젝트는 “게시판 기능”뿐 아니라 **JPA 연관관계, LAZY loading, fetch join, paging 문제**를 같이 공부하는 예제에 가깝습니다.


---
**18. 전체 요청 흐름 요약**
가장 대표적인 사용 시나리오는 이렇습니다.

```text
1. http://localhost:9000 접속
2. Login 클릭
3. /user/login 에서 로그인
4. 성공 시 session에 loginUser 저장
5. Board 클릭
6. AOP가 loginUser 확인
7. /board/list 게시글 목록 조회
8. 제목 클릭
9. /board/read/{bno} 상세 조회 + 조회수 증가
10. 댓글 작성 또는 게시글 수정/삭제
11. 작업 후 redirect로 다시 목록 또는 상세 페이지 이동
```


---
**19. 이 프로젝트의 핵심 학습 포인트**
이 프로젝트에서 특히 봐야 할 핵심은 다음입니다.

- Spring MVC 요청 매핑 구조
- JSP View Resolver 동작 방식
- HttpSession 기반 로그인 유지
- AOP로 로그인 체크를 공통 처리하는 방식
- Controller, Service, Repository 계층 분리
- JPA Entity 관계: `FreeBoard` 1 : N `Reply`
- `@OneToMany`, `@ManyToOne`, `LAZY`, `cascade`
- 게시판 페이징 처리
- fetch join으로 댓글 개수 조회 최적화
- `@ControllerAdvice`를 이용한 공통 예외 처리
- PRG 패턴으로 등록/수정/삭제 후 redirect 처리

다만 소스의 일부 한글 주석과 JSP 문구가 인코딩 문제로 깨져 보입니다. 기능 구조 자체는 읽히지만, 화면 문구나 주석을 공부용으로 계속 볼 예정이면 UTF-8 인코딩 정리가 꽤 도움이 될 것 같습니다.