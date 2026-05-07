package sample06;

import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class EmpController {
	/*
	 * byType을 기준으로 객체를 찾아서 주입 -> 만약 type이 같은 객체가 여러 개라면
	 * byName이 기준이 된다. (id와 property = field 명이 같아야 함)
	 * */
	@Autowired
	private EmpDTO empDto;
	
	@Autowired
	@Qualifier("dto2") // id가 dto2인 객체를 찾아서 empDto2에 주입
	private EmpDTO empDto2;

	@Autowired // set, 생성자 필요 X(xml에서도 간결)
	private EmpService empService;

	public EmpController() {
		System.out.println("EmpController 기본 생성자입니다.");

		System.out.println("empDto = " + empDto);
		System.out.println("empService = " + empService);
	}

	/*
	 * 주입은 객체가 생성된 후에 주입이 된다!
	 * 
	 * @PostConstruct: 생성과 주입이 완료된 후에 해야할 일이 있을 때
	 * 메소드 위에 선언하면 자동으로 호출된다!
	 * 
	 * <dependency> <groupId>javax.annotation</groupId>
	 * <artifactId>javax.annotation-api</artifactId>
	 * <version>1.3.2</version>
	 * </dependency>
	 * ->pom.xml 에 추가해야한다!
	 */
	@PostConstruct
	public void init() {
		System.out.println("----init 호출----");
		System.out.println("empDto = " + empDto + ", " + empDto.getEmpno());
		System.out.println("empDto2 = " + empDto2 + ", " + empDto2.getEmpno());
		System.out.println("empService = " + empService);
	}

	public void test() {
		System.out.println("EmpController test call");

		empService.test(empDto);
	}
}
