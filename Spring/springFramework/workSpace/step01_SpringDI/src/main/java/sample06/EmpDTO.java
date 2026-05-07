package sample06;

import org.springframework.beans.factory.annotation.Value;

public class EmpDTO {
//	@Value("50") // set, 생성자 필요 X(xml에서도 간결)
	private int empno;

	@Value("도연")
	private String ename;

	@Value("${job}") // 외부의 환경 변수 | properties 파일에 있는 key
	private String job;

//	public EmpDTO() {
//		System.out.println("---EmpDTO 기본 생성자 호출---");
//	}

	public void setEmpno(int empno) {
		this.empno = empno;
	}

	public int getEmpno() {
		return empno;
	}

//	@Override
//	public String toString() {
//		return "EmpDTO [empno=" + empno + ", ename=" + ename + ", job=" + job + "]";
//	}
}