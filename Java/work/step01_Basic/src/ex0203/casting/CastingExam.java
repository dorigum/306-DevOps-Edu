package ex0203.casting;

class CastingExam{
	public static void main(String []args){
		byte b1;
		byte b2, b3; 
		int i1 = 7;
		System.out.println("int i1 = " + i1);

		b1 = (byte)i1; // int형을 byte에 담을 수 없다.(형 변환 연산이 필요) : Casting
		System.out.println("byte b =" + b1);

		b2 = (byte)20;
		System.out.println("byte b2 = "+ b2);
		
	   	b3 = (byte)(b2 + b1);
		System.out.println("byte b3 = " + b3);
		
		long lo = 56897L;
		System.out.println("long lo = " + lo);

		int i4 = (int)(lo + i1); 
		System.out.println("int i4 = " + i4);
		
		boolean boo;
		boo= true;	
		System.out.println("boolean boo = " + boo);

		double d = 4.67e-3;
		System.out.println("double d = " + d);

		float f1 = lo; // 실수형은 정수형보다 크게 본다.
		System.out.println("float f1 = " + f1);
				
	    float f2 = (float)d;
		System.out.println("float f2 = " + f2);

		char c1, c2, c3;
		c1 = '\u0167';
		System.out.println("char c1 = " + c1);
		
		b2 = (byte)c1;
		System.out.println("byte b2 = " + b2);
		
		c2 = 'A';
		System.out.println("char c2 = " + c2);
		
		c3 = (char)(c2 + 2);
		System.out.println("char c3 = " + c3);
		
		
		System.out.println("------|| vs |------");
		// System.out.println(true );
		
		System.out.println("------&& vs &------");
		// System.out.println();
	}
	
	public static boolean test() {
		System.out.println("test call");
		return false;
	}
}