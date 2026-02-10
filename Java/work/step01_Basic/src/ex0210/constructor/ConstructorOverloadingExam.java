package ex0210.constructor;

class Puppy3 {
	/*
	 * String 타입 전역 변수 선언 int 타입 전역 변수 선언
	 */
	String puppyName; // 초기값: null
	int puppyNum; // 초기값: 0

	/*
	 * 인수가 없는 생성자 작성
	 * String 타입 전역변수에 "메리" 할당
	 * "public Puppy3()가 호출되었습니다." 출력
	 * 전역 변수 출력
	 */
	Puppy3() {
		this.puppyName = "메리";

		System.out.println("public Puppy3()가 호출되었습니다.");
		System.out.println("전역 변수 puppyName = " + this.puppyName + "puppyNum = " + puppyNum);
	}

	/*
	 * String 타입의 인수 1개를 받는 생성자 작성
	 * String 타입 전역변수에 인수 할당
	 * "public Puppy3()가 호출되었습니다" 출력
	 * 전역 변수 출력
	 */
	public Puppy3(String puppyName) { // "Java"
		this.puppyName = puppyName;
		
		System.out.println("public Puppy3(String puppyName)가 호출되었습니다.");
		System.out.println("전역 변수 puppyName = " + this.puppyName + "puppyNum = " + puppyNum);
	}

	/*
	 * String 타입의 인수 2개를 받는 생성자 작성
	 * 인수 2개를 하나의 String으로 만들어
	 * String 타입의 인수 1개를 받는 생성자에게 인수로 주며 호출
	 * "public Puppy3()가 호출되었습니다" 출력
	 */
	public Puppy3(String s1, String s2) { // "Java", "HI"
		this(s1 + s2);
		
		System.out.println("public Puppy3(String s1, String s2)가 호출되었습니다.");
	}

	/*
	 * boolean 타입의 인수 1개를 받는 생성자 작성
	 * 인수를 "쫑"과 붙여 하나의 String으로 만들어
	 * String 타입의 인수 1개를 받는 생성자에게 인수로 주며 호출
	 * "public Puppy3()가 호출되었습니다" 출력
	 */
	public Puppy3(boolean booPy) { // true
		this(booPy + "쫑"); // String 1개를 받는 생성자 호출
		System.out.println("public Puppy3(boolean booPy)가 호출되었습니다.");
	}

	/*
	 * char 타입의 인수 1개를 받는 생성자 작성
	 * 인수가 없는 생성자를 호출하고
	 * 인수로 받은 data를 int타입 전역 변수에 할당
	 * "puplic Puppy3()가 호출되었습니다" 출력
	 * int형 전역 변수 출력
	 */
	public Puppy3(char ch) { // A
		this(); // 인수가 없는 생성자 호출(기본 생성자)
		this.puppyNum = ch; // promotion
	}

	/*
	 * 메소드 printMemberVariable
	 * 리턴 없음
	 * 전역 변수를 출력
	 */
	public void printMemberVariable() {
		System.out.println("전역 변수 puppyName = " + puppyName);
		System.out.println("전역 변수 puppyNum = " + puppyNum);
	}

}

// --------------------------------------------------------------------
public class ConstructorOverloadingExam {
	// 메인 메소드에서 Puppy3 클래스의 각 생성자를 한번씩 이용해 객체 5개 생성
	public static void main(String[] args) {
		System.out.println("---------(1)---------");
		Puppy3 p1 = new Puppy3(); // 인수없는 생성자 호출
		
		System.out.println("---------(2)---------");
		Puppy3 p2 = new Puppy3("Java"); // String을 받는 생성자가 호출됨
		
		System.out.println("---------(3)---------");
		Puppy3 p3 = new Puppy3("Java", "HI"); // String을 2개 받는 생성자가 호출됨
		
		System.out.println("---------(4)---------");
		Puppy3 p4 = new Puppy3(true); // boolean을 받는 생성자가 호출됨
		
		System.out.println("---------(5)---------");
		Puppy3 p5 = new Puppy3('A'); // char를 받는 생성자가 호출됨
		
		
		System.out.println("---------주소값 출력---------");
		System.out.println("p1 = " + p1);
		System.out.println("p2 = " + p2);
		System.out.println("p3 = " + p3);
		System.out.println("p4 = " + p4);
		System.out.println("p5 = " + p5);

		// 각 객체의 printMemberVariable 메소드를 한번씩 호출
		System.out.println("---------각 객체의 메소드 호출---------");
		p1.printMemberVariable();
		p2.printMemberVariable();
		p3.printMemberVariable();
		p4.printMemberVariable();
		p5.printMemberVariable();
	}
}