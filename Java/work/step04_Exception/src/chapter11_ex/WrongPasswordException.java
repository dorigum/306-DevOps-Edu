package chapter11_ex;

public class WrongPasswordException extends Exception {
	// chapter11_p487(확인 문제 07번)
	public WrongPasswordException() { }
	
	public WrongPasswordException(String message) {
		// super 호출 필수!
		super(message);
	}
}