package ex0203_구도연;

public class Exercise01 {

	public static void main(String[] args) {
		// 성적표 만들기
		// 국어, 영어, 수학 점수는 45~100 사이의 난수를 발생해서 저장
		// 경우의 수 = ((최대값 - 최소값 + 1)) + 최소값
		// A: 90~100 / B: 80~89 / C: 70~79 / D: 60~69 / F: 69 미만(디폴트)
		String name = "도연";
		int kor, eng, math;
		char grade;
		int sum;
		double avg;
		
		kor = (int)(Math.random() * 56) + 45;
		eng = (int)(Math.random() * 56) + 45;
		math = (int)(Math.random() * 56) + 45;
		sum = kor + eng + math;
		avg = sum / 3.0;
		
		grade = switch((int)(avg / 10)) {
			case 9,10 -> 'A';
			case 8 -> 'B';
			case 7 -> 'C';
			case 6 -> 'D';
			default -> 'F';
		};
		
		System.out.println("이름: " + name);
		System.out.println("국어: " + kor);
		System.out.println("영어: " + eng);
		System.out.println("수학: " + math);
		System.out.println("총점: " + sum);
		System.out.println("평균: " + (int)(avg * 100) / 100.0);
		System.out.println("등급: " + grade);
	}
}