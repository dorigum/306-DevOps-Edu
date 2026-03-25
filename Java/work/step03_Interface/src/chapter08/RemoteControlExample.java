package chapter08;

public class RemoteControlExample {
	public static void main(String[] args) {
		// chapter08_p346
		// chapter08_p354
		// 인터페이스 변수 선언
		RemoteControl rc;

		// rc 변수에 Television 객체를 대입
		rc = new Television();
		rc.turnOn();
		rc.setVolume(5);
		// rc.turnOff();
		System.out.println("--------------------------------");
		
		// 디폴트 메소드 호출
		rc.setMute(true);
		rc.setMute(false);
		System.out.println("--------------------------------");
		
		// -----------------------------------------------------------------
		// rc 변수에 Audio 객체를 대입(교체 시킴)
		rc = new Audio();
		rc.turnOn();
		rc.setVolume(5);
		// rc.turnOff();
		System.out.println("--------------------------------");
		
		// 디폴트 메소드 호출
		rc.setMute(true);
		rc.setMute(false);
		System.out.println("--------------------------------");
		/*
		 * System.out.println("리모콘 최대 볼륨: " + RemoteControl.MAX_VOLUME);
		 * System.out.println("리모콘 최저 볼륨: " + RemoteControl.MIN_VOLUME);
		 */
		RemoteControl.changeBattery();
	}
}