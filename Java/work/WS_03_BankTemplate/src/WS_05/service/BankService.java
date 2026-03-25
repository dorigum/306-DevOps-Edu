package WS_05.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import WS_05.dto.AccountDto;
import WS_05.dto.InstallAccountDto;
import WS_05.dto.LoanAccountDto;
import WS_05.dto.SavingAccountDto;
import WS_05.dto.UserDto;


public class BankService {
	List<UserDto> userList; // null
	List<AccountDto> accountList; // null

	// 생성자에서 테스트를 위한 고객 및 계좌 객체를 생성하고 배열에 저장한다.
	public BankService() {
		// 배열 생성
		userList = new ArrayList<UserDto>(); 
		accountList = new ArrayList<AccountDto>();
			
        userList.add(new UserDto(111, "홍길동", "hong@gildong@com", "010-1111-1111", false) );
        userList.add(new UserDto(222, "이길동", "lee@gildong@com", "010-2222-2222", true) );
        userList.add(new UserDto(333, "삼길동", "sam@gildong@com", "010-3333-3333", false) );
        
        // 다형성(Polymorphism) 적용: AccountDto 타입의 리스트에 다양한 형태의 자식 객체를 추가
        accountList.add(new InstallAccountDto(20, "00200202002002", 1000, 111, 12, 10000));
		
        accountList.add(new SavingAccountDto(10, "00100101001001", 500, 111, 100));
		
        accountList.add(new LoanAccountDto(60, "00600606006006", 500, 333, "House"));
		accountList.add(new LoanAccountDto(30, "00300303003003", 0, 111, "Building"));
		
		accountList.add(new SavingAccountDto(70, "00700707007007", 500, 333, 200));	    
		
		accountList.add(new LoanAccountDto(50, "00500505005005", 200, 222, "Car"));
		
		accountList.add(new SavingAccountDto(40, "00400404004004", 1000, 222, 50));
    
		System.out.println("-----세팅완료!!-----");
	} // 생성자 끝

	// ------------------------------------------------------
	// 1) 특정 사용자의 계좌 목록을 배열로 리턴하는 메소드를 작성한다.
	// 특정 사용자의 계좌 목록을 List로 리턴
	public List<AccountDto> getAccountList(int userSeq) {
		List<AccountDto> searchList = new ArrayList<>();

		// 향상된 for문으로 List를 순회
		for (AccountDto account : accountList) {
			if (account.getUserId() == userSeq) {
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
		Collections.sort(sortedList, (a, b) -> a.getUserId() == b.getUserId() ? b.getBalance() - a.getBalance()
				: a.getUserId() - b.getUserId());

		return sortedList;
	}
}