package web.mvc;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import web.mvc.domain.Member;
import web.mvc.repository.MemberRepository;

@Slf4j
@SpringBootTest
class Step03RegisterApplicationTests {
    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    String id = "8253jang";
    @Test
    void contextLoads() {
        log.info("passwordEncoder = {}", passwordEncoder);
    }

    // 비밀번호 암호화 테스트
    @Test
    @DisplayName("암호화 test")
    public void passwordTest() {
        String rawPassword = "8253jang";

        // 비밀번호 인코딩
        String encodedPassword = passwordEncoder.encode(rawPassword); // 평문 -> 암호화
        log.info("encodedPassword = {}", encodedPassword);

        // 비밀번호 매칭 확인
        boolean isPasswordMatch = passwordEncoder.matches(rawPassword, encodedPassword);
        log.info("Password matches = {}", isPasswordMatch);
    }

    // 관리자 등록
    @Test
    @DisplayName("관리자 계정 추가")
    void memberInsert() {
        String encPwd = passwordEncoder.encode("1234"); // 비밀번호 암호화

        memberRepository.save(
                Member.builder()
                        .id("admin")
                        .pwd(encPwd)
                        .role("ROLE_ADMIN")
                        .address("오리역")
                        .name("도연")
                        .build());
    }
}