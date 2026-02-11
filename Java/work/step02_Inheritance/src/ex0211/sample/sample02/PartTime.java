package ex0211.sample.sample02;

public class PartTime extends Employee { // ★ 상속 선언
	// 비정규직에만 해당되는 속성만 선언
    private int timePay;

    public PartTime() { }

    public PartTime(int empNo, String eName, String job, int mgr, String hiredate, String deptName, int timePay) {
    	// ★ 부모 생성자 호출(공통 필드)
    	super(empNo, eName, job, mgr, hiredate, deptName); 
        
    	// 비정규직에만 해당되는 속성
        this.timePay = timePay;
    }

    @Override
    public void message() {
    	// 부모 필드 eName이 private이라서 geteName()으로 가져오기
        System.out.println(geteName() + " 사원은 비정규직입니다.");
    }

    @Override
    public String toString() {
    	// ★ 부모의 toString() 결과 이어 붙이기
        return super.toString() + " | " + timePay;
    }

    // -----------------------------------------------------------------------
    // timePay에 대한 Getter/Setter 추가
	public int getTimePay() {
		return timePay;
	}

	public void setTimePay(int timePay) {
		this.timePay = timePay;
	}
}