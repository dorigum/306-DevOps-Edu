package ex0213.shop;

public class Shop {
	public void shopAge(int age) throws ShopAgeException {
		if(age > 18) {
			System.out.println(age + "살: 입장하신 걸 환영합니다.");
			/*
			 * // 발생할 객체 생성 ShopAgeException e = new ShopAgeException();
			 */

		} else {
			throw new ShopAgeException();
			
		}
	}
}