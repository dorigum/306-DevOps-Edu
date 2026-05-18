Q. 이 pdf 내용을 참고해서, 현재 작업중인 step12_MVC_Product 내 코드들이 어떤 구조로 CRUD 되고 있는지, 예외 처리는 어떻게 되고 있는지, 코드를 작성할 때 어떻게 접근해야 하는지 자세히 설명해줘

---
A. PDF 실습자료와 함께 들어있는 JSP/XML, 그리고 현재 [step12_MVC_Product](C:/Edu/Spring/springFramework/workSpace/step12_MVC_Product) 코드를 기준으로 보면, 이 프로젝트는 “DB 없이 `List<ProductDTO>`를 임시 저장소처럼 쓰는 Spring MVC CRUD 실습” 구조입니다. 다만 현재 코드는 CRUD가 완성된 상태는 아니고, 전체 목록 조회만 컨트롤러까지 연결되어 있으며 나머지는 뼈대와 JSP 링크만 있는 중간 단계입니다.

**전체 구조**
요청 흐름은 이렇게 잡으면 됩니다.

```text
브라우저 요청
 -> DispatcherServlet
 -> ProductController
 -> ProductService
 -> ProductDAO
 -> List<ProductDTO>
 -> JSP View
```

`web.xml`에서 `DispatcherServlet`이 `/` 전체 요청을 받도록 설정되어 있습니다.  
[web.xml](C:/Edu/Spring/springFramework/workSpace/step12_MVC_Product/src/main/webapp/WEB-INF/web.xml:41)

`root-context.xml`은 Service, DAO, Exception 패키지를 스캔합니다.  
[root-context.xml](C:/Edu/Spring/springFramework/workSpace/step12_MVC_Product/src/main/webapp/WEB-INF/spring/root-context.xml:11)

`servlet-context.xml`은 Controller를 스캔하고, 컨트롤러가 `"productList"` 같은 논리 뷰 이름을 반환하면 `/WEB-INF/views/productList.jsp`로 바꿔줍니다.  
[servlet-context.xml](C:/Edu/Spring/springFramework/workSpace/step12_MVC_Product/src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml:21)

초기 상품 데이터는 `productInit-context.xml`의 `ProductDTO` bean들로 등록됩니다. DAO의 생성자에 `List<ProductDTO>`가 주입되기 때문에, Spring이 등록된 `ProductDTO` bean들을 모아서 리스트로 넣어주는 구조입니다.  
[productInit-context.xml](C:/Edu/Spring/springFramework/workSpace/step12_MVC_Product/src/main/webapp/WEB-INF/spring/productInit-context.xml:10)  
[ProductDAOImpl.java](C:/Edu/Spring/springFramework/workSpace/step12_MVC_Product/src/main/java/web/mvc/dao/ProductDAOImpl.java:21)

---
**현재 동작하는 CRUD**
현재 실제로 연결된 건 “전체 조회 Read All”입니다.

`GET /` 요청이 오면 [ProductController.java](C:/Edu/Spring/springFramework/workSpace/step12_MVC_Product/src/main/java/web/mvc/controller/ProductController.java:24)의 `selectAll()`이 실행됩니다.

```java
List<ProductDTO> list = productService.select();
mv.setViewName("productList");
mv.addObject("productList", list);
```

그 다음 Service가 DAO를 호출하고, DAO는 가지고 있는 `list`를 그대로 반환합니다.  
[ProductServiceImpl.java](C:/Edu/Spring/springFramework/workSpace/step12_MVC_Product/src/main/java/web/mvc/service/ProductServiceImpl.java:25)  
[ProductDAOImpl.java](C:/Edu/Spring/springFramework/workSpace/step12_MVC_Product/src/main/java/web/mvc/dao/ProductDAOImpl.java:29)

마지막으로 `productList.jsp`가 `${productList}`를 반복 출력합니다.  
[productList.jsp](C:/Edu/Spring/springFramework/workSpace/step12_MVC_Product/src/main/webapp/WEB-INF/views/productList.jsp:50)

---
**주의할 점**
현재 `ProductServiceImpl.select()`에는 치명적인 문제가 있습니다.

```java
log.info("select = {}", select());
```

이 코드는 로그를 찍으려고 자기 자신 `select()`를 다시 호출합니다. 그래서 실제 실행 시 무한 재귀가 발생할 수 있습니다. 의도는 아마 아래처럼 DAO 결과를 로그로 찍는 것이었을 가능성이 큽니다.

```java
List<ProductDTO> list = productDAO.select();
log.info("select = {}", list);
return list;
```

---
**등록 Create 구조**
JSP는 등록 흐름을 이미 기대하고 있습니다.

`productList.jsp`에서 `/insertForm`으로 이동합니다.  
[productList.jsp](C:/Edu/Spring/springFramework/workSpace/step12_MVC_Product/src/main/webapp/WEB-INF/views/productList.jsp:60)

