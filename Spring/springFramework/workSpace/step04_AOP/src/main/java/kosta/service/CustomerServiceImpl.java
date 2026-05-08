package kosta.service;

public class CustomerServiceImpl implements CustomerService {

	@Override
	public int select() {
		System.out.println(getClass().getName() + " select() 핵심 기능입니다.");
		
		return 10;
	}

	@Override
	public String selectById(String id) {
		System.out.println(getClass().getName() + " selectById(String id) 핵심 기능입니다.");

		if (id == null)
			throw new RuntimeException("id를 입력해주세요~~^^");

		return "배고프다";
	}

	@Override
	public void update(String id, String name) {
		System.out.println(getClass().getName() + " update(String id, String name) 핵심 기능입니다.");
	}
}