package ex0204.method;

class MakeMethodExam02{ // ★★★★★개인 숙제
/*	
	Method 이름 : methodExam02_01
	자기 자신만 접근 가능
	Return Type : 정수
	Parameter : 정수 2개	
	--------------------------------------------------------
	(구현부에서)하는 일 : 인수로 받은(들어온) 두 개의 정수의 합을 출력하고
	1. 두 정수의 합이 3의 배수이면 "3의 배수"출력
	2. 두 정수의 합 리턴
*/
	private int methodExam02_01(int a, int b) {
		int sum = a + b;
		System.out.println("두 정수의 합: " + sum);
		
		if(sum % 3 == 0) {
			System.out.println("3의 배수");
		}
		return sum;
	}

/*	Method 이름 : methodExam02_02
	어디서나, 아무나 접근 가능
	Return Type : String
	Parameter : String	
	--------------------------------------------------------
	(구현부에서)하는 일 : 인수로 받은(들어온) String에 바보를 붙여서 출력
	인수로 받은 (들어온) String에 바보를 붙여서 리턴
*/
	public String methodExam02_02(String a) {
		String result = a + "바보";
		System.out.println(result);
		return result;
	}
	
/*	
	Method 이름 : methodExam02_03
	같은 package에서 아무나 접근 가능
	Return Type : 리턴 X
	Parameter : 정수 1개와 String 1개	
	--------------------------------------------------------
	(구현부에서)하는 일 : 인수로 받은 정수와 String을 (하나의 String으로) 붙여서 출력
	인수로 받은 정수가 4의 배수라면 1 출력, 아니라면 0 출력
*/
	protected void methodExam02_03(int a, String b) {
		System.out.println(a + b);
		
		if(a % 4 == 0) { // 4의 배수라면
			System.out.println(1); // 1 출력
		} else { // 아니라면
			System.out.println(0); // 0 출력
		}
	}
	
/*	
	Method 이름 : methodExam02_04
	상속 관계라면 어디서나 접근 가능
	Return Type : 실수(부동소수형)
	Parameter : 첫 번째 인수(정수),두 번째 인수(실수),세 번째 인수(정수)
	--------------------------------------------------------	
	(구현부에서)하는 일 : (첫 번째 인수 * 두 번째 인수) - 세 번째 인수 결과 출력
	위의 결과를 리턴
*/
	protected double methodExam02_04(int i, double j, int k) {
		double result = (i * j) - k;
		System.out.println("계산 결과: " + result);
		
		return result;
	}
	
// main---------------------------------------------------------------
	public static void main(String [] args){
		MakeMethodExam02 mme = new MakeMethodExam02();
		
		int k = mme.methodExam02_01(33, 22);
		System.out.println("리턴된 합: " + k);
		
		System.out.println("-----------------");
        mme.methodExam02_02("도연");
		
        System.out.println("-----------------");
   		mme.methodExam02_03(42, " 이뿌니~");
   		
   		System.out.println("-----------------");
        mme.methodExam02_04(7, 5.5, 8);
	}
}