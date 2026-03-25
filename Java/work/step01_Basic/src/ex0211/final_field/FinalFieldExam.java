package ex0211.final_field;

public class FinalFieldExam {
	int i; // 인스턴스 필드
	
	// final 필드는 반드시 **명시적 초기화** 필수(자동 초기화 X)
	// 객체를 생성하는 사용자 객체를 생성할 때, 값을 초기화 할 수 있다.
	final int j; // 인스턴스 필드
	
	// 어떻게 객체를 생성하던(누가 사용하던) 항상 동일한 값
	static final int k; // static 필드, 생성자 초기화 불가
	
	static {
		k = 100;
	}
	public FinalFieldExam() {
		j = 10;
	}
	
	public FinalFieldExam(int j) {
		this.j = j;
	}
	
	public static void main(String[] args) {
		// static 필드 접근
		System.out.println(FinalFieldExam.k);
		
		FinalFieldExam fe = new FinalFieldExam();
		System.out.println(fe.i);
		System.out.println(fe.j);
		System.out.println(fe.k);
	
		// final은 고정값으로 값 변경 불가
		fe.i = 20;
//		fe.j = 100; // The final field FinalFieldExam.k cannot be assigned
//		fe.k = 200; // The final field FinalFieldExam.k cannot be assigned
	}
}