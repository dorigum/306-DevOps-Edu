package mvc.exception;

/*
 * Electronics 배열의 저장공간을 벗어 났을 때 처리할 예외 클래스 
 */
public class ElectronicsArrayBoundsException extends Exception {
	public ElectronicsArrayBoundsException() { }
	public ElectronicsArrayBoundsException(String message) {
		super(message);
	}
}