package ex0220.set;

import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

public class SetExam {
//	Set<String> set = new HashSet<String>();
//	Set<String> set = new TreeSet<String>(); // TreeSet(): 오름차순 정렬
	Set<String> set = new TreeSet<String>(Collections.reverseOrder()); // 내림차순 정렬
	
	public SetExam(String[] args) {
		// 추가
		for(String s : args) {
			boolean re = set.add(s); // 중복값은 저장하지 X
			System.out.println(s + " 추가 결과 = " + re);
		}
		
		System.out.println("저장된 개수 = " + set.size());
		
		// 저장된 정보를 조회(출력)
		System.out.println("\n-----정보 출력-----");
		
		Iterator<String> it = set.iterator();
		
		while(it.hasNext()) {
			String s = it.next();
			System.out.println(s);
		}
		
		System.out.println("\n-----요소 제거-----");
		
		boolean re = set.remove("마");
		
		System.out.println("제거 결과 = " + re);
		System.out.println("제거 후 = " + set); // set.toString();

		System.out.println("\n-----존재 여부-----");
		re = set.contains("하");
		System.out.println("결과 = " + re);
	}
	
	public static void main(String[] args) {
		new SetExam(args);
	}
}