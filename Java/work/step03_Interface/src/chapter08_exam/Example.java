package chapter08_exam;

public class Example {
	// chapter08_p393(확인 문제_08번)
	public static void action(A a) {
		a.method1();
		
		// 1. instanceof: 객체 a가 C 클래스의 인스턴스가 맞는지 확인
		// 2. 맞다면, C 타입으로 형변환(Castring)
		// 3. method2() 실행
		if(a instanceof C) { // true or false
			C c = (C)a;
			
			c.method2();
		}
	}
	
	public static void main(String[] args) {
		action(new B());
		action(new C());
	}
}