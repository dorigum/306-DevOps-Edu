package ex0213.shop;

import java.util.Random;

public class MainShop {
	public static void main(String[] args) {
		System.out.println("*****SHOPPINGMALL OPEN*****");
		Shop sp = new Shop();
	
		Random r = new Random();
		
		for (int i=0; i<10; i++) {
			// int shopAge = (int)(Math.random() * 55) + 1;
			int age = r.nextInt(55) + 1;
			
			try {
				sp.shopAge(age);

			} catch(ShopAgeException e) {
				System.out.println(age + "살: " + e.getMessage());
			
			}
		}
			System.out.println("*****SHOPPINGMALL CLOSE*****");
			
			// 예외 처리된 사람
			System.out.println("예외 처리된 사람: " + ShopAgeException.count + " 명");
	}
}