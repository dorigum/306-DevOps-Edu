- Controller가 요청을 받아서 ->
- Service에게 요청을 전달+필요한 정보 요청->
	- (Controller에서 바로 DAO로 안 가고 Service를 거쳐가는 이유: 중간에서 검증 역할을 함!!!)
- DAO가 요청과 동일한 정보가 있는지 조회->

---
1. 상품 상세 보기 기능
	1. 1. DAO에게 code로 상품을 찾아달라고 한다.
	2. DAO가 null을 반환하면, 해당 상품 코드가 없다는 뜻이다.
	3. 그러면 INVALID_PRODUCT_CODE 예외를 던진다.
	4. 상품이 있으면 Controller에게 반환한다.

>**여기서 왜 Service에서 예외를 던질까요?**
>
>DAO는 단순히 “찾아봤는데 없네요”라고 null을 줄 수 있습니다.
>그런데 “없는 상품 코드를 요청한 건 에러 상황이다”라고 판단하는 건 업무 규칙에 가깝습니다.
>그래서 Service에서 처리하는 게 자연스럽습니다.



**4. DAO는 list에서 상품을 찾는다**
이미 `ProductDAOImpl.java`에 `selectByCode()`를 구현해두셨죠.

```java
@Override
public ProductDTO selectByCode(String code) {
	for (ProductDTO product : list) {
		if (product.getCode().equals(code)) {
			return product;
		}
	}
	return null;
}
```

이 코드는 아주 중요합니다. 상세보기, 삭제, 수정이 모두 이 메서드를 기반으로 돌아갑니다.
동작은 이렇습니다.

```text
list = [A01 상품, A02 상품, A03 상품...]

요청 code = A02

1. A01 상품을 꺼내서 code 비교
   A01 == A02 ? 아님
   
2. A02 상품을 꺼내서 code 비교
   A02 == A02 ? 맞음
   
3. A02 상품 반환
```

못 찾으면 null을 반환합니다.


**7. 상세보기 완성 후 동작 흐름**

최종적으로는 이렇게 됩니다.

```text
1. 사용자가 목록에서 상품명 클릭
2. 브라우저가 /read?code=A01 요청
3. ProductController.read(String code) 실행
4. code 변수에 "A01"이 자동 저장됨
5. productService.selectByCode("A01") 호출
6. productDAO.selectByCode("A01") 호출
7. list에서 code가 A01인 ProductDTO 찾음
8. 찾은 ProductDTO를 Service -> Controller로 반환 
9. Controller가 ModelAndView에 "product"라는 이름으로 담음
10. /WEB-INF/views/read.jsp 실행
11. read.jsp가 ${product.name}, ${product.price} 등을 출력
```

지금 단계에서 기억할 핵심은 하나예요.

```text
- Controller는 URL과 화면 이동 담당
- Service는 있는 상품인지 검사 담당
- DAO는 list에서 실제로 찾는 담당
- JSP는 전달받은 product 출력 담당
```


상세보기 기능은 잘 작성됐어요. 흐름도 정확합니다.

`/read?code=A01` 요청이 들어오면 **ProductController.java**의 `read(String code)`가 `code`를 받고, `productService.selectByCode(code)`를 호출한 뒤 `"product"`라는 이름으로 `read.jsp`에 넘깁니다. **ProductServiceImpl.java**에서는 DAO 조회 결과가 `null`이면 `INVALID_PRODUCT_CODE` 예외를 던지고, **ProductDAOImpl.java**는 `list`에서 코드가 같은 상품을 찾아 반환합니다. 이 구조가 딱 맞아요.

작은 오타 하나만 고쳤습니다. 컨트롤러 로그가 실제 코드값을 찍도록 수정했어요.

```java
log.info("상품 상세보기 call = {}", code);
```

그리고 다음 단계로 **삭제 기능**까지 연결해뒀습니다.
추가된 흐름은 이렇습니다.

```text
productList.jsp 삭제 버튼
-> /del/{code}
-> ProductController.delete()
-> ProductServiceImpl.delete()
-> ProductDAOImpl.delete()
-> list.remove(product)
-> redirect:/
```

수정된 핵심 코드는 다음 세 군데입니다.

**ProductController.java**
```java
@RequestMapping("/del/{code}")
public String delete(@PathVariable String code) {
	log.info("상품 삭제 call = {}", code);
	
	productService.delete(code);
	
	return "redirect:/";
}
```

**ProductServiceImpl.java**
```java
public int delete(String code) throws MyErrorException {
	ProductDTO product = productDAO.selectByCode(code);
	
	if (product == null) {
		throw new MyErrorException(ErrorCode.INVALID_PRODUCT_CODE);
	}
	
	return productDAO.delete(code);
}
```

**ProductDAOImpl.java**
```java
public int delete(String code) throws MyErrorException {
	ProductDTO product = selectByCode(code);
	
	if (product == null) {
		return 0;
	}
	
	list.remove(product);
	return 1;
}
```

---
### 수정하기

