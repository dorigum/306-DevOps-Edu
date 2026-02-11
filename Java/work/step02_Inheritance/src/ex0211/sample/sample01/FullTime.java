package ex0211.sample.sample01;

public class FullTime {
    // 1. 필드 선언
    private int empNo;
    private String eName;
    private String job;
    private int mgr;
    private String hiredate;
    private String deptName;
    private int salary; // 정규직 속성
    private int bonus; // 정규직 속성

    // 2. 생성자(모든 필드를 초기화)
    public FullTime(int empNo, String eName, String job, int mgr, String hiredate, String deptName, int salary, int bonus) {
        this.empNo = empNo;
        this.eName = eName;
        this.job = job;
        this.mgr = mgr;
        this.hiredate = hiredate;
        this.deptName = deptName;
        
        this.salary = salary;
        this.bonus = bonus;
    }
    
    // 3. 메소드(message)
    public void message() {
        System.out.println(eName + " 사원은 정규직입니다.");
    }

    // 4. toString(정규직 정보 리턴)
    @Override
    public String toString() {
        return empNo + " | " + eName + " | " + job + " | " + mgr + " | " + hiredate + " | " + deptName + " | " + salary + " | " + bonus;
    }
    
    // -------------------------------------------------------------------------------------------
	// get(), set() 추가
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

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public int getBonus() {
		return bonus;
	}

	public void setBonus(int bonus) {
		this.bonus = bonus;
	}
}