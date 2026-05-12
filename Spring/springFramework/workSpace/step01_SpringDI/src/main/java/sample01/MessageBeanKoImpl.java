package sample01;

public class MessageBeanKoImpl implements MessageBean {

	public MessageBeanKoImpl() {
		System.out.println("MessageBeanKoImpl 생성자 호출");
	}

	public void sayHello(String name) {
		System.out.println(name + "님 반가워요:D");
	}

}
