package ex0223.io;

import java.util.Scanner;

public class MenuView {
	Scanner sc = new Scanner(System.in);
	SaveProfileController profileController = new SaveProfileController();

	public void printMenu() {
		while (true) {
			System.out.println("------------------------------------------");
			System.out.println("1. 프로필 저장    2. 프로필 불러오기    3. 종료");
			System.out.println("------------------------------------------");
			System.out.println("메뉴 입력 > ");

			// 입력받은 메뉴의 공백 제거
			String menu = sc.nextLine().trim();

			switch (menu) {
			case "1":
				this.inputInsert(); break;
			case "2":
				this.inputSearch();	break;
			case "3":
				System.out.println("프로그램을 종료합니다.");
				System.exit(0); // 프로그램 즉시 종료
			default:
				System.out.println("메뉴를 다시 선택해주세요!!!!");
			}
		}
	}

	// 1번: 프로필 저장 로직
	public void inputInsert() {
		try {
			System.out.print("이름 > ");
			String name = sc.nextLine();

			System.out.print("몸무게 > ");
			int weight = Integer.parseInt(sc.nextLine());

			System.out.print("비밀번호 > ");
			int passwd = Integer.parseInt(sc.nextLine());

			// 컨트롤러로 데이터 넘기기
			profileController.saveProfile(name, weight, passwd);

		} catch (NumberFormatException e) {
			System.out.println("숫자를 정확히 입력해주세요!");
		}
	}

	// 2번: 프로필 불러오기 로직
	public void inputSearch() {
		System.out.print("이름> ");
		String name = sc.nextLine();

		// 컨트롤러에 이름 넘겨서 검색 요청하기
		profileController.loadProfile(name);
	}
}