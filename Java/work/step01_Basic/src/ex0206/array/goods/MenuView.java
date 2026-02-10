package ex0206.array.goods;

import java.util.Scanner;
/**
 키보드 입력을 받아 각 기능을 호출해줄 View
*/
public class MenuView {
	private Scanner sc = new Scanner(System.in);
	private GoodsService service = new GoodsService(); // 전역 변수 초기화

    // 전체 메뉴를 출력하는 메소드
	public void printMenu(String[][] data) {
		// 전달받은 초기 데이터를 서비스에 전달해서 배열에 저장한다.
		service.init(data);
	
		while(true) {
		  System.out.println("----------------------------------------------------------------------------");
		  System.out.println("1.등록      2.전체검색      3.상품코드검색      4. 수정하기      5. 삭제      9.종료");
		  System.out.println("----------------------------------------------------------------------------");
		  System.out.print("메뉴 선택 > ");
		  
		  // 메뉴 번호 입력 시, 숫자가 아닌 문자로 잘못 입력될 경우를 대비한 예외처리 추가
		  try {
				// 여기서 문자열을 숫자로 바꿀 때 에러가 날 수 있으니 try로 감싸기
			int menuChoice = Integer.parseInt(sc.nextLine());
		  
			switch(menuChoice) {
				case 1 : this.inputInsert(); break; // 1. 등록
				case 2 : // 2. 전체검색
					Goods[] goodsArr = service.selectAll(); 
					EndView.printSelectAll(goodsArr);
					break;
				case 3 : this.inputSelectByCode(); break; // 3. 상품코드검색
				case 4 : this.inputUpdate(); break; // 4.수정하기
				case 5 : this.deleteCode(); break; // 5. 삭제
				case 9 : // 9. 종료
					System.out.println("다음에 또 이용해주세요. 프로그램을 종료합니다.");
					System.exit(0);
					break;
					
				// 1~5, 9 이외의 숫자를 입력했을 때
				default: System.out.println("메뉴는 1~5번 / 종료는 9를 입력해주세요.");
			} // switch 문
			
		} catch (NumberFormatException e) {
			// ★숫자가 아닌 '문자'를 입력해서 에러가 나면 다음과 같은 메세지 출력
			System.out.println("메뉴는 1~5번 / 종료는 9를 입력해주세요.");
		} // switch 문
	} // while 문
} // printMenu 끝
	
	public void inputInsert() {
		// 키보드 입력 4개 받기
		System.out.print("상품 코드 > ");
		String code = sc.nextLine();
		
		System.out.print("상품 이름 > ");
		String name = sc.nextLine();
		
		System.out.print("상품 가격 > ");
		int price = Integer.parseInt(sc.nextLine());
		
		System.out.print("상품 설명 > ");
		String explain = sc.nextLine();

		// ★입력받은 정보로 Goods 객체를 조립
		Goods goods = new Goods();
		goods.setCode(code);
		goods.setName(name);
		goods.setPrice(price);
		goods.setExplain(explain);
		
		// 조립한 goods 넘겨주기
		int result = service.insert(goods);
		
		if(result == -1)
			EndView.printMessage("더 이상 상품을 등록할 수 없습니다.");
		
		else if(result == 0)
			EndView.printMessage(code + "는 중복 상품이므로 등록할 수 없습니다.");
		
		else
			EndView.printMessage("상품이 등록되었습니다.");
	   }


	// 상품코드 검색 키보드 입력
	public void inputSelectByCode() {
		 System.out.print("검색하려는 상품 코드 > ");
		 String code = sc.nextLine();
	 
		 Goods goods = service.selectByCode(code);

		 if(goods == null) {
			 EndView.printMessage(code+"는 잘못된 상품 코드이므로 검색할 수 없습니다.");
		 } else {
			 EndView.printSelectByCode(goods);
		 }
	 }


	// 수정하기 키보드 입력
	public void inputUpdate() {
		// 키보드 입력 4개 받기
		System.out.print("수정하려는 상품 코드 > ");
		String code = sc.nextLine();
		
		// 존재하는 상품 코드인지 먼저 확인(selectByCode 재활용)
		Goods target = service.selectByCode(code);
		
		if(target == null) {
			EndView.printMessage("수정 실패: 존재하지 않는 코드입니다.");
			return;
		}
		
		// 상품 코드가 존재한다면
		System.out.print("변경 상품 이름 > ");
		String name = sc.nextLine();

		System.out.print("변경 상품 가격 > ");
		int price = Integer.parseInt(sc.nextLine());

		System.out.print("변경 상품 설명 > ");
		String explain = sc.nextLine();
		
		// 입력받은 정보로 Goods 객체 만들기
		Goods goods = new Goods();
		goods.setCode(code);
		goods.setName(name);
		goods.setPrice(price);
		goods.setExplain(explain);
		
		// 서비스에게 수정 요청
		if(service.update(goods)){
			EndView.printMessage("상품이 수정되었습니다.");
		} else {
			EndView.printMessage("상품 수정 실패(코드를 확인하세요.)");
		}
	}
	
	// 삭제하기
	public void deleteCode() {
		System.out.print("삭제할 상품 코드 > ");
		String code = sc.nextLine();
		
		int result = service.delete(code);
		
		if(result == 1) {
			EndView.printMessage("상품이 삭제되었습니다.");
		} else {
			EndView.printMessage("삭제 실패: 존재하지 않는 코드입니다.");
		}
	}
} // 클래스 끝