package chapter08;

public interface RemoteControl {
	// chapter08_p344
	// public 추상 메소드
	// public void turnOn();
	
	// chapter08_p349
	// 상수 선언
	int MAX_VOLUME = 10;
	int MIN_VOLUME = 0;
	
	// chapter08_p350
	// 추상 메소드: 메소드 선언부만 작성
	void turnOn();
	void turnOff();
	void setVolume(int volume);
	
	// chapter08_p355
	// 디폴트 인스턴스 메소드
	default void setMute(boolean mute) {
		if(mute) {
			System.out.println("무음 처리합니다.");
			// 추상 메소드 호출하면서 상수 필드 사용
			setVolume(MIN_VOLUME);
			
		} else {
			System.out.println("무음 해제합니다.");
		}
	}
		
	static void changeBattery() {
		System.out.println("리모콘 건전지를 교체합니다.");
	}
}