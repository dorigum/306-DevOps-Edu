package ex0213.shop;

// 나이가 18살 미만일 때 발생시킬 예외 클래스
public class ShopAgeException extends Exception {
// public class ShopAgeException extends RuntimeException {
	
	static int count; // 몇 명이 예외 처리 되었는지 체크하기 위한 변수
	public ShopAgeException() {
		super("애들은 가라~~~~~~~");
		count++;
	}
	
	// 메세지를 좀 더 유연하게 활용하고 싶다면
	public ShopAgeException(String message) {
		super(message);
		count++;
	}
}