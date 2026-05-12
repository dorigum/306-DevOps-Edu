package sample01;

public class MessageBeanEnImpl implements MessageBean {

	public MessageBeanEnImpl() {
		System.out.println("MessageBeanEnImpl 생성자 호출");
	}

	public void sayHello(String name) {
		System.out.println("Hello~~~" + name);
	}

}
