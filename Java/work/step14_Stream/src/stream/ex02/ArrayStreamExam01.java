package stream.ex02;

import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class ArrayStreamExam01 {
	static int sum;
	
	public static void main(String[] args) {
		System.out.println("1.String Array Stream ------------");
		String[] strArr = {"희정", "현준", "정화", "민지", "경찬"};
		/*
		 * for(String s:strArr) { // 자료 구조의 Iterator를 이용한 방식 System.out.println(s); }
		 */

		// Stream을 이용해서 출력해보자
		// 1) 람다식
		System.out.println("1) 람다식 ------------");
		Stream<String> stream = Arrays.stream(strArr); // .stream으로 인해 String으로 변환됨
		stream.forEach((s) -> System.out.println(s));		
		
		// 2) 메소드 참조
		System.out.println("2) 메소드 참조 ------------");
//		stream(strArr).forEach(System.out::println); // 이미 사용한 Stream은 재사용 불가
		
		// 3) 한번 사용한 Stream을 다시 사용해보자(사용 불가 x)
		Arrays.stream(strArr).forEach(System.out::println); // -> 재생성 필요
		
		System.out.println("2.int Array Stream ------------");
		int[] intArr = {1, 2, 3, 4, 5, 6, 7, 8, 9};
		
		Arrays.stream(intArr)
			.filter(i -> i%2 == 0)
			.forEach(System.out::println);

		System.out.println("3.range() vs rangeClosed() ------------");
		IntStream.range(0, 10).forEach(System.out::println); // 0~9 출력
		
		System.out.println("--------------------------------");
		IntStream.rangeClosed(0, 10).forEach(System.out::println); // 0~10 출력

		// 지역변수로 선언되어 있으면 익명 클래스에서 사용 불가, final만 사용 가능
		// -> final 은 값 변경이 안되므로 연산(수정)을 하려고 하면 에러 발생
		// Chapter09_p406
		// Java 8 이후부터는 명시적으로 final 을 붙이지 않아도 final 특성을 갖게 된다.
		System.out.println("--------------------------------");
		IntStream.rangeClosed(0, 10).forEach((i) -> sum += i);
		System.out.println("합계 = " + sum);
		
		int re = IntStream.rangeClosed(0, 10).sum();
		System.out.println("re = " + re);
	}
}