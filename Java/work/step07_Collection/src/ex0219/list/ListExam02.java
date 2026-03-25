package ex0219.list;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class ListExam02 {
//	List<Integer> list = new ArrayList<Integer>(5);
//	List<Integer> list = new Vector<Integer>(5);
	List<Integer> list = new LinkedList<Integer>();
	
	public ListExam02() { // 생성자
		// 추가
		list.add(10);
		list.add(5);
		list.add(7);
		list.add(20);
		list.add(6);
		list.add(5);
		
		// list에 저장된 객체의 개수 출력
		System.out.println("저장된 개수 = " + list.size());
		
		// 저장된 정보 출력하기
		for(int i=0; i<list.size(); i++) { // 순서 보장, 중복 가능
			int no = list.get(i);
			System.out.println("no = " + no);
		}
		
		System.out.println("----------------개선된 for문----------------");

		for(int no : list) { // super는 독립적으로 사용 X
			System.out.println("no = " + no);
		}
		
		// -----------------------------------------------
		// 삭제
//		int i = this.remove(2); // 세번째 제거
//		System.out.println("삭제 후 = " + i); // 삭제된 객체값
		
//		boolean b = this.remove((Object)20);
//		System.out.println("b = " + b);
//		
//		System.out.println("----------------삭제 후 개선된 for문----------------");
//
//		for(int no : this) {
//			System.out.println("no = " + no);
//		}
		
		System.out.println("----------------정렬하기----------------");
		System.out.println("정렬 전 : " + list); // this.toString()
		
		Collections.sort(list);
		System.out.println("정렬 후 : " + list); // 기본 오름차순 정렬
		
		Collections.sort(list, Collections.reverseOrder()); // 내림차순 정렬
		System.out.println("내림차순 정렬 후 : " + list);
	}
	public static void main(String[] args) {
		new ListExam02();
	}
}