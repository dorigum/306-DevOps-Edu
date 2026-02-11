package ex0211.sample.sample02;

public class Employee {
    // 1. 공통 필드(private으로 자식들도 직접 접근 불가 -> getter/setter 필요)
    private int empNo;
    private String eName;
    private String job;
    private int mgr;
    private String hiredate;
    private String deptName;

    // 2. 부모 생성자(자식이 호출해서 초기화)
    public Employee() { } // 기본 생성자

    public Employee(int empNo, String eName, String job, int mgr, String hiredate, String deptName) {
        this.empNo = empNo;
        this.eName = eName;
        this.job = job;
        this.mgr = mgr;
        this.hiredate = hiredate;
        this.deptName = deptName;
    }

    // 3. 메소드
    public void message() {
        System.out.println(eName + " 사원입니다.");
    }

    @Override
    public String toString() {
        return empNo + " | " + eName + " | " + job + " | " + mgr + " | "+ hiredate + " | " + deptName;
    }

    // -------------------------------------------------------------------------------
    // 4. Getter / Setter(필드가 private이라 자식 클래스에서 값 꺼내려면 필요!)
    // 💡우클릭 -> Source -> Generate Getters and Setters -> Select All
	public int getEmpNo() {
		return empNo;
	}

	public void setEmpNo(int empNo) {
		this.empNo = empNo;
	}

	public String geteName() {
		return eName;
	}

	public void seteName(String eName) {
		this.eName = eName;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public int getMgr() {
		return mgr;
	}

	public void setMgr(int mgr) {
		this.mgr = mgr;
	}

	public String getHiredate() {
		return hiredate;
	}

	public void setHiredate(String hiredate) {
		this.hiredate = hiredate;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
}