package ex0212.equals;

class EqualsExam2{
	String stringObject;
	
	public EqualsExam2(String s){
		stringObject = s;
	}
	
	// instanceOf, DownCasting을 왜 사용하는지 중요!!!!
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof EqualsExam2) {
			EqualsExam2 e2 = (EqualsExam2) obj; // ObjectDownCasting
			
			return stringObject.equals(e2.stringObject);
		}
		return false;
	}
	
	public static void main(String args[]){		
		System.out.println("\nEqualsExam ee1 = new EqualsExam(\"Object.equals()\");" );
		System.out.println("\nEqualsExam ee2 = new EqualsExam(\"Object.equals()\");" );
				
		EqualsExam2 ee1 = new EqualsExam2("Object.equals()");
		EqualsExam2 ee2 = new EqualsExam2("Object.equals()");
		
		// false
		if(ee1.equals(ee2)){ // Object에 정의된  equals 호출: 주소값 비교
			System.out.println("ee1.equals(ee2) = true");
		} else {
			System.out.println("ee1.equals(ee2) = false");
		}
		
		// false
		if(ee1 == ee2){
			System.out.println("(ee1 == ee2) = true");
		} else {
			System.out.println("(ee1 == ee2) = false");
		}
		
		System.out.println("\nString s1 = \"new String Object1\";");
		System.out.println("\nString s2 = \"new String Object1\";");
		
		
		// ---------------------------------------------------
		String s1 = "new String Object1";
		String s2 = "new String Object1";
		
		// true : 글자값 비교
		if(s1.equals(s2)) { // 문자열 비교
			System.out.println("s1.equals(s2) = true");
		} else {
			System.out.println("s1.equals(s2) = false");
		}
		
		// true
		if(s1 == s2) {  // 주소값 비교
			System.out.println("(s1 == s2) = true");
		} else {
			System.out.println("(s1 == s2) = false");
		}

		System.out.println("\nString s3 = new String(\"new String Object2\");");
		System.out.println("\nString s4 = new String(\"new String Object2\");");
 
 		String s3 = new String("new String Object2");
		String s4 = new String("new String Object2");
		
		// true
		if(s3.equals(s4)) { // 문자열 비교
			System.out.println("s3.equals(s4) = true");
		} else {
			System.out.println("s3.equals(s4) = false");
		}
		
		// false
		if(s3 == s4) {  // 주소값 비교
			System.out.println("(s3 == s4) = true");
		} else {
			System.out.println("(s3 == s4) = false");
		}

		System.out.println("\nString s5 = new String(\"new String Object3\");");
		System.out.println("\nString s6 = \"new String Object3\";");
		System.out.println("\nString s7 = \"new String Object3\";");
 
 		String s5 = new String("new String Object3");
		String s6 = "new String Object3";
		String s7 = "new String Object3";
		
		// true
		if(s5.equals(s6)) {  // 문자열 비교
			System.out.println("s5.equals(s6) = true");
		} else {
			System.out.println("s5.equals(s6) = false");
		}
		
		// false
		if(s5 == s6) { // 주소값 비교
			System.out.println("(s5 = s6) = true");
		} else {
			System.out.println("(s5 == s6) = false");
		}

		// true
		if(s6.equals(s7)) {  // 문자열 비교
			System.out.println("s6.equals(s7) = true");
		} else {
			System.out.println("s6.equals(s7) = false");
		}
		
		// true
		if(s6 == s7) { // 즈소값 비교
			System.out.println("(s6 == s7) = true");
		} else {
			System.out.println("(s6==s7) = false");
		}
	}
}
