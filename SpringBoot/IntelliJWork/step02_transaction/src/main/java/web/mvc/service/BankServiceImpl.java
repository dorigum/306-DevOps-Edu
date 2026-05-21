package web.mvc.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.mvc.domain.Bank;
import web.mvc.dto.RequestTransferDTO;
import web.mvc.exception.BasicException;
import web.mvc.exception.ErrorCode;
import web.mvc.repository.BankRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
//@Transactional(rollbackFor = Exception.class)
public class BankServiceImpl implements  BankService{

    private final BankRepository bankRepository; //Spring Data JPA구현객체를 생성해서 주입

//    @Transactional(rollbackFor = BasicException.class)
    @Transactional
    @Override
    public int transfer(RequestTransferDTO requestTransferDTO) throws BasicException {
        // 출금 계좌에서 금액 만큼 인출하기 -
       Bank outBank = bankRepository.findById(requestTransferDTO.getOutAccount())
                .orElseThrow(()->new BasicException(ErrorCode.FAILED_WITHDRAWAL_ACCOUNT));

        outBank.setBalance(outBank.getBalance()-requestTransferDTO.getAmount());//update

        // 입금 계좌에 금액만큼 입금하기
        // 영속성 Context: 외부 DB로 가지 않아도 데이터가 조회가 되기 때문에 성능이 좋아짐
       Bank intBank = bankRepository.findById(requestTransferDTO.getInAccount())
                .orElseThrow(()->new BasicException(ErrorCode.FAILED_DEPOSIT_ACCOUNT));

        intBank.setBalance(intBank.getBalance() + requestTransferDTO.getAmount());//update

        // 잔액 확인
        if(intBank.getBalance() > 1000){
            throw new BasicException(ErrorCode.FAILED_MAXIMUM);
        }

        return 1;
    }

    // 검색
    @Transactional(readOnly = true)
    public List<Bank> findAll(){
        return bankRepository.findAll();
    }
}