package ex0224;

public class InterruptedExam01 {

	public static void main(String[] args) {
		System.out.println("*****메인 시작합니다.*****");

		Thread th1 = new Thread(() -> {
			try {
				while (true) {
					System.out.println("곧 집에 간다~~~~");
					Thread.sleep(1000); // 0.001초
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		});

		th1.start();

		try {
			Thread.sleep(3000); // 메인 스레드 3초 정리

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// 3초 후에 th1 스레드를 멈추게 하고 싶다!!!
		th1.interrupt();
		// InterruptedException 발생하려면
		// Thread.sleep(1000); < 잠시라도 일시정지 상태가 되어야만 정지가 가능하다.
		
		
		System.out.println("*****메인 종료합니다.*****");
	}
}
