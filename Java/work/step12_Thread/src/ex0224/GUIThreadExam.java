package ex0224;

import java.awt.Container;
import java.awt.FlowLayout;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class GUIThreadExam extends JFrame {
	JTextField text1 = new JTextField(5);
	JTextField text2 = new JTextField(5);
	JButton btn = new JButton("눌러봐~~");

	public GUIThreadExam() {
		super("GUIThreadExam 입니다.^^");

		// JFrame의 레이아웃 변경
		super.setLayout(new FlowLayout());

		// Container 위에 Component 추가
		Container con = super.getContentPane();
		con.add(text1);
		con.add(text2);
		con.add(btn);

		// 옵션 설정
		// 창 크기
		super.setSize(500, 400);
		super.setLocationRelativeTo(null); // 정중앙에 놓기

		// 창 보여줘
		super.setVisible(true);

		// x를 클릭했을 때 창 닫기
		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// 시계 Thread 동작
		new NowTimeThread().start();
		
		// textfield에 숫자 출력하기
//		new Thread(new Xxx() {재정의}).start();
		new Thread(() -> {
			for(int i=0; i<=1000; i++) {
				text1.setText(i + "");
				
				try {
					Thread.sleep(50);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}).start();
		
		// 버튼을 클릭했을 때 할 일 작성 - 이벤트 등록하기 -> 이벤트발생주체.addXxxListener(구현);
		btn.addActionListener((e) -> {
//			System.out.println(1111);
			new Thread(() -> {
				for(char ch = 'A'; ch <= 'z'; ch++) {
					text2.setText(ch + "");
					
					try {
					Thread.sleep(100);
					
					} catch(InterruptedException ex) {
						ex.printStackTrace();
					}
				}
			}).start();
		});
	} // 생성자 끝

	// --------------------------------------------------------
	/*
	 * 현재 날짜와 시간 title에 출력하는 스레드
	 */
	public static void main(String[] args) {
		new GUIThreadExam();
	}
	
	// --------------------------------------------------------
	// Inner 클래스로 Outer 클래스의 모든 필드와 메소드 접근 가능
	// 1초마다 동작하는 시계 Thread
	class NowTimeThread extends Thread {
		@Override
		public void run() {
			while(true) {
				// 현재 시간을 가져오기
				Calendar now = Calendar.getInstance();
	
				StringBuilder sb = new StringBuilder();
	
				int y = now.get(Calendar.YEAR);
				int m = now.get(Calendar.MONTH) + 1;
				int d = now.get(Calendar.DATE);
	
				int h = now.get(Calendar.HOUR);
				int min = now.get(Calendar.MINUTE);
				int sec = now.get(Calendar.SECOND);
				
				sb.append(y);
				sb.append("년 ");
				sb.append(m);
				sb.append("월 ");
				sb.append(d);
				sb.append("일 ");
				sb.append(h);
				sb.append(" : ");
				sb.append(min);
				sb.append(" : ");
				sb.append(sec);
			
				now.get(Calendar.SECOND);
			
				// JFrame위 title 변경
				// setTitle("♪♪♪제목 변경 되고 있니↘↗");
				GUIThreadExam.this.setTitle(sb.toString());

				try {
					Thread.sleep(1000); // 1초 대기

				} catch (InterruptedException e) {
					e.printStackTrace();
				} 
			} // 1. while문 닫기
		} // 2. run() 메소드 닫기
	} // 3. NowTimeThread (Inner 클래스) 닫기
} // 4. GUIThreadExam (Outer 클래스) 닫기