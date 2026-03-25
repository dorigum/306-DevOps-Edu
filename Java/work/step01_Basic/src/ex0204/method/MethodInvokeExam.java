package ex0204.method;

class Methodinvoke01 { // ★★★★★개인 숙제
	// 다음과 같이 메소드를 작성하세요.
	
	//	method 이름 : method01
	//	어디서나, 누구나 접근가능
	//	정수 리턴
	//	인수로 정수 2개 받음
	//	인수로 들어온 정수 2개를 곱해서 출력하고 리턴
	
	public int method01(int a, int b) {
		int result = a * b;
		System.out.println("method01() 메소드 실행 결과: " + result);
		return result;
	}
}

class Methodinvoke02 {
	// method 이름 : method02
	// 어디서나 누구나 접근 가능, 객체를 생성하지 않고도 접근 가능(★static 사용)
	// 정수 리턴
	// 인수로 정수 2개 받음
	// 인수로 들어온 정수 2개를 더해서 출력하고 더한 수 리턴
	
	// static 사용으로 객체 생성 없이 클래스명으로 바로 접근 가능!
	public static int method02(int a, int b) {
		int sum = a + b;
        System.out.println("method02() 메소드 실행 결과: " + sum);
        return sum;
	}
}

class MethodInvokeExam{
	public static void main(String []args){
		Methodinvoke01 mi01 = new Methodinvoke01();
		
		// MethodInvoke01의 method01 호출
		int result1 = mi01.method01(22, 33);
	    System.out.println("main에서 확인한 결과1: " + result1);
		
		// MethodInvoke02의 method02 호출
	    int result2 = Methodinvoke02.method02(100, 200);
	    System.out.println("main에서 확인한 결과2: " + result2);
	}
}