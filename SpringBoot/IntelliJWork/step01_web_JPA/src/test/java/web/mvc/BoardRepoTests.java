package web.mvc;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.test.annotation.Rollback;
import web.mvc.entity.Board;
import web.mvc.repository.BoardRepository;

/*
* @DataJpaTest는
* 기본적으로 내장 DB(H2 설정)를 자동 설정해서 테스트를 수행한다.
* @AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) 설정해서
* ~.properties 설정을 변경하지 않고 설정에 있는 DB를 사용하겠다.
*
* 기본 trasnaction이 설정되어 있고 rollback 처리된다.
*/
//@SpringBootTest // 통합 테스트
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false) // 무조건 Commit
@Slf4j
public class BoardRepoTests {
    @Autowired
    private BoardRepository boardRepo;

    @Test
    public void test() {
        log.info("board test입니다.");
    }

    // 등록
    @DisplayName("게시물 등록")
    @Test
    public void test2() {
        boardRepo.save(Board.builder()
                .title("제목 1").writer("도연").content("내용")
                .build());

        boardRepo.save(Board.builder()
                .title("인텔리제이 어려워ㅠ").writer("순이").content("ㅠㅠ")
                .build());
    }
}