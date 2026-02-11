package ex0210_구도연;

/* 싱글톤 클래스 작성법
 1. private 생성자 작성
 2. 자기 자신을 생성하는 static 멤버 필드
 3. 멤버 필드를 리턴하는 메소드 제공
 */

// CHAPTER 06_18
class ShopService {
	private static ShopService ss = new ShopService();

	private ShopService() { // 외부에서 객체를 직접 생성하지 못하도록 막음

	}

	// 현재 객체를 직접 생성해서 리턴해주는 메소드 제공
	public static ShopService getInstance() {
//		return new Test();
		return ss;
	}
}

// -----------------------------------------------------------
public class ShopServiceEaxm {
	public static void main(String[] args) {
		// new Test(); // The constructor Test() is not visible
		ShopService s1 = ShopService.getInstance();
		ShopService s2 = ShopService.getInstance();

		System.out.println("s1 = " + s1);
		System.out.println("s2 = " + s2);
	}

}
