package WS_06;

import java.util.List;
import com.exception.BalanceLackException;
import com.exception.UserAccountNotFoundException;
import com.ws03.dto.AccountDto;

public interface BankService_06 {
    // 2. 기존 BankService 클래스의 메소드들을 선언만 합니다. (구현 X)
    List<AccountDto> getAccountList();
    List<AccountDto> getAccountListSortByBalance();
    List<AccountDto> getAccountListSortByUserSeq();
    
    // 이전에 추가했던 사용자 정의 예외 처리 메소드들도 선언합니다.
    AccountDto getUserAccount(int userSeq, int accountSeq) throws UserAccountNotFoundException;
    int withdraw(int userSeq, int accountSeq, int amount) throws BalanceLackException, UserAccountNotFoundException;
}