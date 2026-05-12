package kosta.web.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/*
 * 예외 코드와 예외 메시지(언제, 어디서...)를 상수 객체로 관리 
 * enum은 서로 관련된 값들을 상수로 관리하는 객체
 */
@RequiredArgsConstructor
@Getter
public enum ErrorInfo {
   /*
    * 나이가 18보다 작을 때
    */
	INVALID_AGE(500, "미성년자는 안됩니다."),
	
	/*
	 * 아이디 중복일 때
	 */
	INVALID_ID(600, "ID는 중복입니다.");
	
	private final int status;
	private final String message;
	
	/*
	// @RequiredArgsConstructor 사용
	ErrorInfo(int status, String message) {
	this.status=status;
	this.message=message;
}

// @Getter 사용
public int getStatus() {
	return status;
}

public String getMessage() {
	return message;
} */
	
}

// ---------------------------------------------------
/*class Test{
   public void aa() {
	   ErrorInfo e = ErrorInfo.INVALID_AGE;
	   int status = e.getStatus();
   }
}*/