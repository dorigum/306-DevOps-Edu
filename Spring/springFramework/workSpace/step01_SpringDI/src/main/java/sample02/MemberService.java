package sample02;

public class MemberService {
	private MemberDAO memberDao;
	private Member member;

	public MemberService() {
		System.out.println("MemberService 기본 생성자 호출됨");
	}

	public MemberService(MemberDAO memberDao, Member member) {
		System.out.println("MemberService 생성자 memberDao = " + memberDao + ", member" + member);
		this.memberDao = memberDao;
		this.member = member;
	}

	public void selectInsert() {
		System.out.println("MemberService selectInsert call");
		memberDao.insert(member);
	}
}
