package sample06;

public class EmpService {

	public EmpService() {
		System.out.println("EmpService 기본 생성자");
	}

	public void test(EmpDTO empDto) {
		System.out.println("EmpService의 test 호출됨!!");
		System.out.println("empDTO = " + empDto);
	}
}
