package stream.ex03;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class MatchStreamExam02 {
	public static void main(String[] args) {
		List<Student> list  = Arrays.asList(
		   new Student("희정", 22, 80, "컴퓨터"),
		   new Student("나영", 25, 60, "과학"),
		   new Student("미영", 21, 82, "컴퓨터"),
		   new Student("삼순", 30, 95, "국어"),
		   new Student("삼식", 22, 70, "수학"),
		   new Student("효경", 50, 80, "국어")
		 );
		
		// 1. allMatch - 모든 학생의 점수가 80 이상인지?
		System.out.println("1. allMatch - 모든 학생의 점수가 80 이상인지?");
		boolean re = list.stream().allMatch((s) -> s.getScore() >= 80);
		System.out.println("re = " + re);
		
		// 2. anyMatch - 학생 중에 80 이상이 한명이라도 있는지?
		System.out.println("2. anyMatch - 학생 중에 80 이상이 한명이라도 있는지?");
		boolean re2 = list.stream().anyMatch((s) -> s.getScore() >= 80);
		System.out.println("re2 = " + re2);
		
		// 3. noneMatch - 80 이상이 아무도 없니?
		System.out.println("3. noneMatch - 80 이상이 아무도 없니?");
		boolean re3 = list.stream().noneMatch((s) -> s.getScore() >= 80);
		System.out.println("re3 = " + re3);
		
		// 4. max() - 가장 많은 나이
		System.out.println("4. max() - 가장 많은 나이는?");
		Student st = list.stream()
		.max((s1, s2) -> s1.getAge() - s2.getAge())
		// .max(): 숫자를 반환하는 함수 아님!!!! 조건에 부합하는 데이터가 출력
		.orElse(null); 
		System.out.println(st);
		System.out.println("---------------------------------------------");
		
		// ----------------------------------------------------------------
		int arr[] = new int[] {1,3,5,6,8};
		
		System.out.println("5. max() - 최대값?");
		int max = Arrays.stream(arr).max().getAsInt();
		System.out.println("max = " + max);

		System.out.println("6. min() - 최소값?");
		int min = Arrays.stream(arr).min().getAsInt();
		System.out.println("min = " + min);
	}
}