package ex0203_구도연;

import java.util.Scanner;

public class Exercise09 {

	public static void main(String[] args) {
		// while 문과 Scanner 사용
		// 키보드로부터 입력된 데이터로 예금, 출금, 조회, 종료 기능 제공 코드 작성
		boolean run = true; // 상태 변수 역할(반복문 실행 여부를 판단하는 변수)
		int balance = 0;
		Scanner sc = new Scanner(System.in);
		
		while(run) {
			System.out.println("---------------------------------");
			System.out.println("1. 예금 |" + "2. 출금 |" + "3. 잔고 |" + "4. 종료");
			System.out.println("---------------------------------");
			System.out.print("선택> ");	
			
			String choiceNum = sc.nextLine();
			
			switch(choiceNum) {
				case "1" :
					System.out.print("예금액> ");
					balance += Integer.parseInt(sc.nextLine());
					break;
				
				case "2" :
					System.out.print("출금액> ");
					balance -= Integer.parseInt(sc.nextLine());
					break;
				
				case "3" :
					System.out.println("잔고> " + balance);
					break;
					
				case "4" :
					run = false; // 종료하려면 상태 변수를 false로 변경
					break;
					
				default :
					System.out.println("1~4 사이의 숫자만 입력해주세요.");
			} // switch 종료
		} // while 종료
		System.out.println("프로그램을 종료합니다.");
	}
}