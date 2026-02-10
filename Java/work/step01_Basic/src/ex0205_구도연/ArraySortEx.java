package ex0205_구도연;

import java.util.Arrays;

public class ArraySortEx {

	// ★버블 정렬(Bubble Sort):
	// 옆에 있는 친구랑 키를 재서, 내가 더 크면 자리바꾸기 -> 이걸 끝까지 반복!(구현하기 쉬움)

	// ★선택 정렬(Selection Sort):
	// "여기서 제일 작은 친구는 누구야?" 하고 찾아서 맨 앞줄로 데려오기
	// 그 다음 작은 친구 찾아서 두 번째 줄로...

	// ★삽입 정렬(Insertion Sort):
	// 카드 게임 할 때처럼, 카드를 하나 뽑아서 "내 앞의 애들 중 어디에 끼워 넣어야 맞지?" 하고 쏙 집어넣기!
	
	public static void main(String[] args) {
		int[] arr = {5, 7, 1, 2, 4, 3, 8, 9, 6, 10};
		
		System.out.println("정렬 전: " + Arrays.toString(arr));

		// 버블 정렬로 오름차순 정리
		// i: 전체 회전 수
		for(int i=0; i<arr.length-1; i++) {
			
			// j: 옆 친구랑 비교하는 반복문
			// - i를 빼는 이유: 맨 뒤로 보낸 큰 숫자는 이미 정렬이 끝났으니까 다시 안 봐도 됨!
			for(int j=0; j<arr.length-1-i; j++) {
				
				// "앞(j)이 뒤(j+1)보다 크면? 자리를 바꿔라!" -> 오름차순
				if(arr[j] > arr[j+1]) {
					int temp = arr[j];
					arr[j] = arr[j+1];
					arr[j+1] = temp;
				}
			}
		}
		System.out.println("정렬 후: " + Arrays.toString(arr));
	}
}