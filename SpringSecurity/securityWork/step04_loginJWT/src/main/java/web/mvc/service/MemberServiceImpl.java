package web.mvc.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.mvc.domain.Member;
import web.mvc.exception.ErrorCode;
import web.mvc.exception.MemberAuthenticationException;
import web.mvc.repository.MemberRepository;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder; // 비밀번호 암호화를 위한 주입

    @Transactional(readOnly = true)
    @Override
    public String duplicateCheck(String id) {
        Member member = memberRepository.duplicateCheck(id);
        System.out.println("member = " + member);

        if (member == null) return "사용 가능합니다!";
        else return "이미 존재하는 아이디입니다.";
    }

    // -------------------------------------------------------------
    @Transactional
    @Override
    public void signUp(Member member) {
        if (memberRepository.existsById(member.getId())) {
            throw new MemberAuthenticationException(ErrorCode.DUPLICATED);
        }

        member.setPwd(passwordEncoder.encode(member.getPwd()));
        member.setRole("ROLE_USER");

        Member m = memberRepository.save(member); // 동일한 ID가 들어오면 수정됨
        log.info("m = {}", m);
    }
}