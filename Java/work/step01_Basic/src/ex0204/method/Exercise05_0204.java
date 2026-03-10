package ex0204.method;

import java.util.Scanner;

class GradeCalculator {
	// ★총점, 평균, 등급 리턴 메소드 작성
	// **성적표 계산은 private, 성적표 출력은 public**
	
	// 총점 구하기(private)
	private int sumScore(int kor, int eng, int math) {
		return kor + eng + math;
	}
	
	// 평균 구하기(private)
	private int avgScore(int sum, int count) {
		return sum / count;
	}
	
	// 등급 구하기(private)
	private String getGrade(int avg) {
		if(avg >= 90) return "A";
        else if(avg >= 80) return "B";
        else if(avg >= 70) return "C";
        else if(avg >= 60) return "D";
        else return "F";
	}
	
	// 성적표 출력하기(public)
	public void scoreResult(String name, int kor, int eng, int math) {
        int sum = sumScore(kor, eng, math);
        int avg = avgScore(sum, 3); // 과목수는 3학점
        String grade = getGrade(avg);

        System.out.println("✅" + name + "님의 성적표✅");
        System.out.println("국어: " + kor + ", 영어: " + eng + ", 수학: " + math);
        System.out.println("💡 총점: " + sum);
        System.out.println("💡 평균: " + avg);
        System.out.println("💡 등급: " + grade);
    }
}

//main----------------------------------------------------------------
public class Exercise05_0204 {
	public static void main(String[] args) {
		boolean run = true; // 상태 변수 역할(반복문 실행 여부를 판단하는 변수)
		Scanner sc = new Scanner(System.in);
		
		// 점수 계산 객체 생성하기
		GradeCalculator calculator = new GradeCalculator();
		
		while(run) {
			System.out.println("---------------------------------");
			System.out.println("1. ❣성적표 구하기❣ | 2. 🧀프로그램 종료🧀");
			System.out.println("---------------------------------");
			System.out.print("선택> ");	
			
			String choiceNum = sc.nextLine();
			
			switch(choiceNum) {
				case "1" :
					System.out.print("이름 입력> ");
                    String name = sc.nextLine();
                    
					System.out.print("국어 점수> ");
					int kor = Integer.parseInt(sc.nextLine());
					
					System.out.print("영어 점수> ");
					int eng = Integer.parseInt(sc.nextLine());
					
					System.out.print("수학 점수> ");
					int math = Integer.parseInt(sc.nextLine());
					
					calculator.scoreResult(name, kor, eng, math);
					break;
				
				case "2" :
					run = false;
					break;
					
				default :
					System.out.println("1~2 사이의 숫자만 입력해주세요.");
			} // switch 종료
		} // while 종료
		
		System.out.println("프로그램을 종료합니다.");
		sc.close();
		// [Resource leak: 'sc' is never closed]
		// -> 쓰고 돌려줘야 하는데 반납(close)을 안하면 메모리 낭비될 수 있다는 알림
	}
}