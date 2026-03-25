package ex0224;

public class SynchronizedExam {

	public static void main(String[] args) {
		System.out.println("-----메인 시작합니다!-----");
		Bank bank = new Bank();
		
		// th1, th2가 쓰는 bank는 같은 주소를 공유
		CustomerThread th1 = new CustomerThread(bank, "입금자", true);
		CustomerThread th2 = new CustomerThread(bank, "인출자", false);
		
		th1.start();
		th2.start();
		
		System.out.println("-----메인 종료합니다!-----");
	}
}

// --------------------------------------------------------------
// 여러 스레드가 공유할 객체
// @param: state는 true 이면 입금, false 이면 출금
class Bank {
	int balance; // 잔액
	
	/*
	 * 제한자 synchronized 는 특정 스레드 synchronized 블럭 안에서
	 * 일을 다 마무리할 때까지 다른 스레드가 제어권을	 뺏어 가지 못하도록 하는 것.
	 * synchronized 블럭 안에 있는 스레드가 lock을 가지고 있다고 하고 블럭을 빠져나가면 잠금이 풀린다.
	 * wait(): 현재 running 중인 스레드를 중지상태로 만듦
	 * notify(), notifyAll(): wait()에 의해 중지 상태인 스레드를 runnable 상태로 만듦
	 * 주의 : wait(),notify(), notifyAll()는 반드시 synchronized 블럭 안에서 사용한다.
	 */
	
	public synchronized void balanceChange(String name, boolean state) {
		if(state) { // 입금
			if(balance == 0) {
				System.out.print(name + "------> 현재 잔액: " + balance + " / ");
				balance++;
				System.out.println(name + "------> 증가 후 현재 잔액: " + balance);
//			}
				
			} else {
				System.out.println(name+" 대기중~~~~");
				
				try {
				  wait(); // 현재 스레드를 정지 상태로 변경
				
				} catch(InterruptedException e) {
					e.printStackTrace();
				}
			}
			
			// -------------------------------------------------------------
			} else { // 출금
				if (balance == 1) {
//				synchronized (this) {
					System.out.print(name + "------> 현재 잔액: " + balance + " / ");
					balance--;
					System.out.println(name + "------> 감소 후 현재 잔액: " + balance);
//			}
				} else {
					System.out.println(name + "대기중~~~");

					try {
						wait(); // 현재 스레드를 정지 상태로 변경

					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
			notifyAll(); // wait() 에 의해서 일시정지 중인 스레드를 runnable 상태로 만든다.
		}
	}

// -------------------------------------------------------------
// 입금 or 출금을 하는 스레드 객체
class CustomerThread extends Thread {
	// 여기다가 Bank를 바로 생산하면, 고객마다 Bank가 생기게 됨 -> 공유하지 못함
	// main에서 Bank 생성해서 호출
	Bank bank;
	boolean state;
	
	CustomerThread(Bank bank, String name, boolean state) { // 호출해주기
		super(name);
		
		this.bank = bank;
		this.state = state;
	}
	
	@Override
	public void run() {
		for(int i=0; i<50; i++) {
			bank.balanceChange(super.getName(), state);
		}
		System.out.println(super.getName() + "스레드를 종료합니다:)");
	}
}