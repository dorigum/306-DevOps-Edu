package kosta.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import kosta.service.CustomerService;

public class MainApp {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("springAop.xml");
		System.out.println("--------------------");

		CustomerService service = context.getBean("service", CustomerService.class);
		int re = service.select();
		System.out.println("re = " + re);

		System.out.println("**************************");
//		String result = service.selectById("koo");
		String result = service.selectById(null);
		System.out.println("result = " + result);

		System.out.println("**************************");
		service.update("koo", "도연");
	}
}