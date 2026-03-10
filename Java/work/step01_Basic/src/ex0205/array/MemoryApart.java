package ex0205.array;

class MultiArray{
	// 정수형 2차원 배열 8 * 9
	// 메소드 이름 : array99
	// for loop 를 사용하여 
	// 배열에 곱한 (구구단)결과 저장
	// 배열에 결과를 꺼내 출력
	int[][] arr = new int[8][9];
	
	public void array99() {
		for(int i=0; i<arr.length; i++) {
			for(int j=0; j<arr[i].length; j++) {
				// 배열 값 출력하기
				arr[i][j] = (i + 2) * (j + 1);
				System.out.print(arr[i][j] + " ");
			}
			System.out.println();
		}
		
// -------------------------------------------------------
		System.out.println("향상된 for 문");
		for(int[] i : arr) {
			for(int num : i) {
				System.out.print(num + " ");
			}
			System.out.println();
		}
	}
}
	public class MemoryApart{
		public static void main(String[] args) {
		// main 메소드에서 
		// MultiArray 객체의 array99 메소드 호출
			new MultiArray().array99();
		}
	}