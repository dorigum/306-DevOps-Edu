package ex0219.list;

import java.util.ArrayList;
import java.util.Collections;

public class ListExam01 extends ArrayList<Integer> {
	public ListExam01() { // 생성자
		super(5); // int형 인수를 받는 부모 생성자
		
		// 추가
		super.add(10);
		this.add(5);
		add(7);
		super.add(20);
		super.add(6);
		this.add(5);
		
		// list에 저장된 객체의 개수 출력
		System.out.println("저장된 개수 = " + super.size());
		
		// 저장된 정보 출력하기
		for(int i=0; i<size(); i++) { // 순서 보장, 중복 가능
			int no = super.get(i);
			System.out.println("no = " + no);
		}
		
		System.out.println("----------------개선된 for문----------------");

		for(int no : this) { // super는 독립적으로 사용 X
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
		System.out.println("정렬 전 : " + this); // this.toString()
		
		Collections.sort(this);
		System.out.println("정렬 후 : " + this); // 기본 오름차순 정렬
		
		Collections.sort(this, Collections.reverseOrder()); // 내림차순 정렬
		System.out.println("내림차순 정렬 후 : " + this);
	}
	public static void main(String[] args) {
		new ListExam01();
	}
}