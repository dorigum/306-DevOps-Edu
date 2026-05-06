package sample02;

public class Member {
	private String id;
	private String pwd;
	private int age;
	private String addr;

	public Member() {
		System.out.println("Member 기본 생성자입니다.");
	}

	public Member(String id) {
		System.out.println("Member(String id) 생성자입니다. id = " + id);
		this.id = id;
	}

	public Member(String id, int age) {
		System.out.println("Member(String id, int age) 생성자입니다.");
		this.id = id;
		this.age = age;
	}

	public Member(String id, String pwd) {
		System.out.println("Member(String id, String pwd) 생성자입니다.");
		this.id = id;
		this.pwd = pwd;
	}

	public Member(String id, String pwd, int age) {
		System.out.println("Member(String id, String pwd, int age) 생성자입니다.");
		this.id = id;
		this.pwd = pwd;
		this.age = age;
	}

	public Member(String id, int age, String pwd, String addr) {
		System.out.println("Member(String id, int age, String pwd, String addr) 생성자입니다.");
		this.id = id;
		this.age = age;
		this.pwd = pwd;
		this.addr = addr;
	}

	@Override
	public String toString() {
		return "Member [id=" + id + ", pwd=" + pwd + ", age=" + age + ", addr=" + addr + "]";
	}
}
