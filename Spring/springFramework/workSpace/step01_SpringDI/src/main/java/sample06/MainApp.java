package sample06;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainApp {

	public static void main(String[] args) {
		System.out.println("----시작하기----");

		ApplicationContext application = new ClassPathXmlApplicationContext("sample06/springDI_Autowired.xml");

		System.out.println("----------------");
		EmpController controller = application.getBean("controller", EmpController.class);
		
		controller.test();
	}
}
