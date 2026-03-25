package WS_04.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import WS_04.dto.AccountDto;
import WS_04.dto.UserDto;

public class BankService {
	private List<UserDto> userList;
	private List<AccountDto> accountList;

	public BankService() {
		userList = new ArrayList<>();
		accountList = new ArrayList<>();

		userList.add(new UserDto(100, "구도연", "kkamang03@gmail.com", "010-5612-5447", false));
		userList.add(new UserDto(200, "이효리", "lee@daum.net", "010-2222-3333", false));
		userList.add(new UserDto(300, "송중기", "song@naver.com", "010-5554-2222", false));
		userList.add(new UserDto(400, "삼순이", "sam@daum.com", "010-2222-2222", false));

		accountList.add(new AccountDto(1, "1111-11111", 1000000, 100));
		accountList.add(new AccountDto(2, "2222-22222", 250000, 100));
		accountList.add(new AccountDto(3, "3333-33333", 350000, 100));
		accountList.add(new AccountDto(4, "4444-44444", 150000, 200));
		accountList.add(new AccountDto(5, "5555-55555", 250000, 200));
		accountList.add(new AccountDto(6, "6666-66666", 350000, 300));

		System.out.println("-----세팅완료!!-----");
	} // 생성자 끝

	// ------------------------------------------------------
	// 1) 특정 사용자의 계좌 목록을 배열로 리턴하는 메소드를 작성한다.
	// 특정 사용자의 계좌 목록을 List로 리턴
	public List<AccountDto> getAccountList(int userSeq) {
		List<AccountDto> searchList = new ArrayList<>();
		
		// 향상된 for문으로 List를 순회
		for (AccountDto account : accountList) {
			if (account.getUserSeq() == userSeq) {
				searchList.add(account);
			}
		}
		return searchList.isEmpty() ? null : searchList;
	}

	// 2) 특정 사용자의 고객 정보를 리턴
	public UserDto getUserDetail(int userSeq) {
		for (UserDto user : userList) {
			if (user.getUserSeq() == userSeq) {
				return user;
			}
		}
		return null;
	}

	// 추가 사항------------------------------------------------------
	// 3) 신규로, 모든 계좌 목록을 리턴하는 메소드를 작성한다.
	// 모든 계좌 목록 리턴
	public List<AccountDto> getAccountList() {
		return accountList;
	}

	// 4) 신규로, 잔고 기준으로 정렬한 모든 계좌 목록을 리턴 하는 메소드를 작성한다. 
	// 잔고 기준으로 정렬한 모든 계좌 목록 리턴(AccountDto의 Comparable 이용)
	public List<AccountDto> getAccountListSortByBalance() {
		// 원본 리스트가 섞이지 않도록 복사본 만들기
		List<AccountDto> sortedList = new ArrayList<AccountDto>(accountList);	
		Collections.sort(sortedList); // AccountDto에 정의된 compareTo 기준(잔고)으로 자동 정렬
		
		return sortedList;
	}

	// 5) 신규로, 사용자 일련번호 기준으로 정렬한 모든 계좌 목록을 리턴하는 메소드를 작성한다.
	// 만약 사용자 일련번호 같으면 잔액을 기준으로 내림차순!
	// : Comparator Interface를 이용한다.
	// 사용자 일련번호 기준으로 정렬한 모든 계좌 목록 리턴
	public List<AccountDto> getAccountListSortByUserSeq() {
		List<AccountDto> sortedList = new ArrayList<AccountDto>(accountList);
		
		// 익명 클래스를 사용하여 즉석에서 Comparator 구현(UserSeq 기준)
		Collections.sort(sortedList, (a, b) -> a.getUserSeq() == b.getUserSeq() ?
						 b.getBalance() - a.getBalance() : a.getUserSeq() - b.getUserSeq());
		
		return sortedList;
	}
}