package chapter11_ex;

public class Example_06 {
	// chapter11_p486(확인 문제 06번)
	public static void main(String[] args) {
		String[] strArray = {"10", "2a"};
		int value = 0;
		
		for(int i=0; i<=2; i++) {
			try {
				value = Integer.parseInt(strArray[i]);
			
			} catch(ArrayIndexOutOfBoundsException e) {
				System.out.println("인덱스 초과");
				
			} catch(NumberFormatException e) {
				System.out.println("숫자로 변환 불가");
				
			} finally {
				System.out.println(value);
				// 예외가 발생해도 value 값은 초기화되지 않고
				// 직전의 성공한 값(10)을 계속 유지하고 있는 상태
			}
		}
	}
}