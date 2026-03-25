package array.exam;
/**
  상품으로 속성을 관리하는 개체
*/
public class Goods{
	private String code; //상품코드 null
	private String name;//상품이름  null
	private int price;//가격 0 
	private String explain;//설명 null
	
	public Goods(String code, String name, int price, String explain) {
		super();
		this.code = code;
		this.name = name;
		this.price = price;
		this.explain = explain;
	}

	

}