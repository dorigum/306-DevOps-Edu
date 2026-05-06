package sample02;

public class MemberDAO {
	public MemberDAO() {
		System.out.println("MemberDAO 기본 생성자");
	}

	public void insert(Member member) {
		System.out.println("MemberDAO의 insert call");
		System.out.println("member = " + member);

	}
}
