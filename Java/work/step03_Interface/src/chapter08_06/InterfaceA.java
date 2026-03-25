package chapter08_06;

public sealed interface InterfaceA permits InterfaceB {
	// chapter08_p387
	// 봉인된 인터페이스: Java 15부터 무분별한 자식 인터페이스 생성을 방지하기 위해 사용
	// InterfaceA의 자식 인터페이스는 InterfaceB만 가능하고
	// 그 이외는 자식 인터페이스가 될 수 없도록 봉인
	void methodA();
}