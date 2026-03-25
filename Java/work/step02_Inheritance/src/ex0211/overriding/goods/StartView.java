package ex0211.overriding.goods;

public class  StartView{
	public static void main(String[] args) {
       String[][] data = new String[][] {
    	   {"A01", "새우깡", "2500", "짜고 맛나다."},	// ---> Goods 
    	   {"A02", "고구마깡", "3500", "고구마맛이고 달다."},
    	   {"A03", "감자깡", "5000", "감자맛에 고소한맛."},
    	   {"A04", "허니버터칩", "2200", "달콤하다."},
    	   {"A05", "콘칩", "3000" , "고소하다."}
    	};
    	
    	System.out.println("*****프로그램 시작합니다.*****");

    	MenuView mv = new MenuView(data); // 전역 변수 초기화
    	mv.printMenu();
	}
}