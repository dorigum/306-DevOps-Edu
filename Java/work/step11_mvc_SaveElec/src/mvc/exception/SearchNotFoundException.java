package mvc.exception;

/*
 * 검색 결과가 존재하지 않을 때 처리할 예외 클래스 
 */
public class SearchNotFoundException extends Exception {
	public SearchNotFoundException() { }
	public SearchNotFoundException(String message) {
		super(message);
	}
}