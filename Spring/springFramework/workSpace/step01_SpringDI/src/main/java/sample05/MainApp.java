package sample05;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainApp {

	public static void main(String[] args) {

		ApplicationContext context = new ClassPathXmlApplicationContext("sample05/springDI_Setter.xml");

//		UserBean userBean = application.getBean("userBean", UserBean.class);
//		userBean.getOrderMessage();
		System.out.println("------------------------------------");
		
		OrderMessage order = context.getBean("order", OrderMessage.class);
		order.getOrderMessage();
	}
}
