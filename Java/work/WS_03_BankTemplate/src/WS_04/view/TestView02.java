package WS_04.view;

import java.util.List;
import WS_04.dto.AccountDto;
import WS_04.service.BankService;

public class TestView02 {
	public static void main(String[] args) {
		BankService bankService = new BankService();
		
		System.out.println("1. 모든 계좌정보 조회------------------");
		print(bankService.getAccountList());

		System.out.println("2. 잔액기준으로 정렬하기------------------");
		print(bankService.getAccountListSortByBalance());

		System.out.println("3. UserSeq 기준으로 정렬하기------------------");
		print(bankService.getAccountListSortByUserSeq());

		System.out.println("4. 모든 계좌정보 조회------------------");
		print(bankService.getAccountList());
	}

	// 올바른 계좌 목록 출력 메소드
	public static void print(List<AccountDto> accountList) {
		for (AccountDto accountDto : accountList) {
			// 보기 편하게 출력 형식 수정
			System.out.println("계좌번호: " + accountDto.getAccountNumber() + " | 잔고: " + accountDto.getBalance()
								+ " | 고객번호: " + accountDto.getUserSeq());
		}
		System.out.println();
	}
}