package ex0211.constructor;

/* 싱글톤 클래스 작성법
 1. private 생성자 작성
 2. 자기 자신을 생성하는 static 멤버 필드
 3. 멤버 필드를 리턴하는 메소드 제공
 */
class Test {
	private static Test t = new Test();

	private Test() { // 외부에서 객체를 직접 생성하지 못하도록 막음

	}

	// 현재 객체를 직접 생성해서 리턴해주는 메소드 제공
	public static Test getInstance() {
//		return new Test();
		return t;
	}
}

// -----------------------------------------------------------
public class PrivateConstructorExam {

	public static void main(String[] args) {
		// new Test(); // The constructor Test() is not visible
		Test t1 = Test.getInstance();
		Test t2 = Test.getInstance();

		System.out.println("t1 = " + t1);
		System.out.println("t2 = " + t2);
	}

}
