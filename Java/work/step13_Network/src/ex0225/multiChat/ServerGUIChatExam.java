package ex0225.multiChat;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

// 접속한 클라이언트들을 관리하기 위한 서버
public class ServerGUIChatExam {
	Socket sk;
	List<ClientSkThread> list = new ArrayList<>();

	public ServerGUIChatExam() {
		try (ServerSocket server = new ServerSocket(8002)) {

			while (true) {
				System.out.println("클라이언트 접속 대기 중입니다🤗");
				sk = server.accept();

				System.out.println(sk.getInetAddress() + "님이 접속하셨습니다!");

				ClientSkThread th = new ClientSkThread();
				list.add(th);
				th.start();

				System.out.println("현재 접속 인원: " + list.size() + "명\n");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	} // 생성자 끝
	
	// -------------------------------------------------------------
	// 접속한 클라이언트에게 메시지를 전송하는 메소드
	public void sendMessage(String message) {
		for(ClientSkThread th : list) {
			th.pw.println(message);
		}
	}

	// Inner class-------------------------------------------------------------
	class ClientSkThread extends Thread {
		PrintWriter pw;
		BufferedReader br;
		String nickName; // 대화명

		ClientSkThread() {
			try {
				pw = new PrintWriter(sk.getOutputStream(), true);
				br = new BufferedReader(new InputStreamReader(sk.getInputStream()));

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		@Override
		public void run() {
			// 클라이언트가 보내온 내용을 읽어서, 접속한 모든 클라이언트에게 전송
			try {
				// -----------------------------------------------------------------
				// [대화명 중복 체크]---------------------------------------------------
				while(true) {
					String checkName = br.readLine();
					boolean isDuplicate = false;
					
					for(ClientSkThread client : list) {
						if(client != this && client.nickName != null && client.nickName.equals(checkName)) {
							isDuplicate = true;
							break;
						}
					}
					
					if(isDuplicate) {
						pw.println(checkName + "은(는) 중복된 대화명입니다.");
						
					} else {
						pw.println(checkName);
						nickName = checkName;
						break;
					}
				}
				
				// -------------------------------------------------------------
//				nickName = br.readLine(); // 대화명 저장(처음 채팅방에 입장할 때)
				
				sendMessage("[" + nickName + "]님이 입장하셨습니다!"); // 내가 채팅방에 입장할 때 최초로 한번만 띄워질 메시지
				
				while(true) {
					String inputData = br.readLine();
					sendMessage("[" + nickName + "]" + inputData); // 채팅방에 다른 사람이 입장할 때마다 띄워질 메시지
				}
				
			} catch (Exception e) {
//				e.printStackTrace();
				// 현재 스레드에 문제가 생김
				// -> 현재 스레드를 list 에서 제거
				list.remove(this); // (서버 입장에서) 내가 가지고 있는 스레드에 문제가 생겨서 this로 제거
				
				// 남아 있는 클라이언트에게 알림
				sendMessage("[" + nickName + "]님이 퇴장하셨습니다.");
				
				// 서버 콘솔에 남아있는 인원 수 출력
				System.out.println("현재 접속 인원: " + list.size() + "명");
			}
		}
	}

	// -------------------------------------------------------------
	public static void main(String[] args) {
		new ServerGUIChatExam();
	}

}
