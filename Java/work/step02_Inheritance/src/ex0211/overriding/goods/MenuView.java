package ex0211.overriding.goods;

import java.util.Scanner;

public class MenuView {
	private Scanner sc = new Scanner(System.in);
	private GoodsService service; // 전역 변수 초기화

	public MenuView(String[][] data) {
		service = new GoodsService(data);
	}

	// 전체 메뉴를 출력하는 메소드
	public void printMenu() { // 메인에서 전달한 2차원 배열의 주소값 전달
		
		while (true) {
			System.out.println("----------------------------------------------------------------------------");
			System.out.println("1.등록      2.전체검색      3.상품코드검색      4. 수정하기      5. 삭제      9.종료");
			System.out.println("----------------------------------------------------------------------------");
			System.out.print("메뉴 선택 > ");

			int menuChoice = Integer.parseInt(sc.nextLine());
			
			switch(menuChoice) {
            	case 1 :
            		this.inputInsert();
            		break;
            		
        		case 2 :
        			Goods[] goodsArr = service.selectAll();
        			EndView.printSelectAll(goodsArr);
        			break;
        			
        		case 3 :
        			this.inputSelectByCode();
        			break;
        			
    			case 4 :
    				this.inputUpdate();
    				break;
    				
				case 5 :
					this.inputDeleteCode();
					break;
					
				case 9 :
					System.out.println("다음에 또 이용해주세요. 프로그램을 종료합니다.");
					System.exit(0); // 프로그램 종료 메소드
					break;
					
				default: System.out.println("메뉴는 1~4 or 9만 입력해주세요.");
			} // switch 문
		} // while 문
	} // printMenu 끝

	// -----------------------------------------------------------------
	// 키보드 입력 4개 받기
	public void inputInsert() {
		System.out.print("상품 코드 > ");
		String code = sc.nextLine();
		
		System.out.print("상품 이름 > ");
		String name = sc.nextLine();
		
		System.out.print("상품 가격 > ");
		int price = Integer.parseInt(sc.nextLine());
		
		System.out.print("상품 설명 > ");
		String explain = sc.nextLine();

		// ★입력받은 정보로 Goods 객체를 조립
		Goods goods = new Goods(code, name, price, explain);
		
		// 조립한 goods 넘겨주기
		InsertResult result = service.insert(goods);
		
//		if(result == InsertResult.INSERT_OUTINDEX)
//			EndView.printMessage("더 이상 상품을 등록할 수 없습니다.");
//		
//		else if(result == 0)
//			EndView.printMessage(code + "는 중복 상품이므로 등록할 수 없습니다.");
//		
//		else
//			EndView.printMessage("상품이 등록되었습니다.");
//	   }

		switch(result) {
			case INSERT_OUTINDEX : EndView.printMessage("더 이상 등록할 수 없습니다.");
				break;
			case INSERT_DUPLICATE : EndView.printMessage(code + "는 중복이므로 등록할 수 없습니다.");
				break;
				
		default : EndView.printMessage("등록되었습니다.");
		}
	}
	
	// 상품코드 검색 키보드 입력
	public void inputSelectByCode() {
		System.out.print("검색하려는 상품 코드 > ");
		String code = sc.nextLine();

		Goods goods = service.selectByCode(code);

		if(goods == null) {
			EndView.printMessage(code + "는 잘못된 상품 코드이므로 검색할 수 없습니다.");
		} else {
			EndView.printSelectByCode(goods);
		}
	}

	// 수정하기 키보드 입력
	public void inputUpdate() {
		// 키보드 입력 4개 받기
		// 존재하는 상품 코드인지 먼저 확인(selectByCode 재활용)
		System.out.print("수정하려는 상품 코드 > ");
		String code = sc.nextLine();
		
		// 상품 코드가 존재한다면
		System.out.print("변경 상품 이름 > ");
		String name = sc.nextLine();

		System.out.print("변경 상품 가격 > ");
		int price = Integer.parseInt(sc.nextLine());
		
		System.out.print("변경 상품 설명 > ");
		String explain = sc.nextLine();
		
		// -----------------------------------------------------------
		// 입력받은 정보로 Goods 객체 만들기
		Goods goods = new Goods(code, name, price, explain);
		boolean re = service.update(goods);
		
		// 서비스에게 수정 요청
		if(re) {
			EndView.printMessage("상품이 수정되었습니다.");
		} else {
			EndView.printMessage("상품 수정 실패(코드를 확인하세요.)");
		}
	} // 메소드 끝

	// -------------------------------------------------------
	// 상품 코드로 현재 위치 찾기 입력
	// 삭제하기
	public void inputDeleteCode() {
		System.out.print("삭제할 상품 코드 > ");
		String code = sc.nextLine();

		if (service.delete(code) == -1) {
			EndView.printMessage(code + "삭제 실패: 존재하지 않는 코드입니다.");
		} else {
			EndView.printMessage(code + " 상품 코드가 삭제되었습니다.");
		}
	}
} // 클래스 끝