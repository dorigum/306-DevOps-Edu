package WS_06;

import java.util.ArrayList;
import java.util.List;
import com.exception.BalanceLackException;
import com.exception.UserAccountNotFoundException;
import com.ws03.dto.*;

// 1. 기존 BankService 클래스는 BankServiceImpl로 클래스 명을 변경하고 BankService Interface를 구현(implements)한다.
public class BankServiceImpl_06 implements BankService_06 {
    private List<UserDto> userList;
    private List<AccountDto> accountList; 

    public BankServiceImpl_06() {
        // ... (처음에 올려주셨던 ArrayList 객체 생성 및 데이터 추가 코드 동일하게 위치) ...
    }

    // 3. 인터페이스를 구현하는 메소드에 @Override를 사용한다.
    @Override
    public List<AccountDto> getAccountList() {
        return accountList;
    }

    @Override
    public List<AccountDto> getAccountListSortByBalance() {
        // (잔액 기준 정렬 로직 구현부)
        return accountList;
    }

    @Override
    public List<AccountDto> getAccountListSortByUserSeq() {
        // (UserSeq 기준 정렬 로직 구현부)
        return accountList;
    }

    @Override
    public AccountDto getUserAccount(int userSeq, int accountSeq) throws UserAccountNotFoundException {
        for (AccountDto account : accountList) {
            if (account.getUserId() == userSeq && account.getAccountId() == accountSeq) {
                return account;
            }
        }
        throw new UserAccountNotFoundException("사용자 또는 계좌를 찾을 수 없습니다.");
    }

    @Override
    public int withdraw(int userSeq, int accountSeq, int amount) throws BalanceLackException, UserAccountNotFoundException {
        AccountDto account = getUserAccount(userSeq, accountSeq);
        
        if (account.getBalance() < amount) {
            throw new BalanceLackException("잔액이 부족합니다.");
        }
        
        account.setBalance(account.getBalance() - amount);
        return account.getBalance();
    }
}