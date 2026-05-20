package web.mvc;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import web.mvc.repository.BoardRepository;

@SpringBootTest // 통합 테스트(프로젝트 전체를 테스트)
@Slf4j
@RequiredArgsConstructor
class Step01WebJpaApplicationTests {

    @BeforeEach
    public void beforeEach() {
        log.info("BeforeEach ...");
    }

    @AfterEach
    public void afterEach() {
        log.info("AfterEach ...");
    }

    // @Autowired
    private BoardRepository boardRepo;

    @Test
    @DisplayName("기본 test")
    void contextLoads() {
        log.info("기본 test입니다.");
        log.info("boardRepo = " + boardRepo);
    }
}