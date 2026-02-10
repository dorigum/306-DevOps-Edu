package ex0205_구도연;

public class ArrayEx01 {

	public static void main(String[] args) {
		// [Chapter 05] p201 6번
		// 배열 길이 출력하기
		int[][] array = {
			{95, 86},
			{83, 92, 96},
			{78, 83, 93, 87, 88}
		};
		
		System.out.println(array.length); // 3(행)
		System.out.println(array[2].length); // 5(인덱스 번호2: 3번째 배열의 열 개수)
	}
}

