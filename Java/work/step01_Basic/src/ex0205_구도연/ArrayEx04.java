package ex0205_구도연;

import java.util.Scanner;

public class ArrayEx04 {

	public static void main(String[] args) {
		// [Chapter 05] p201 8번
		// 학생들의 점수를 분석하는 프로그램 작성
		// 학생 수, 각 학생들의 점수를 입력받고,
		// while 문과 Scanner의 nextLine() 메소드를 이용해서
		// 최고 점수 및 평균 점수를 출력
		boolean run = true; // 상태 변수 역할(반복문 실행 여부를 판단하는 변수)
		Scanner sc = new Scanner(System.in);
		
		int stuCnt = 0;
		int[] scores = null; // 학생 수를 입력받기 전이므로 null로 초기화
		
		while(run) {
			System.out.println("---------------------------------------------------------");
			System.out.println("1. 학생 수 | 2. 점수 입력 | 3. 점수 리스트 | 4. 분석 | 5. 종료");
			System.out.println("---------------------------------------------------------");
			System.out.print("선택> ");	
			
			int choiceNum = Integer.parseInt(sc.nextLine());
			
			switch(choiceNum) {
				case 1 :
					System.out.print("학생 수> ");
                    stuCnt = Integer.parseInt(sc.nextLine()); // 입력받은 학생 수만큼 배열 생성
                    
                    scores = new int[stuCnt];
                    break;
				
				case 2 :
					// 배열 크기만큼 반복하며 점수 입력 받기
					for(int i=0; i<scores.length; i++) {
						System.out.print("scores[" + i + "]> ");
						scores[i] = Integer.parseInt(sc.nextLine());
					}
					break;
					
				case 3 :
					// 입력한 점수 출력
					for(int i=0; i<scores.length; i++) {
						System.out.println("scores[" + i + "]: " + scores[i]);
					}
					break;
					
				case 4 :
					int max = 0;
					int sum = 0;
					double avg = 0.0;
					
					// 최고 점수 구하기
					for(int i=0; i<scores.length; i++) {
						if(scores[i] > max) { 
							max = scores[i];
						}
						sum += scores[i];
					}
					
					avg = (double) sum / stuCnt;
					
					System.out.println("최고 점수: " + max);
					System.out.println("평균 점수: " + avg);
					break;
					
				case 5 :
					run = false;
					break;
					
				default :
					System.out.println("1~5 사이의 숫자만 입력해주세요.");
			} // switch 종료
		} // while 종료
		
		System.out.println("프로그램을 종료합니다.");
		sc.close();
	}
}