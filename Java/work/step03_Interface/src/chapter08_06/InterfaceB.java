package chapter08_06;

public non-sealed interface InterfaceB extends InterfaceA {
	// chapter08_p387
	// non-sealed: 봉인 해제
	// InterfaceB는 다른 자식 인터페이스를 만들 수 있다.
	void methodB();
}