```text
1단계: 수정 폼 보여주기
/read.jsp에서 수정하기 클릭
 -> /updateForm/{code}
 -> code로 기존 상품 조회
 -> updateForm.jsp에 product 전달

2단계: 실제 수정 처리
updateForm.jsp에서 수정 내용 submit
 -> POST /products/{code}
 -> code로 기존 상품 찾기
 -> 가격 검증
 -> 기존 상품의 name, price, detail 변경
 -> redirect:/read?code={code}
```

**삭제 기능과 비교**
```text
삭제:
code로 찾는다 -> remove 한다 -> 목록으로 이동

수정:
code로 찾는다 -> 값을 바꾼다 -> 상세보기로 이동
```

Service의 역할은 이렇게 나뉩니다.
```text
1. 가격 범위 검사
2. 수정 대상 상품이 존재하는지 검사
3. DAO에게 수정 요청
4. 수정 결과가 실패면 예외
```

DAO는 실제 list 안의 객체를 바꿉니다.
```java
@Override
public int updateByCode(ProductDTO productDTO) throws MyErrorException {
	ProductDTO dbProduct = selectByCode(productDTO.getCode());
	
	if (dbProduct == null) {
		return 0;
	}
	
	dbProduct.setName(productDTO.getName());
	dbProduct.setPrice(productDTO.getPrice());
	dbProduct.setDetail(productDTO.getDetail());	
	
	return 1;
}
```

여기서 중요한 건 list 안의 객체를 새 객체로 바꾸는 게 아니라, **찾아온 기존 객체의 값을 setter로 변경**한다는 점이에요.
```java
dbProduct.setName(...)
dbProduct.setPrice(...)
dbProduct.setDetail(...)
```

왜냐하면 dbProduct는 list 안에 들어있는 실제 객체를 참조하고 있기 때문입니다. 그래서 이 객체의 값을 바꾸면 리스트 안의 데이터도 바뀐 것처럼 동작합니다.

정리하면 수정 기능은 이렇게 구현하면 됩니다.
```text
Controller:
- /updateForm/{code} : 수정 폼 보여주기
- POST /products/{code} : 수정 처리

Service:
- 가격 검사
- 상품 존재 여부 검사
- DAO 수정 호출
- 실패 시 예외

DAO:
- code로 기존 상품 찾기
- name, price, detail 변경
- 성공 1 반환
```

삭제/조회 흐름을 이해했다면 수정도 같은 패턴이에요. 차이는 ==**삭제는 remove**, **수정은 setter로 값 변경**==입니다.


---
### 예외 처리
```text
사용자가 잘못된 요청을 보냄
 -> Service 또는 DAO에서 문제 발견
 -> throw new MyErrorException(ErrorCode.XXX)
 -> GlobalExceptionAdvice가 예외를 가로챔
 -> error.jsp에 에러 정보 전달
 -> 사용자에게 에러 페이지 출력
```

**1. 예외가 필요한 상황**
이 프로젝트에서는 이런 상황들이 예외입니다.

```text
상품 코드 중복
-> DUPLICATE_PRODUCT_CODE

존재하지 않는 상품 코드 조회/삭제/수정
-> INVALID_PRODUCT_CODE

가격이 1000보다 작거나 10000보다 큼
-> INVALID_PRICE

수정 결과가 실패
-> FAILD_UPDATE
```

이 값들은 ErrorCode.java에 enum으로 정리되어 있습니다.
```java
INVALID_PRODUCT_CODE(600, "상품코드가 잘못되었습니다."),
DUPLICATE_PRODUCT_CODE(601, "상품코드 중복입니다."),
INVALID_PRICE(602, "가격은 1000~ 10000원 사이만 가능합니다."),
FAILD_UPDATE(603, "수정되지 않았습니다.");
```

enum은 쉽게 말하면 **정해진 에러 목록표**예요.

문자열을 여기저기 직접 쓰는 대신, 정해진 이름으로 관리하는 겁니다.
```java
throw new MyErrorException(ErrorCode.INVALID_PRICE);
```

이렇게 쓰면 “가격 오류”라는 의미가 코드에서 바로 보입니다.


**6. 왜 Controller에서 try-catch 하지 않을까?**
초보 단계에서는 이런 생각이 들 수 있어요.
```java
try {
    productService.insert(productDTO);
} catch (MyErrorException e) {
    ...
}
```

Controller마다 이렇게 처리할 수도 있습니다. 하지만 그러면 등록, 삭제, 수정, 상세보기마다 비슷한 catch 코드가 계속 생깁니다.
그래서 Spring MVC에서는 `@ControllerAdvice`를 사용해서 예외 처리를 한 곳에 모읍니다.
```text
Controller는 정상 흐름만 담당
Service/DAO는 문제 상황에서 예외 던짐
GlobalExceptionAdvice는 예외 화면 담당
```

이렇게 역할을 나누면 코드가 훨씬 깔끔해집니다.


---
# SpringMVC_Ajax
