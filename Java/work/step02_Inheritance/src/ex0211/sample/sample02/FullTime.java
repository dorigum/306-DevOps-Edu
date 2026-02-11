package ex0211.sample.sample02;

public class FullTime extends Employee { // ★ 상속 선언
    // 정규직에만 해당되는 속성만 선언
    private int salary;
    private int bonus;

    public FullTime() { }

    public FullTime(int empNo, String eName, String job, int mgr, String hiredate, String deptName, int salary, int bonus) {
        // ★ 부모 생성자 호출(공통 필드)
        super(empNo, eName, job, mgr, hiredate, deptName); 
        
        // 정규직에만 해당되는 속성
        this.salary = salary;
        this.bonus = bonus;
    }

    @Override
    public void message() {
        // 부모 필드 eName이 private이라서 geteName()으로 가져오기
        System.out.println(geteName() + " 사원은 정규직입니다.");
    }

    @Override
    public String toString() {
        // ★ 부모의 toString() 결과 이어 붙이기
        return super.toString() + " | " + salary + " | " + bonus;
    }
    
    // -----------------------------------------------------------------------
    // salary, bonus에 대한 Getter/Setter 추가
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