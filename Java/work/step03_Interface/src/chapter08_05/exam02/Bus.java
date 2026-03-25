package chapter08_05.exam02;

public class Bus implements Vehicle {
	// chapter08_p382
	// 추상 메소드 재정의
	@Override
	public void run() {
		System.out.println("버스가 달립니다.");
	}
}