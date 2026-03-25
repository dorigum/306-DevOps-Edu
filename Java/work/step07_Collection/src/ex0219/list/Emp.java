package ex0219.list;

// 사원 정보
public class Emp implements Comparable<Emp> {
	private int empno;
	private String ename;
	private int age;
	private String addr;

	public Emp() { }

	public Emp(int empno, String ename, int age, String addr) {
		super();
		this.empno = empno;
		this.ename = ename;
		this.age = age;
		this.addr = addr;
	}

	// ---------------------------------------------------------
	public int getEmpno() {
		return empno;
	}

	public void setEmpno(int empno) {
		this.empno = empno;
	}

	public String getEname() {
		return ename;
	}

	public void setEname(String ename) {
		this.ename = ename;
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

	// ---------------------------------------------------------
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Emp [empno = ");
		builder.append(empno);
		builder.append(", ename = ");
		builder.append(ename);
		builder.append(", age = ");
		builder.append(age);
		builder.append(", addr = ");
		builder.append(addr);
		builder.append("]");

		return builder.toString();
	}

	@Override
	public int compareTo(Emp o) {
//		return empno - o.getEmpno(); // 오름차순
		return o.getEmpno() - empno; // 내림차순
	}
}