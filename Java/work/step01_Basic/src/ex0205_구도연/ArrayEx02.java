package ex0205_구도연;

public class ArrayEx02 {

	public static void main(String[] args) {
		// [Chapter 05] p201 7번
		// 최대값 출력하기
		int[] array = {1, 5, 3, 8, 2};
		int max = array[0];
		
		for(int i=1; i<array.length; i++) {
			max = Math.max(max, array[i]);
		}
		System.out.println(max);
	}
}