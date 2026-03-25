package ex0220.set;

import java.util.HashSet;
import java.util.Set;

public class MemberSetExam {
	Set<Member> set = new HashSet<Member>();

	public MemberSetExam() {
		// 회원 추가
		set.add(new Member("도연", 10, "경기"));
		set.add(new Member("도연1", 20, "서울"));
		set.add(new Member("도연", 10, "경기"));
		
		System.out.println("저장된 개수 = " + set.size());
	}

	public static void main(String[] args) {
		new MemberSetExam();
	}
}