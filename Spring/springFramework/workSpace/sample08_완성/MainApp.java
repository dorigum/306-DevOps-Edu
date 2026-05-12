package sample08;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainApp {
	public static void main(String[] args) {
		ApplicationContext context =
				new ClassPathXmlApplicationContext("sample08/springDI.xml");
		
		BookController con =
				context.getBean("con" , BookController.class);
		con.invoker();

	}

}
