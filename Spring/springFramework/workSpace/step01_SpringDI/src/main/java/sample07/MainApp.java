package sample07;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainApp {
	public static void main(String[] args) {
		ApplicationContext application = new ClassPathXmlApplicationContext("sample07/applicationContext.xml");

		System.out.println("-----------------------------");
		
		BoardController con = application.getBean("controller", BoardController.class);
		con.test();
	}
}
