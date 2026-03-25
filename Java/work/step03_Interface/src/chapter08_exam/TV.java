package chapter08_exam;

public class TV implements Remocon {
	// Chapter08_p391(확인 문제_05번)
	public void powerOn() {
		System.out.println("TV를 켰습니다.");
	}
	
	public static void main(String[] args) {
		Remocon r = new TV();
		r.powerOn();
	}
}