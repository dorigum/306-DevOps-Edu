<<<<<<< HEAD
package ex0210_구도연;

public class ex0210_19 {
	public static void main(String[] args) {
		Account ac = new Account();
		
		ac.setBalance(10000);
		System.out.println("현재 잔고: " + ac.getBalance()); // 현재 잔고: 10000
		
		ac.setBalance(-100); // 음수값일 때
		System.out.println("현재 잔고: " + ac.getBalance()); // 현재 잔고: 10000
		
		ac.setBalance(2000000); // 100만원을 초과할 때
		System.out.println("현재 잔고: " + ac.getBalance()); // 현재 잔고: 10000
	}
=======
package ex0210_구도연;

public class ex0210_19 {
	public static void main(String[] args) {
		Account ac = new Account();
		
		ac.setBalance(10000);
		System.out.println("현재 잔고: " + ac.getBalance()); // 현재 잔고: 10000
		
		ac.setBalance(-100); // 음수값일 때
		System.out.println("현재 잔고: " + ac.getBalance()); // 현재 잔고: 10000
		
		ac.setBalance(2000000); // 100만원을 초과할 때
		System.out.println("현재 잔고: " + ac.getBalance()); // 현재 잔고: 10000
	}
>>>>>>> 34e50e0cbb23c333c91173456266abe4f3590d4f
}