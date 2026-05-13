package sample08_2;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainApp {
	public static void main(String[] args) {
		ApplicationContext context =
				new ClassPathXmlApplicationContext("sample08_2/applicationContext.xml");

		BookController con =
				context.getBean("con", BookController.class);
		con.invoker();
	}
}
