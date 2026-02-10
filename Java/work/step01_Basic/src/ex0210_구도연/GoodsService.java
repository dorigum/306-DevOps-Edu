package ex0210_구도연;

public class GoodsService {
	// GoodsService.java에 생성자 추가
	// init 메소드 제거
	
	// 1. 필드(속성)
	private Goods[] goodsArr; // 배열 선언만 하고, 크기는 생성자에서 결정
	private int count;

	// ------------------------------------------------------------------
	// 생성자 오버로딩 구현
	// 생성자 1: 기본 생성자(크기를 알려주지 않으면 기본 10개로 만듦)
	public GoodsService() { // 클래스 이름과 동일하게 생성, 리턴 타입 X
		this(10); // 생성자 2를 호출해서 10을 넘김(코드 중복 줄이기)
		System.out.println("[✔Check Point]기본 생성자 호출: 배열 10칸 생성");
	}
	
	// 생성자 2: 크기를 지정하는 생성자(원하는 크기만큼 배열 생성)
	public GoodsService(int size) {
		goodsArr = new Goods[size];
		System.out.println("[✔Check Point]크기 지정 생성자 호출: 배열 " + size + "칸 생성");
	}

	// 생성자 3: 데이터를 받아서 초기화하는 생성자(기존 init 메소드 역할)
	public GoodsService(String[][] data) {
		this(10); // 일단 10칸을 만들고(생성자 1, 2 활용)
		
		for(int i=0; i<data.length; i++) {
			// insert 메소드를 재활용하거나 or 직접 넣거나
			// 여기서는 create 메소드를 활용해서 배열에 넣기
			goodsArr[count++] = this.create(data[i]);
		}
		System.out.println("[✔check Point]데이터 주입 생성자 호출: 초기 데이터 " + count + "개 등록 완료");
	}

	// ------------------------------------------------------------------
	// 메소드: 등록, 수정, 삭제 등의 실제 기능들은 그대로 두기
	// Goods 객체 생성(내부에서만 사용하므로 private)
	private Goods create(String[] row) {
		return new Goods(row[0], row[1], Integer.parseInt(row[2]), row[3]);
	}
	
	// 상품 등록
	public int insert(Goods goods) {
		// 1. 배열 길이 체크
		if(goodsArr.length == count) return -1;

		// 2. 상품 코드 중복 체크
		if(this.selectByCode(goods.getCode()) != null) return 0;

		// 3. 상품 등록 성공
		goodsArr[count++] = goods;
		return 1;
	}

	// 전체 상품 정보 조회
	public Goods[] selectAll() {
		return goodsArr;//
	}

	// 상품 코드에 해당하는 상품 검색
	public Goods selectByCode(String code) {
		for(int i=0; i<count; i++) {
			if(goodsArr[i].getCode().equals(code)) {
				return goodsArr[i];
			}
		}
		// 상품이 존재하지 않으면 null 반환
		return null;
	}

	// 상품 코드에 해당하는 가격, 설명 수정하기
	public boolean update(Goods goods) { // 수정하려는 코드, 변경값 - 가격, 설명
		for(int i=0; i<count; i++) {
			if(goodsArr[i].getCode().equals(goods.getCode())) {
				goodsArr[i].setName(goods.getName());
				goodsArr[i].setPrice(goods.getPrice());
				goodsArr[i].setExplain(goods.getExplain());

				return true; // 수정 완료
			}
		}
		return false;
	}

	// 상품 정보 삭제
	public int delete(String code) {
		int index = -1;
		for(int i=0; i<count; i++) {
			if(goodsArr[i].getCode().equals(code)) {
				index = i;
				break;
			}
		}

		if (index == -1) return -1;

		for (int i=index; i<count-1; i++) {
			goodsArr[i] = goodsArr[i+1];
		}

		count--;
		goodsArr[count] = null;

		return 1;
	}
}