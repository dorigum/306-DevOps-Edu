package kosta.service;

import org.springframework.stereotype.Service;

@Service("target")
public class MemberServiceImpl implements MemberService {

	@Override
	public void memberInsert(String name) {
		System.out.println("MemberServiceImpl memberInsert(String name) 핵심 기능~");

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void memberUpdate() {
		System.out.println("MemberServiceImpl memberUpdate 핵심 기능~");
		
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public int memberDelete(int no) {
		System.out.println("MemberServiceImpl memberDelete 핵심 기능~");
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return 100;
	}

	@Override
	public String select(String keyField, String keyWord) {
		System.out.println("MemberServiceImpl select 핵심 기능~");
		
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		return "즐거운 주말!";
	}

}
