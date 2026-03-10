package ex0211.overriding;

// MethodOverridingExam.java
// 이 class는 수정하지 마세요.
class SuperClass { // SuperClass is Object
	protected int intNumber;
	protected String memberString = "Sting in SuperClass";

	SuperClass() { // 생성자
		System.out.println("생성자 SuperClass()가 호출되었습니다.");
	}

	public void methodZero() {
		System.out.println("SuperClass객체의 methodZero()메소드가 호출되었습니다.");
		System.out.println("intNumber=" + intNumber + "\t" + "memberString=" + memberString);
	}

	protected void methodOwn() {
		System.out.println("SuperClass객체의 methodOwn()메소드가 호출되었습니다.");
		System.out.println("intNumber=" + intNumber + "\t" + "memberString=" + memberString);
	}

	int methodTwo(int i) {
		intNumber = i;
		
		System.out.println("SuperClass객체의 methodTwo(int i)메소드가 호출되었습니다.");
		System.out.println("intNumber=" + intNumber + "\t" + "memberString=" + memberString);
		
		return intNumber;
	}

	protected String methodThree(int i, String s) {
		intNumber = i;
		memberString = s;
		
		System.out.println("SuperClass객체의 methodThree(int i, String s)메소드가 호출되었습니다.");
		System.out.println("intNumber=" + intNumber + "\t" + "memberString=" + memberString);
		
		return i + s;
	}

	public void methodFour() {
		memberString = "methodFour invokes methodOwn";
		methodOwn();
	}
}

// SubClass의 Error를 수정하고 Error의 이유를 주석으로 작성하세요.
/*
 * overrriding의 기본 조건 리턴 타입,메소드 이름, 인수가 같아야 한다.
 * modifier는 같거나 커야 한다.
 */

class SubClass extends SuperClass { // Subclass is SuperClass 관계 성립
	SubClass() {
		// super(); // 자동
		// 자식 생성자의 구현부 첫 번째 줄에서 super(); 생략된 것과 같다.
		memberString = "SubClass의 생성자가 입력한 String";
		
		System.out.println("생성자 SubClass()가 호출되었습니다.");
	}

	public void methodZero() {
		System.out.println("SubClass객체의 methodZero()메소드가 호출되었습니다.");
		System.out.println("intNumber=" + intNumber + "\t" + "memberString=" + memberString);
	}

	public void methodOwn() { // public 사용 가능
		System.out.println("SubClass객체의 methodOwn()메소드가 호출되었습니다.");
		System.out.println("intNumber=" + intNumber + "\t" + "memberString=" + memberString);
		//return intNumber;
	}

	int methodTwo(int a) {
		intNumber = a;
		
		System.out.println("SubClass객체의 methodTwo()메소드가 호출되었습니다.");
		System.out.println("intNumber=" + intNumber + "\t" + "memberString=" + memberString);
		
		return intNumber;
	}

	String methodThree(String s, int k) {
		intNumber = k;
		memberString = s;
		
		System.out.println("SubClass객체의 methodThree()메소드가 호출되었습니다.");
		System.out.println("intNumber=" + intNumber + "\t" + "memberString=" + memberString);
		
		return memberString;
	}

	protected String methodThree(int k, String str) {
		intNumber = k;
		memberString = str;
		
		System.out.println("SubClass객체의 methodThree()메소드가 호출되었습니다.");
		System.out.println("intNumber=" + intNumber + "\t" + "memberString=" + memberString);
		
		return memberString;
	}

	public void methodFour() {
		System.out.println("SubClass의 public double methodFour()가 호출되었습니다.");
		
		memberString = "methodFour invokes methodOwn";
		
		methodOwn();
	}
}

// 이 class도 수정하지 마세요.
class MethodOverridingExam {
	public static void main(String[] args) {
		SuperClass sp = new SuperClass();
		System.out.println("**************************************");
		
		SubClass sb = new SubClass();
		System.out.println("**************************************");
	}
}