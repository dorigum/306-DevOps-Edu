package sample05;

public class OrderMessageImpl implements OrderMessage {
	private int orderId;
	private String message;

	public OrderMessageImpl() {
		System.out.println("---OrderMessageImpl constructor call---");
	}

//	public void setUserBean(UserBean userBean) {
//		this.userBean = userBean;
//		System.out.println("setUserBean(UserBean userBean) call");
//	}

	@Override
	public void getOrderMessage() {
		System.out.println("orderId: " + orderId);
		
//		System.out.println("name: " + userBean.getName);
		System.out.println(": " + orderId);
		
	}
}
