// UsingMethod.java
package ex0204.method;

class UsingMethodExam {  // ★★★★★개인 숙제
     //아래와 같은 메소드를 작성하세요.
   
     /*
	 Method 이름 : methodExam03_01
	 자기 자신만 접근가능
	 ReturnType : 정수
	 Parameter : 정수 1개
	----------------------------------------
	(구현부에서)하는 일 :
	"methodExam03_01이 호출되었습니다." 출력
	인수로 받은 정수 출력 
	정수 * 2 리턴
	*/
	private int methodExam03_01(int i) {
		System.out.println("methodExam03_01이 호출되었습니다.");
		System.out.println("03_01 리턴값: " + i);
		return (i * 2);
	}
	
	 /*
	 Method 이름 : methodExam03_02 // this 키워드 사용
	 어디서나 아무나 접근가능
	 Return String
	 Parameter : String 1개, 정수 1개
	 ----------------------------------------
	(구현부에서)하는 일 : 
	methodExam03_02가 호출되었습니다. 출력
	인수로 받은 String 에 바보를 붙여서 출력
	
	두 번째 인수로 들어온 값을 methodExam03_01로 넣어 주며
	methodExam03_01을 호출하고 리턴하는 값을 출력
	인수로 받은 String 에 바보를 붙여서 리턴
	*/	
	public String methodExam03_02(String a, int i) {
		System.out.println("methodExam03_02가 호출되었습니다.");
		System.out.println(a + " 바보");
		
		int num = this.methodExam03_01(i);
		System.out.println("03_02 리턴값: " + num);
		
		return (a + " 바보");
	}
	
	 /*
	Method 이름 : methodExam03_03
	같은 package에서 아무나 접근 가능
	ReturnType : 리턴 안함
	Parameter : 정수 1개와 String 1개
	----------------------------------------
	(구현부에서)하는 일 : 
	methodExam03_03이 호출되었습니다. 출력
	
	인수로 받은 정수 1개와 String 1개를 넘겨주며
	methodExam03_02호출
	*/
	void methodExam03_03(int i, String a) {
		System.out.println("methodExam03_03이 호출되었습니다.");
		this.methodExam03_02(a, i);
	}

     /*
	Method 이름 : methodExam03_04 // MakeMethodExam02.java 가 완성되어야 작성 가능
	상속 관계라면 어디서나 접근 가능
	ReturnType : 실수(부동소수형)
	Parameter : 첫 번째 인수(정수),두 번째 인수(실수), 세 번째 인수(정수)
	----------------------------------------
	(구현부에서)하는 일 : 
	methodExam03_04가 호출되었습니다. 출력
	
	인수로 들어온 값을 이용해 
	MakeMethodExam02 객체의 methodExam02_04 메소드 호출하고
	methodExam02_04가 리턴하는 값을 리턴
	*/
	protected double methodExam03_04(int i, double j, int k) {
		System.out.println("methodExam03_04가 호출되었습니다.");
		
		MakeMethodExam02 mme02 = new MakeMethodExam02();
		double result = mme02.methodExam02_04(i, j, k);
		System.out.println("02_04 리턴값: " + result);
		
		return result;
	}
	
} // UsingMethodExam 끝



class UsingMethod {
	// 메인 메소드에서
	// UsingMethodExam의 호출 가능한 메소드들을 호출하고
	// 리턴하는 값 있다면 출력
	public static void main(String []args){
		UsingMethodExam ume = new UsingMethodExam();
		
		String result1 = ume.methodExam03_02("도연", 10);
		System.out.println("✨최종 리턴값1✨: " + result1);
		
		ume.methodExam03_03(55, "Java");
		
		double result2 = ume.methodExam03_04(7, 9.5, 5);
		System.out.println("✨최종 리턴값2✨: " + result2);
	}
}