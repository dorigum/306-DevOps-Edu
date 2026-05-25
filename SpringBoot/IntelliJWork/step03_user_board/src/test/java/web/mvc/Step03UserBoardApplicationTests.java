package web.mvc;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import web.mvc.domain.FreeBoard;
import web.mvc.domain.User;
import web.mvc.repository.FreeBoardRepository;
import web.mvc.repository.UserRepository;

@SpringBootTest
class Step03UserBoardApplicationTests {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FreeBoardRepository freeBoardRepository;

    @Test
    void contextLoads() {
    }

    @Test
    void userInsert() {
        userRepository.save(User.builder().userId("chan").pwd("1234").name("이찬범").build());
        userRepository.save(User.builder().userId("koo").pwd("1234").name("구도연").build());
        userRepository.save(User.builder().userId("lee").pwd("1234").name("이가현").build());
    }

    @Test
    void boardInsert() {
        for (int i = 1; i <= 45; i++) {
            freeBoardRepository.save(FreeBoard.builder()
                    .subject("제목" + i)
                    .writer("User" + i)
                    .readnum(0)
                    .content("내용" + i)
                    .password("1234")
                    .build());
        }
    }
}