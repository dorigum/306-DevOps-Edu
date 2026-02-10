package ex0204.scanner;

import java.util.Scanner;

public class ScannerExam {

	public static void main(String[] args) {
		// 키보드 입력
		Scanner sc = new Scanner(System.in); // ctrl + shift + o -> 해당 패키지 생성
		
		// 키보드 입력은 숫자, 문자열, 문자열(공백이 있는 문자열)
		System.out.print("국어 ? ");
		int kor = sc.nextInt();
		
		System.out.print("영어 ? ");
		int eng = sc.nextInt();
		
		sc.nextLine();
		
		System.out.print("이름 ? ");
		String name = sc.nextLine(); // nextLine(): 공백 허용 / next(): 공백 허용 x
		
		System.out.println(name + "님" + " 국어: " + kor + " / 영어: " + eng);

	}
}