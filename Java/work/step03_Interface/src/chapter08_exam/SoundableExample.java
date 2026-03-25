package chapter08_exam;

public class SoundableExample {
	// Chapter08_p391(확인 문제_06번)
	public static void printSound(Soundable soundable) {
		System.out.println(soundable.sound());
	}
	
	public static void main(String[] args) {
		printSound(new Cat());
		printSound(new Dog());
	}
}