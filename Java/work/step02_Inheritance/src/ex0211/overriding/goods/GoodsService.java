package ex0211.overriding.goods;

public class GoodsService {
	// GoodsService.java에 생성자 추가
	// init 메소드 제거

	// 1. 필드(속성)
	private Goods[] goodsArr = new Goods[6];
	public static int count;

	// ------------------------------------------------------------------
	public GoodsService(String[][] data) { // MenuView에서 전달받은 2차원 배열의 주소 전달
		for(int i=0; i<data.length; i++) { // 5
			goodsArr[count++] = this.create(data[i]);
		}
	}

	// ------------------------------------------------------------------
	private Goods create(String[] row) {
		Goods goods = new Goods();

		goods.setCode(row[0]);
		goods.setName(row[1]);
		goods.setPrice(Integer.parseInt(row[2]));
		goods.setExplain(row[3]);

		return goods;
	}

	// ------------------------------------------------------------------
	// 상품 등록
	public InsertResult insert(Goods goods) {
		// 1. 배열 길이 체크
		if(goodsArr.length == GoodsService.count) {
			return InsertResult.INSERT_OUTINDEX;
		}

		// 2. 상품 코드 중복 체크
		Goods searchGoods = this.selectByCode(goods.getCode()); // null or 주소 리턴

		if(searchGoods != null) {
			return InsertResult.INSERT_DUPLICATE;
		}

		goodsArr[GoodsService.count++] = goods;
		return InsertResult.INSERT_SUCCESS;
	}

	// ------------------------------------------------------------------
	// 전체 상품 정보 조회
	public Goods[] selectAll() {
		return goodsArr; // 주소값
	}

	// 상품 코드에 해당하는 상품 검색
	// @return: 만약 code에 해당하는 값이 있으면 Goods 리턴 / 없으면 null 리턴
	public Goods selectByCode(String code) {
		for (int i = 0; i < GoodsService.count; i++) {
			Goods goods = goodsArr[i]; // 배열에 있는 주소값

			String c = goods.getCode(); // 저장되어 있는 상품 코드 꺼내기
			String converUpper = c.toUpperCase(); // 저장되어 있는 코드를 대문자로 변환

			if (converUpper.equals(code.toUpperCase())) {
				return goods; // 주소값 리턴
			}
		}
		// 상품이 존재하지 않으면 null 반환
		return null;
	}

	// ------------------------------------------------------------------
	// 상품 코드에 해당하는 가격, 설명 수정하기
	public boolean update(Goods goods) { // 수정하려는 코드, 변경값 - 가격, 설명
		Goods searchGoods = this.selectByCode(goods.getCode());

		if (searchGoods != null) {
			searchGoods.setPrice(goods.getPrice());
			searchGoods.setExplain(goods.getExplain());

			return true; // 수정 완료
		}
		return false;
	}

	// ------------------------------------------------------------------
	// 삭제하려는 상품 코드에 해당하는 배열의 위치(index) 찾기
	// @param code
	// @return
	public int findLocate(String code) {
		for(int i=0; i<count; i++) {
			if(goodsArr[i].getCode().equals(code)) {
				return i;
			}
		}
		return -1; // 못 찾은 경우
	}
	
	// ------------------------------------------------------------------
	// 상품 정보 삭제
	public int delete(String code) {
		int locate = this.findLocate(code);
		
		if(locate == -1) return -1;
		
		goodsArr[locate] = null; // 위치를 null로 초기화
		
		for (int i=locate; i<count-1; i++) { // count = 5-1
			goodsArr[i] = goodsArr[i+1];
		}
		
		goodsArr[--count] = null;
		
		return 1;
	}
}