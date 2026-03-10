package ex0205_구도연;

public class ArrayEx03 {

	public static void main(String[] args) {
		// [Chapter 05] p201 8번
		// 배열 항목의 전체 합과 평균을 구해서 출력
		// 중첩 for 문 이용
		int[][] array = {
			{95, 86},
			{83, 92, 96},
			{78, 83, 93, 87, 88}
		};
		
		int sum = 0; // 배열 항목의 합계
		int count = 0; // 배열 항목의 개수
		
		for(int i=0; i<array.length; i++) { // 행
			for(int j=0; j<array[i].length; j++) { // array[i].length; -> "i 번째 줄아, 너 칸(열)이 몇 개니?"
				sum += array[i][j]; // 합계 누적
				count++; // 개수 증가
			}
		}
		
		double avg = (double)sum / count;
		
		System.out.println("배열 항목의 합: " + sum);
		System.out.println("평균: " + avg);
	}
}