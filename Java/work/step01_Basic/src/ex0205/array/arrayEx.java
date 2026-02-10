package ex0205.array;

class MultiArrayEx{
	// 정수형 2차원 배열 8 * 9
	// 메소드 이름 : array99
	// for loop 를 사용하여 
	// 배열에 곱한 (구구단)결과 저장
	// 배열에 결과를 꺼내 출력
	
	// int[][] arr = new int[8][9]; // 0으로 초기화
	// int[][] arr;
	// int[][] arr = new int[8][]; // 행마다 다른 열 생성
	int[][] arr = new int[][] {
		{1, 2, 3}, {3, 1, 7, 4, 2, 5}, {6, 9, 0, 2}
	};
	
	public void array99() {
		// 각 행의 열을 생성
		// arr[0] = new int[3];
		// arr[1] = new int[1];
		
//		System.out.println(arr); // 주소
//		System.out.println(arr[0]); // 주소
//		System.out.println(arr[0][0]);
		
		for(int i=0; i<arr.length; i++) {
			for(int j=0; j<arr[i].length; j++) {
				System.out.print(arr[i][j] + " ");
			}
			System.out.println();
		}
	}
}
	public class arrayEx{
		public static void main(String[] args) {
		// main 메소드에서 
		// MultiArray 객체의 array99 메소드 호출
			new MultiArray().array99();
		}
	}