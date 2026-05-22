package web.mvc;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import web.mvc.domain.Bank;
import web.mvc.exception.BasicException;
import web.mvc.exception.ErrorCode;
import web.mvc.repository.BankRepository;

@SpringBootTest // 통합 테스트 + 자동 커밋
@Slf4j
class Step03TransactionApplicationTests {
    @Autowired
    private BankRepository bankRepository;

    @Test
    @DisplayName("계좌 등록")
    @Transactional
    @Rollback(false)
    void bankInsert() {
        log.info("bankInsert cal...");

        bankRepository.save(Bank.builder().account("A01").balance(1000).build());
        bankRepository.save(Bank.builder().account("A02").balance(500).build());

        log.info("bankInsert end....");
    }

    @Test
    @DisplayName("test")
    @Transactional
    @Rollback(false)
    void test() {
        log.info("test...");
        Bank bank = bankRepository.findById("A01").orElse(null);//readOnly
        System.out.println(bank);
        bank.setBalance(1200); // update

        log.info("bankInsert end....");
    }
}
