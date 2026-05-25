이 프로젝트가 복잡하게 느껴지는 이유는 **화면 하나를 띄우는 데도 여러 층이 같이 움직이기 때문**이에요. 크게 보면 아래 흐름 하나만 잡으면 됩니다.

```text
브라우저 요청
→ Controller
→ Service
→ Repository
→ DB(Entity)
→ 다시 Controller
→ JSP 화면
```

**문서 기준 큰 그림**  
PDF는 이 프로젝트를 이렇게 정의하고 있어요.

```text
회원관리: 로그인 / 로그아웃
게시판: 등록 / 수정 / 삭제 / 상세보기 / 전체검색
댓글: 등록 / 조회 / 삭제
```

그리고 API 명세서는 주소별 역할을 정리한 표예요. 예를 들면:

```text
/                 → 메인화면
/user/login       → 로그인 폼
/user/loginCheck  → 로그인 처리
/board/list       → 게시글 목록
/board/read/{bno} → 게시글 상세보기
/reply/insert     → 댓글 등록
```

**폴더별 역할**  
controller는 주소를 받는 입구입니다.
- HomeController.java: / 요청을 받아 index.jsp로 보냄
- UserController.java: 로그인, 로그아웃 처리
- FreeBoardController.java: 게시판 요청 처리
- ReplyController.java: 댓글 요청 처리 예정, 현재는 비어 있음

`service`는 실제 업무 로직입니다.
- UserServiceImpl.java: 아이디 조회, 비밀번호 확인
- FreeBoardServiceImpl.java: 게시글 목록, 등록, 상세, 수정, 삭제 로직
- ReplyService.java: 댓글 서비스 설계 자리

`domain`은 DB 테이블과 연결되는 객체입니다.

```text
User      → users 테이블
FreeBoard → 게시글 테이블
Reply     → 댓글 테이블
```

즉 User.java, FreeBoard.java, Reply.java는 “자바 객체이면서 DB 테이블 설계도”라고 보면 됩니다.

`views`는 실제 화면입니다.

```text
WEB-INF/views/index.jsp        → 메인
WEB-INF/views/user/login.jsp   → 로그인
WEB-INF/views/board/list.jsp   → 게시판 목록
WEB-INF/views/board/read.jsp   → 게시글 상세
WEB-INF/views/board/write.jsp  → 게시글 작성
WEB-INF/views/reply/write.jsp  → 댓글 작성
```

**예시: 로그인 흐름**

```text
/user/login 접속
→ UserController
→ user/login.jsp 화면 출력

로그인 버튼 클릭
→ /user/loginCheck
→ UserController.login()
→ UserServiceImpl.loginCheck()
→ UserRepository.findById()
→ DB에서 User 조회
→ 성공하면 session 저장
→ redirect:/
```

**예시: 게시판 목록 흐름**

```text
/board/list 접속
→ FreeBoardController.list()
→ FreeBoardServiceImpl.selectAll()
→ FreeBoardRepository.findAll()
→ DB에서 게시글 목록 조회
→ model에 freeList 저장
→ board/list.jsp 출력
```

현재 프로젝트에서 아직 덜 된 부분도 있어요.

- ReplyController, ReplyRepository는 아직 비어 있음
- 게시판은 /board/list, /board/write 정도만 현재 연결됨
- API 명세서에 있는 /board/read/{bno}, /board/insert, /board/update, /board/delete, /reply/...는 추가 구현이 필요함
- UserController.java에서는 세션 이름을 "user"로 저장하는데, header.jsp는 loginUser를 보고 있어서 나중에 맞춰야 합니다

한 문장으로 요약하면:

```text
Controller는 길 안내,
Service는 실제 일 처리,
Repository는 DB 심부름,
Domain은 DB 테이블 모양,
JSP는 화면입니다.
```

이 관점으로 보면 프로젝트가 훨씬 덜 무섭게 보일 거예요. 지금은 “회원 기능은 일부 완성, 게시판은 연결 중, 댓글은 아직 뼈대만 있는 상태”라고 보면 됩니다.

---
board insert 부분 추가했습니다.
수정 파일: FreeBoardController.java

추가된 코드:

```java
@PostMapping("/insert")
public String insert(FreeBoard board) {
    freeBoardService.insert(board);
    return "redirect:/board/list";
}
```

이제 흐름은 이렇게 됩니다:

```text
/board/write
→ 글 작성 폼
→ 제출
→ POST /board/insert
→ FreeBoardController.insert()
→ FreeBoardServiceImpl.insert()
→ DB 저장
→ /board/list로 이동
```


---
상세 페이지 매핑을 추가했습니다.
수정 파일: FreeBoardController.java

추가된 코드:

```java
@GetMapping("/read/{bno}")
public String read(@PathVariable Long bno, Model model) {
    model.addAttribute("board", freeBoardService.selectBy(bno, true));
    return "board/read";
}
```

이제 흐름은 이렇게 됩니다:

```text
/board/read/1
→ FreeBoardController.read()
→ freeBoardService.selectBy(1, true)
→ 게시글 조회 + 조회수 1 증가
→ model에 board 저장
→ board/read.jsp 출력
```

---
수정하기/삭제하기까지 컨트롤러 연결해뒀어요.
수정 파일: FreeBoardController.java

추가된 기능은 3개입니다.

```java
@PostMapping("/updateForm")
public String updateForm(Long bno, Model model) {
    model.addAttribute("board", freeBoardService.selectBy(bno, false));
    return "board/update";
}

@PostMapping("/update")
public String update(FreeBoard board) {
    FreeBoard updateBoard = freeBoardService.update(board);
    return "redirect:/board/read/" + updateBoard.getBno();
}

@PostMapping("/delete")
public String delete(Long bno, String password) {
    freeBoardService.delete(bno, password);
    return "redirect:/board/list";
}
```

```text
상세 페이지
→ 수정하기 클릭
→ /board/updateForm
→ update.jsp 이동

수정 완료
→ /board/update
→ 비밀번호 확인 후 수정
→ 상세 페이지로 이동

삭제하기 클릭
→ 비밀번호 prompt 입력
→ /board/delete
→ 비밀번호 확인 후 삭제
→ 목록 페이지로 이동
```