컨트롤러의 `@RequestMapping("/{url}") public void url() {}` 때문에 `/insertForm` 요청은 `insertForm.jsp`로 자동 이동됩니다.  
[ProductController.java](C:/Edu/Spring/springFramework/workSpace/step12_MVC_Product/src/main/java/web/mvc/controller/ProductController.java:41)

`insertForm.jsp`는 `POST /products`로 등록 요청을 보냅니다.  
[insertForm.jsp](C:/Edu/Spring/springFramework/workSpace/step12_MVC_Product/src/main/webapp/WEB-INF/views/insertForm.jsp:12)

하지만 현재 컨트롤러에는 `POST /products`를 받는 메서드가 없습니다. 따라서 등록 기능은 아직 연결되지 않았습니다.

서비스의 등록 로직은 가격 검증만 있습니다.  
[ProductServiceImpl.java](C:/Edu/Spring/springFramework/workSpace/step12_MVC_Product/src/main/java/web/mvc/service/ProductServiceImpl.java:32)

```java
if(productDTO.getPrice() > 10000 || productDTO.getPrice() < 1000)
    throw new MyErrorException(ErrorCode.INVALID_PRICE);
```

DAO의 등록 로직도 아직 잘못되어 있습니다.  
[ProductDAOImpl.java](C:/Edu/Spring/springFramework/workSpace/step12_MVC_Product/src/main/java/web/mvc/dao/ProductDAOImpl.java:34)

```java
if(productDTO.getCode().equals(insert(productDTO))) {
```

여기서 `insert(productDTO)`가 다시 자기 자신을 호출하므로 무한 재귀입니다. 등록은 보통 이렇게 접근해야 합니다.

```java
이미 같은 code가 있는지 list에서 검색
있으면 DUPLICATE_PRODUCT_CODE 예외
없으면 list.add(productDTO)
성공하면 1 반환
```

---
**상세 조회 Read One 구조**
`productList.jsp`에서 상품명을 누르면 `/read?code=A01` 같은 요청을 보냅니다.  
[productList.jsp](C:/Edu/Spring/springFramework/workSpace/step12_MVC_Product/src/main/webapp/WEB-INF/views/productList.jsp:53)

`read.jsp`는 `${product}`라는 이름의 객체를 기대합니다.  
[read.jsp](C:/Edu/Spring/springFramework/workSpace/step12_MVC_Product/src/main/webapp/WEB-INF/views/read.jsp:12)

그런데 현재 컨트롤러에는 `/read`를 처리해서 `productService.selectByCode(code)`를 호출하고 `"product"`를 Model에 넣는 메서드가 없습니다. Service와 DAO의 `selectByCode()`도 아직 정상 구현이 아닙니다. 특히 이 조건은 항상 false입니다.

```java
if(!code.equals(code))
```

같은 값을 자기 자신과 비교하므로 상품 코드 검증이 되지 않습니다. 실제로는 DAO에서 해당 code를 가진 상품을 찾고, 없으면 `INVALID_PRODUCT_CODE`를 던지는 식으로 작성해야 합니다.

**삭제 Delete 구조**
목록 JSP의 삭제 버튼은 jQuery로 `/del/{code}`로 이동합니다.  
[productList.jsp](C:/Edu/Spring/springFramework/workSpace/step12_MVC_Product/src/main/webapp/WEB-INF/views/productList.jsp:24)

하지만 현재 컨트롤러에는 `/del/{code}` 매핑이 없습니다. DAO의 `delete()`도 `return 0`만 하고 있습니다.  
[ProductDAOImpl.java](C:/Edu/Spring/springFramework/workSpace/step12_MVC_Product/src/main/java/web/mvc/dao/ProductDAOImpl.java:45)

삭제는 보통 이렇게 씁니다.

```java
code로 상품 찾기
없으면 INVALID_PRODUCT_CODE 예외
있으면 list.remove(product)
성공하면 1 반환
삭제 후 redirect:/
```

**수정 Update 구조**
상세 페이지에서 `/updateForm/{code}`로 이동합니다.  
[read.jsp](C:/Edu/Spring/springFramework/workSpace/step12_MVC_Product/src/main/webapp/WEB-INF/views/read.jsp:33)

`updateForm.jsp`는 기존 상품 정보를 `${product}`로 채우고, `POST /products/{code}`로 수정 요청을 보냅니다.  
[updateForm.jsp](C:/Edu/Spring/springFramework/workSpace/step12_MVC_Product/src/main/webapp/WEB-INF/views/updateForm.jsp:12)

하지만 현재 컨트롤러에는 `/updateForm/{code}`와 `POST /products/{code}` 매핑이 없습니다. Service/DAO의 `updateByCode()`도 아직 미구현입니다. 수정은 보통 다음 순서입니다.

