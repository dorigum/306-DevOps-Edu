package ex0220.set;

public class Member {
	private String name;
	private int age;
	private String addr;

	public Member(String name, int age, String addr) {
		super();
		this.name = name;
		this.age = age;
		this.addr = addr;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Member [name = ");
		builder.append(name);
		builder.append(", age = ");
		builder.append(age);
		builder.append(", addr = ");
		builder.append(addr);
		builder.append("]");
		return builder.toString();
	}

	@Override
	public int hashCode() {
		return name.hashCode() + age;
	}

	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Member m) {
			// 이름이 같으면서 + 나이도 같으면 중복 값으로 체크
			if(name.equals(name) && age == m.getAge()) {
				return true; // 중복
			}
		}
		return false;
	}
}