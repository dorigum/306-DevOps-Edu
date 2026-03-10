package ex0224;

// 숫자를 100번 출력하는 스레드 - 상속
class NumberThread extends Thread {
	int sum; // 총합
	
	public NumberThread(String name) {
		super(name);
	}
	
	// 스레드로 동작할 기능을 작성
	@Override
	public void run() {
		for(int i=0; i<=100; i++) {
			System.out.println(super.getName() + " -> " + i);
			sum += i; // 누적
			
//			Thread.yield(); // 스레드 양보
			try {
				Thread.sleep(10); // 0.01초 정지 상태 -> 1초가 지나면 Runnable 상태가 된다.
			
			} catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println(super.getName() + "-----END-----");
	}
}

// -----------------------------------------------------------------
// 알파벳을 출력하는 스레드 - 구현
class AlphaThread implements Runnable {
	@Override
	public void run() {
		Thread th = Thread.currentThread(); // 현재 스레드 객체
		
		for(char ch = 'A'; ch <= 'z'; ch++) {
			System.out.println(th.getName() + " -> " + ch);
			
//			Thread.yield(); // 스레드 양보
			try {
				Thread.sleep((int) (Math.random() * 100)); // 0.01초 정지 상태 -> 1초가 지나면 Runnable 상태가 된다.
			
			} catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		System.out.println("-----END-----");
	}
}

//-----------------------------------------------------------------
public class ThreadExam {
	public static void main(String[] args) {
		System.out.println("-----메인 시작합니다.-----");
		
		NumberThread th1 = new NumberThread("첫번째 Thread");
		NumberThread th2 = new NumberThread("두번째 Thread");	
		
		Thread th3 = new Thread(new AlphaThread(), "세번째 Thread");
		
		// 멀티스레드로 동작하기 위해서는 run()을 직접 호출하지 않는다.
//		th1.run();
//		th2.run();
//		th3.run();
			
		th1.start();
		th2.start();
		th3.start();
		
		/*
		 * try { 
		 * th1.join(); // th1 스레드가 일을 마무리할 때까지 메인 스레드는 정지 상태
		 * } catch(InterruptedException e) {
		 * e.printStackTrace();
		 * }
		 */	 
		System.out.println("총합 = " + th1.sum);		
		System.out.println("-----메인 종료합니다.-----");
	}
}