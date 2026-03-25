package ex0219.exam02;

public class MainApp {
	public static void main(String[] args) {
		// 상품 생성
		Product<Tv, String> p1 = new Product<>();
		
		p1.setKind(new Tv());
		p1.setModel("삼성 TV");
		
		Tv tv = p1.getKind();
		
		// ---------------------------------------------
		// 제네릭 타입은 기본형 사용 X(ex: int, String...)
		Product<Video, Integer> p2 = new Product<>();
		
		p2.setKind(new Video());
		p2.setModel(100); // AutoBoxing
		
		Video v = p2.getKind();
		int it = p2.getModel(); // UnBoxing
	}
}