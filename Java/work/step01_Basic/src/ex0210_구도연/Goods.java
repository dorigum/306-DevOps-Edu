package ex0210_구도연;

// Goods.java에 생성자 추가
// 생성자 Overloading
public class Goods {
	private String code; // 상품 코드 null
	private String name; // 상품 이름 null
	private int price; // 가격 0
	private String explain; // 설명 null
	
	// 1. 기본 생성자(필수!!)
	// 이걸 안 만들면, new Goods() 했을 때 에러가 날 수 있음!!!
	public Goods() {
		
	}

	// 2. 필드 생성자(파라미터를 모두 받는 생성자) -> ★생성자 오버로딩★
	// 객체를 만들면서 동시에 값을 넣을 수 있게 해줌
	public Goods(String code, String name, int price, String explain) {
		this.code = code;
		this.name = name;
		this.price = price;
		this.explain = explain;
	}
	
	// -----------------------------------------------------------------
	// set()
	public void setCode(String code) {
		this.code = code;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPrice(int price) {
		if (price > -1)
			this.price = price;
	}

	public void setExplain(String explain) {
		this.explain = explain;
	}

	// -----------------------------------------------------------------
	// get()
	public String getCode() {
		return code;
	}

	public String getName() {
		return name;
	}

	public int getPrice() {
		return price;
	}

	public String getExplain() {
		return explain;
	}
}