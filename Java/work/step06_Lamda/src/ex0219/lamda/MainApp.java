package ex0219.lamda;

public class MainApp {
	public static void main(String[] args) {
		// 1. 기존 방식
//		AInterface ai = new Test();
//		ai.aa();
		// -------------------------------------------

		// 2. AnonymousInner type으로 작성(익명 타입 선언)
		// AInterface ai = new Xx() {재정의};
//		AInterface ai = new AInterface() {
//			@Override
//			public void aa() {
//				System.out.println("AnonymousInner aa 호출!!");
//			}
//		};
//		ai.aa();
		// -------------------------------------------

		// 3. 람다식
		// AInterface ai = () -> {기능};
//		AInterface ai = () -> { System.out.println("인수와 리턴이 없는 람다식"); };
//		ai.aa();
		// -------------------------------------------

		// 기능이 한 문장일 때는 {}를 생략한다.
//		AInterface ai = () -> System.out.println("인수와 리턴이 없는 람다식");
//		ai.aa();
		// -------------------------------------------

		// 인수가 있는 람다식
//		BInterface bi = (a) -> System.out.println("전달된 a = " + a);
//		bi.bb(5);
		// -------------------------------------------
		
		// 인수와 리턴이 있는 경우
//		CInterface ci = (a, b) -> a * b;
//		int re = ci.cc(5, 3);
//		System.out.println("re = " + re);
		// -------------------------------------------
		
		// return을 사용하고 싶다면 {}로 묶어서 사용
		CInterface ci = (a, b) -> { return a * b; };
		int re = ci.cc(5, 3);
		System.out.println("re = " + re);
	}
}

// ----------------------------------------------
class Test implements AInterface {
	@Override
	public void aa() {
		System.out.println("Test의 aa 구현 메소드입니다.");
	}
}