package ex0224;

public class DaemonThreadExam03 {

	public static void main(String[] args) {
		System.out.println("*****메인 시작합니다.*****");
		
		Thread th1 = new Thread(() -> {
			while (true) {
				System.out.println("곧 집에 간다~~~~");
				
				try {
					Thread.sleep(500);

				} catch (InterruptedException e) {
					e.printStackTrace();

				}
			}
		});

		th1.setDaemon(true); // 메인 스레드가 종료될 때 th1 스레드도 함께 종료된다.
		
		th1.start();

		try {
			Thread.sleep(3000);

		} catch (Exception e) {
			e.printStackTrace();

		}

		System.out.println("*****메인 종료합니다.*****");
	}
}