package sample02;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainApp {

	public static void main(String[] args) {
//		System.out.println("---기존 방식---");
//
//		MemberDAO dao = new MemberDAO();
//		Member member = new Member("koo", "1234", 10, "서울");
//
//		MemberService service = new MemberService(dao, member);
//		service.selectInsert();

		System.out.println("----Spring Container 이용하기----");

		ApplicationContext application = new ClassPathXmlApplicationContext("sample02/springDI_Constructor.xml");

		System.out.println("---호출해보자---");
		MemberService service = application.getBean("service", MemberService.class);
		service.selectInsert();
	}
}
