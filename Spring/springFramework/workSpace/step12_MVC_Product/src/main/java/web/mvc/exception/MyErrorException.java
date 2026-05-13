package web.mvc.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 상품코드가 중복되거나 가격의 범위가 벗어났을때 발생하는 예외 클래스
 */

@Getter
@RequiredArgsConstructor
public class MyErrorException extends RuntimeException {
//public class MyErrorException extends Exception {//체크예외

	private final ErrorCode errorCode;

}
/*
 * INVALID_PRODUCT_CODE(600, "상품코드가 잘못되었습니다."), DUPLICATE_PRODUCT_CODE(601,
 * "상품코드 중복입니다."), INVALID_PRICE(602, "가격은 1000~ 10000원 사이만 가능합니다."),
 * FAILD_UPDATE(603, "수정되지 않았습니다.");
 * 
 * 사용 throw new MyErrorException(ErrorCode.INVALID_PRODUCT_CODE);
 */
