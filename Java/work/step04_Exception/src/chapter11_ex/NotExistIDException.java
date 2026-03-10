package chapter11_ex;

public class NotExistIDException extends Exception {
	// chapter11_p487(확인 문제 07번)
	public NotExistIDException() { }
	
	public NotExistIDException(String message) {
		// ★부모(Exception) 생성자에게 메시지를 넘겨줘야 e.getMessage()가 작동
		super(message);
	}
}