package kosta.service;

public class GoodsService {

	public void insert() {
		System.out.println("GoodsService의 insert() 핵심 기능입니다.");
		
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public String goodsSelect(int code) {
		System.out.println("GoodsService의 goodsSelect(int code)핵심 기능입니다.");
		
		try {
			Thread.sleep(800);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return "hi";
	}

}
