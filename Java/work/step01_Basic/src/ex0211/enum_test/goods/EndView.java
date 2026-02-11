package ex0211.enum_test.goods;

// 요청 결과를 출력할 view
public class EndView {
	// 성공 여부 메시지를 출력하는 메소드
	public static void printMessage(String message) {
		System.out.println(message + "\n");
	}

	// 전체 검색 결과를 출력하는 메소드
	public static void printSelectAll(Goods[] goodsArr) { // service에 있는 주소가 전달됨
		System.out.println("**********상품 정보 조회하기**********");

		for (int i = 0; i < goodsArr.length; i++) {
			if (goodsArr[i] != null) {
				System.out.print("코드 : " + goodsArr[i].getCode() + " | ");
				System.out.print("이름 : " + goodsArr[i].getName() + " | ");
				System.out.print("가격 : " + goodsArr[i].getPrice() + " | ");
				System.out.println("설명 : " + goodsArr[i].getExplain() + " | ");
			}
		}
	}

	// 상품 코드에 해당하는 상세정보 출력하는 메소드
	public static void printSelectByCode(Goods goods) {
		System.out.println("**********상품 상세 조회**********");

		if (goods != null) {
			System.out.println("상품 코드 : " + goods.getCode());
			System.out.println("상품 이름 : " + goods.getName());
			System.out.println("상품 가격 : " + goods.getPrice());
			System.out.println("상품 설명 : " + goods.getExplain());
		}
	}
}