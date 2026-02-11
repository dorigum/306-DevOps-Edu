package ex0210.constructor;

// ConstructorExam.java
class Puppy {
	// String, int 타입 전역 변수 선언(member 변수 선언)
	String puppyName; // 초기값: null -> "메리"
	int puppyNum; // 초기값: 0 -> -98998

	/*
	 * 메소드 printPuppyName 작성
	 * 인수 없음, 리턴 안함
	 * "public void printPuppyName()이 호출되었습니다!" 출력
	 * 전역 변수를 출력
	 */
	public void printPuppyName() {
		System.out.println("public void printPuppyName()이 호출되었습니다!");
		System.out.println("전역 변수 this.puppyName = " + this.puppyName); // this.puppyName, puppyName 둘 다 가능
		System.out.println("전역 변수 num = " + puppyNum);
	}

	/*
	 * 인수가 없는 생성자 작성
	 * "생성자 public Puppy()가 호출되었습니다!" 출력
	 * printPuppyName 메소드 호출
	 * 전역 변수 String에 "메리" 할당
	 * 전역 변수 int에 -98998 할당
	 */
	public void Puppy() {
		System.out.println("생성자 public Puppy()가 호출되었습니다!");
		
		this.printPuppyName();
		
		this.puppyName = "메리";
		this.puppyNum = -98998;
	}
}

// ------------------------------------------------------------------
public class ConstructorExam {
	public static void main(String[] agrs) {
		// Puppy 객체 생성(객체가 생성되는 시점, 멤버 필드 초기화 -> 명시적 초기화, 생성자 호출)
		Puppy py = new Puppy(); // 생성자 호출
		System.out.println("-------------------------------");

		// Puppy 객체의 printPuppyName 메소드 호출
		py.printPuppyName();
	}
}