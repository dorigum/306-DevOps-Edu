package chapter08_05.exam02;

public class Taxi implements Vehicle {
	// chapter08_p383
	// 추상 메소드 재정의
	@Override
	public void run() {
		System.out.println("택시가 달립니다.");
	}
}