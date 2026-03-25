package ex0203_구도연;

public class Exercise10 {

	public static void main(String[] args) {
		// 이중 for 문
		// 삼각형
		for(int i=1; i<=5; i++) {
			for(int j=1; j<=i; j++) {
				System.out.print("★");
			}
			System.out.println();
		}
		System.out.println("************************");
		
		// 역삼각형
		for(int i=5; i>=1; i--) {
			for(int j=1; j<=i; j++) {
				System.out.print("★");
			}
			System.out.println();
		}
		System.out.println("************************");
		
// --------------------------------------------------------------
		// 위 코드와의 차이: 단순히 ★을 추가하고 줄바꿈
		// 여기에서는 한 행에 ★과 공백이 같이 추가되어야함
		// 상위 for 문
		//   - 하위 for 문1
		//   - 하위 for 문2
		
		// 삼각형(2)
		for(int i=1; i<=5; i++) { // 공백은 줄어들고, ★은 늘어남
			for(int j=1; j<=(5-i); j++) {
				System.out.print(" "); // 공백 채우기
			}
			// ★을 출력하기 위한 내부 for 문
			for(int k=1; k<=i; k++) {
				System.out.print("★");
			}
			System.out.println();
		}
		System.out.println("************************");
		
		// 역삼각형(2)
		for(int i=5; i>=1; i--) { // 공백은 늘어나고, ★은 줄어듦
			for(int j=1; j<=(5-i); j++) {
				System.out.print(" ");
			}
			for(int k=1; k<=i; k++) {
				System.out.print("★");
			}
			System.out.println();
		}
//		System.out.println("************************");
	}
}