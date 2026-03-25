package stream.ex02;

import java.util.Arrays;
import java.util.List;

public class FilterStreamExam02 {
	public static void main(String[] args) {
		List<String> list = Arrays.asList("장희정", "정정화", "오윤겸", "장희정", "손지민", "황태윤", "오윤겸");

		System.out.println("1. 중복행 제거  -------------");
		long count = list.stream().distinct().count();
		System.out.println(count);
		
		// ----------------------------------------------------------------
		System.out.println("2. filter 요소 걸러내기(장희정만 뽑기) -------------");
		list.stream()
		.filter((name) -> name.equals("장희정"))
		.forEach(System.out::println);
		
		// ----------------------------------------------------------------
		System.out.println("3. filter 요소 걸러내기('황'으로 시작하는) -------------");
		list.stream()
		.filter((name) -> name.startsWith("황"))
		.forEach(System.out::println);
		
		// ----------------------------------------------------------------
		System.out.println("4. filter 요소 걸러내기('화'로 끝나는) -------------");
		list.stream()
		.filter((name) -> name.endsWith("화"))
		.forEach(System.out::println);
		
		// ----------------------------------------------------------------
		System.out.println("5. 중복 제거, '오'로 시작, 출력하기 -------------");
		list.stream()
		.distinct()
		.filter((name) -> name.startsWith("오"))
		.forEach(System.out::println);
		
		
		System.out.println("--------------------------");
		System.out.println(list);
	}
}