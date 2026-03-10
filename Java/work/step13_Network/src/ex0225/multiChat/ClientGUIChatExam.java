package ex0225.multiChat;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ClientGUIChatExam extends JFrame {

	JTextArea textArea = new JTextArea();
	JTextField textField = new JTextField();
	JScrollPane scrollPane = new JScrollPane(textArea); // 메시지가 계속 들어올 때, 스크롤이 자동으로 내려가는 기능

	Socket sk;
	PrintWriter pw;
	BufferedReader br;

	public ClientGUIChatExam() {
		super("채팅 프로그램");

		Container con = getContentPane();

		// 컴포넌트 추가
		con.add(textField, BorderLayout.SOUTH);
		con.add(scrollPane, BorderLayout.CENTER);

		// 옵션 설정
		textArea.setFocusable(false); // 커서 놓기 안됨(채팅이 올라오는 컨테이너에 글이 써지는 것을 막음)
		textArea.setBackground(Color.pink);

		// 창 크기
		setSize(500, 400);

		// 정중앙에 놓기
		setLocationRelativeTo(null);

		// 보여줘
		setVisible(true);

		// x 버튼을 클릭했을 때 프로그램 종료
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// 서버 연결
		connection();

		// ------------------------------------------------------------------
		// 이벤트 처리(JTextField에 값을 입력하고 enter 했을 때 서버에 데이터 전송)
		textField.addActionListener((e) -> {
			// 입력한 내용을 서버에 전송
			pw.println(textField.getText());
			
			// textField를 지움
			textField.setText("");
		});
		
		// ------------------------------------------------------------------
		// 서버가 보내온 데이터를 받아서 JTextArea에 추가하는 스레드
		new Thread(() -> { // 소켓에 대한 정보들이 들어와 있는 상태(connect)
			try {
				while(true) {
					String data = br.readLine();
					textArea.append(data + "\n"); // append(): 데이터 이어쓰기
					
					// 옵션
					textArea.setCaretPosition(textArea.getText().length());
				}
				
			} catch(Exception e) {
				e.printStackTrace();
				
			}
		}).start();
	} // 생성자 끝

	/*
	 * 서버 접속 요청 메소드
	 */
	public void connection() {
		try {		
			sk = new Socket("127.0.0.1", 8002);
			br = new BufferedReader(new InputStreamReader(sk.getInputStream()));
			pw = new PrintWriter(sk.getOutputStream(), true);

			// -------------------------------------------------------------------
			// 대화명 입력 창을 띄운다.
			// JOptionPane.showInputDialog(): 메인 화면(부모)에서 알림창(자식)을 띄움(알림을 끄지 않으면 메인이 클릭되지 않음)
			// 별도의 팝업창 띄우는 방식 X
			
			// -------------------------------------------------------------------
			// [대화명 중복 체크]-----------------------------------------------------
			String name = "";

			while(true) {
				name = JOptionPane.showInputDialog(this, "대화명을 입력하세요.");

				pw.println(name);
				
				String checkName = br.readLine(); // 서버의 대답 읽어오기 

				if (name.equals(checkName)) { // 내가 보낸 이름(name)과 서버가 돌려준 이름(checkName)이 똑같다면? -> 통과!
					break;
				
				} else  {
					JOptionPane.showMessageDialog(this, name + "은(는) 이미 사용 중인 대화명입니다. 다른 이름을 입력해주세요.", "경고", JOptionPane.ERROR_MESSAGE); 
				}
			}
			
			// -------------------------------------------------------------------
//			pw.println(name);

			setTitle("[" + name + "]");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		new ClientGUIChatExam();
	}

}