```java
path의 code로 기존 상품 찾기
없으면 INVALID_PRODUCT_CODE
가격 검증
기존 객체의 name, price, detail 변경
성공하면 1 반환
수정 후 redirect:/read?code=...
```

**예외 처리 구조**
예외는 `ErrorCode enum` + `MyErrorException` + `GlobalExceptionAdvice` 구조입니다.

`ErrorCode`에는 상품 코드 오류, 중복 코드, 가격 오류, 수정 실패 코드가 정의되어 있습니다.  
[ErrorCode.java](C:/Edu/Spring/springFramework/workSpace/step12_MVC_Product/src/main/java/web/mvc/exception/ErrorCode.java:12)

`MyErrorException`은 `ErrorCode`를 들고 다니는 사용자 정의 런타임 예외입니다.  
[MyErrorException.java](C:/Edu/Spring/springFramework/workSpace/step12_MVC_Product/src/main/java/web/mvc/exception/MyErrorException.java:11)

`GlobalExceptionAdvice`는 프로젝트 어디에서든 `MyErrorException`이 던져지면 잡아서 에러 페이지로 보내려는 전역 예외 처리 클래스입니다.  
[GlobalExceptionAdvice.java](C:/Edu/Spring/springFramework/workSpace/step12_MVC_Product/src/main/java/web/mvc/exception/GlobalExceptionAdvice.java:15)

다만 현재 예외 처리도 손볼 곳이 있습니다.

첫째, `e.getMessage()`는 현재 거의 `null`이 됩니다. `MyErrorException`이 메시지를 부모 생성자에 넘기지 않고 `ErrorCode`만 필드로 가지고 있기 때문입니다. 따라서 `e.getErrorCode().getMsg()`와 `e.getErrorCode().getStatus()`를 써야 합니다.

둘째, `mv.setViewName("error/error")`는 `/WEB-INF/views/error/error.jsp`를 찾습니다. 그런데 현재 프로젝트에는 `/WEB-INF/views/error.jsp`만 있습니다.  
[error.jsp](C:/Edu/Spring/springFramework/workSpace/step12_MVC_Product/src/main/webapp/WEB-INF/views/error.jsp:1)

그래서 둘 중 하나로 맞춰야 합니다.

```java
mv.setViewName("error");
```

또는 실제 JSP를 `/WEB-INF/views/error/error.jsp`로 옮깁니다.

셋째, `@ControllerAdvice`는 `GlobalExceptionAdvice`에만 있으면 됩니다. 현재 Service와 DAO에도 붙어 있는데, 예외 처리 메서드가 없고 역할상 맞지 않으므로 제거하는 편이 깔끔합니다.  
[ProductServiceImpl.java](C:/Edu/Spring/springFramework/workSpace/step12_MVC_Product/src/main/java/web/mvc/service/ProductServiceImpl.java:18)  
[ProductDAOImpl.java](C:/Edu/Spring/springFramework/workSpace/step12_MVC_Product/src/main/java/web/mvc/dao/ProductDAOImpl.java:19)

---
**코드 작성 접근법**
이 실습은 컨트롤러부터 막 작성하기보다, 아래 순서로 접근하면 덜 꼬입니다.

1. JSP가 보내는 URL을 먼저 확인합니다.
`/`, `/insertForm`, `POST /products`, `/read?code=`, `/del/{code}`, `/updateForm/{code}`, `POST /products/{code}`

2. 컨트롤러에 URL별 메서드를 만듭니다.
컨트롤러는 요청 파라미터를 받고, Service를 호출하고, View 이름 또는 `redirect:/`만 결정합니다.

3. Service에는 비즈니스 검증을 둡니다.
가격 범위, 존재하지 않는 상품 코드, 수정 실패 같은 판단은 Service에서 하는 게 좋습니다.

4. DAO는 `List<ProductDTO>`를 실제 저장소처럼 다룹니다.
검색은 반복문/stream, 등록은 `add`, 삭제는 `remove`, 수정은 기존 객체 setter 변경으로 처리합니다.

5. 실패 상황은 `return 0`으로 조용히 넘기지 말고 `MyErrorException`을 던집니다.
그러면 `GlobalExceptionAdvice`가 한 곳에서 에러 화면으로 보내줍니다.

정리하면, 이 프로젝트의 의도는 MVC 계층 분리 연습입니다. `Controller`는 URL 담당, `Service`는 검증과 업무 규칙 담당, `DAO`는 리스트 CRUD 담당, `JSP`는 화면 담당입니다. 지금 코드는 그 구조의 뼈대는 잡혀 있지만, 목록 조회 외 CRUD 매핑과 DAO 로직, 전역 예외 화면 연결은 아직 완성해야 하는 상태입니다.