package ex0223.exam;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class SaveProfileController {

	// 1. 프로필 저장 메소드
	public void saveProfile(String name, int weight, int passwd) {
		// "이름.txt" 파일 객체 생성
		File file = new File("src/ex0223/io/name" + ".txt");

		try {
			// createNewFile(): 파일이 없어서 새로 만들면 true, 이미 존재하면 false 반환
			if (file.createNewFile()) {
				// 파일 쓰기용 스트림 (문자 기반)
				FileWriter fw = new FileWriter(file);

				// "몸무게:비밀번호" 형태로 저장
				fw.write(weight + ":" + passwd);
				fw.close(); // 다 썼으면 꼭 닫아주기!

				System.out.println("프로필 저장완료1");
			} else {
				// 이미 해당 이름의 파일이 존재하는 경우
				System.out.println("이미 존재하므로 다시 입력하세요.");
			}
			
		} catch (IOException e) {
			System.out.println("파일 저장 중 오류가 발생했습니다.");
			e.printStackTrace();
		}
	}

	// 2. 프로필 불러오기 메소드
	public void loadProfile(String name) {
		File file = new File(name + ".txt");

		// 파일이 존재하지 않을 때
		if (!file.exists()) {
			System.out.println(name + "에 해당하는 정보는 없습니다.");
			return; // 더 이상 진행하지 않고 메소드 종료
		}

		// 파일이 존재할 때 읽어오기
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			// 파일 안에 한 줄밖에 없으므로 한 번만 읽으면 됩니다.
			String line = br.readLine();

			if (line != null) {
				// ":" 기준으로 문자열 쪼개기
				String[] data = line.split(":");

				// data[0]은 몸무게, data[1]은 비밀번호
				System.out.println(name + "님 몸무게는 " + data[0] + "kg 이고 비번은 " + data[1] + " 입니다.");
			}
			
		} catch (IOException e) {
			System.out.println("파일 읽기 중 오류가 발생했습니다.");
			e.printStackTrace();
		}
	}
}