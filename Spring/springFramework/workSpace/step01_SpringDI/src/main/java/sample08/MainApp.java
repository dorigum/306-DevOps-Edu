package sample08;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainApp {
	public static void main(String[] args) {
		ApplicationContext application = new ClassPathXmlApplicationContext("sample08/applicationContext.xml");

		BookController bc = application.getBean("bookController", BookController.class);
		bc.invoker();
	}
}