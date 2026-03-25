package WS_04.view;

import java.util.List;
import WS_04.dto.AccountDto;
import WS_04.dto.UserDto;
import WS_04.service.BankService;

public class TestView {
	public static void main(String[] args) {
		System.out.println("*****1. 고객 seq 100에 해당하는 계좌정보 검색하기*****");
		BankService service = new BankService();

		// 배열이 아닌 List로 받기(100번 고객으로 테스트)
		List<AccountDto> accountList = service.getAccountList(100);

		if (accountList == null || accountList.isEmpty()) {
			System.out.println("회원의 계좌 정보가 없습니다.");
			
		} else {
			// .length 대신 .size() 사용
			System.out.println("회원 계좌 개수 : " + accountList.size());
			
			for (AccountDto dto : accountList) {
				System.out.println(dto.getAccountSeq() + " , " + dto.getAccountNumber() + " , " + dto.getUserSeq());
			}
		}

		System.out.println("\n\n*****2. userSeq에 해당하는 고객의 정보*****");
		UserDto userDto = service.getUserDetail(300); // 300번 고객으로 테스트

		if (userDto != null) {
			System.out.print(userDto.getUserSeq() + " , " + userDto.getName() + " , " + userDto.getEmail() + " , ");
			System.out.println(userDto.getPhone() + " , " + userDto.isSleep());
			
		} else {
			System.out.println("회원의 정보가 없습니다.");
		}
	}
}