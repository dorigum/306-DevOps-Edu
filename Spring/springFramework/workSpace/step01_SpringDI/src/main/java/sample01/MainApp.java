package sample01;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainApp {

	public static void main(String[] args) {
//		System.out.println("---기존 방식---");
//
//		MessageBean bean = new MessageBeanEnImpl();
//		bean.sayHello("doyeon");
//
//		bean = new MessageBeanKoImpl();
//		bean.sayHello("도연");

		System.out.println("----Spring Container가 bean 관리하기----");

		/*
		 * Spring Ioc Container의 ApplicationContext는 BeanFactory를 상속 받은 것으로 객체들의 생성, 의존
		 * 관계 주입, 생명 주기를 관리한다.(다국어 지원도 가능, 부가 기능 더 있다.)
		 * 
		 * Spring Ioc Container는 객체를 사전 초기화 하기 때문에 scope="singleton" 으로 관리된다. 동일한 id로
		 * getBean을 여러 번 해도 항상 동일한 객체를 사용한다.
		 * 
		 * 만약 bean 설정에서 scope="prototype" 설정하면 객체를 미리 만들지 않고, 필요할 때(getBean)마다 새로운 객체를
		 * 생성해준다.
		 */
		ApplicationContext application = new ClassPathXmlApplicationContext("sample01/applicationContext.xml");

		System.out.println("-----------------------------------");

		MessageBean bean = application.getBean("en", MessageBean.class);
		bean.sayHello("doyeon");

		bean = application.getBean("ko", MessageBean.class);
		bean.sayHello("도연");

		MessageBean bean2 = application.getBean("ko", MessageBean.class);
		bean2.sayHello("미미");

		System.out.println("bean = " + bean);
		System.out.println("bean2 = " + bean2);
	}

}
