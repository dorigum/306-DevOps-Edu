package mvc.view;

import java.util.Scanner;

import mvc.controller.ElectronicsController;
import mvc.dto.Electronics;


// 사용자의 요청을 키보드로 입력받는 클래스
public class MenuView {
	Scanner sc = new Scanner(System.in);
	
	ElectronicsController controller = new ElectronicsController(); // 전역변수 초기화, 생성자 호출

	// 전체 메뉴를 출력하는 메소드
	public void printMenu() {
		while (true) {
			System.out.println("----------------------------------------------------------------------------------");
			System.out.println("1. 전체검색    2. 모델번호검색    3.등록    4. 수정    5. 삭제    6.가격기준정렬    9. 종료");
			System.out.println("----------------------------------------------------------------------------------");
			System.out.print("메뉴선택 > ");
			String menu = sc.nextLine();
			
			switch (menu) {
			case "1":
				controller.selectAll(); break;

			case "2":
				this.inputSearch(); break;

			case "3":
				this.inputInsert(); break;

			case "4":
				this.inputUpdate(); break;

			case "5":
				this.inputDelete(); break;

			case "6":
				controller.selectSortByPrice();
				break;

			case "9":
				System.out.println("프로그램을 종료합니다.");
				System.exit(0);

			default:
				System.out.println("메뉴를 다시 선택해주세요!!!!");
			} // switch문 끝
		} // while문 끝
	} // 메소드끝

	// ----------------------------------------------------------------------
	// 등록할 때 키보드 입력을 처리하는 메소드
	public void inputInsert() {
		try {
			System.out.print("모델 번호는? ");
			int modelNo = Integer.parseInt(sc.nextLine());

			System.out.print("모델 이름은? ");
			String modelName = sc.nextLine();

			System.out.print("모델 가격은? ");
			int modelPrice = Integer.parseInt(sc.nextLine());

			System.out.print("모델 설명은? ");
			String modelDetail = sc.nextLine();

			controller.insert(new Electronics(modelNo, modelName, modelPrice, modelDetail));

		} catch (NumberFormatException e) {
			System.out.println("숫자를 입력해야 하는 항목에 문자가 입력되었습니다. 다시 시도해주세요.");
		}
	}

	// ----------------------------------------------------------------------
	// 모델번호에 해당하는 전자제품을 검색하기 위해서 모델번호를 키보드로 입력받아 처리하는 메소드
	public void inputSearch() {
		try {
			System.out.print("찾을 전자제품 모델번호는? ");
			int modelNo = Integer.parseInt(sc.nextLine());
			
			controller.searchByModelNo(modelNo);

		} catch (NumberFormatException e) {
			System.out.println("숫자를 입력해야 하는 항목에 문자가 입력되었습니다. 다시 시도해주세요.");
		}
	}

	// ----------------------------------------------------------------------
	// 모델번호에 해당하는 전자제품 정보(설명)을 수정하기 위해 키보드로 입력받아 처리하는 메소드
	public void inputUpdate() {
		try {
			System.out.print("수정하려는 전자제품 모델번호는? ");
			int modelNo = Integer.parseInt(sc.nextLine());

			System.out.print("변경하려는 모델 설명은? ");
			String modelDetail = sc.nextLine();

			controller.update(new Electronics(modelNo, modelDetail));

		} catch (NumberFormatException e) {
			System.out.println("업데이트 오류");
		}
	}
	
	// ----------------------------------------------------------------------
	// 모델번호에 해당하는 전자제품 정보를 삭제하기 위해서 모델번호를 키보드로 입력받아 처리하는 메소드
	public void inputDelete() {
		try {
			System.out.print("삭제할 전자제품 모델번호는? ");
			int modelNo = Integer.parseInt(sc.nextLine());
			
			controller.deleteModelNo(modelNo);
 
		} catch (NumberFormatException e) {
			System.out.println("삭제 오류");
		}
	}
}