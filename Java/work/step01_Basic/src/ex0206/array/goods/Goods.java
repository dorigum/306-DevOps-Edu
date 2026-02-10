package ex0206.array.goods;

// 상품의 속성을 관리하는 객체
public class Goods{
	private String code; // 상품코드 null
	private String name; // 상품이름  null
	private int price; // 가격 0 
	private String explain; // 설명 null
	
	// set()
	public void setCode(String code) {
		this.code = code;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setPrice(int price) {
		if(price > -1)
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