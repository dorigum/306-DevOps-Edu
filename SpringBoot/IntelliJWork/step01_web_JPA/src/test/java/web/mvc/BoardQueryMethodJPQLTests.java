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

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
@Slf4j
public class BoardQueryMethodJPQLTests {
    @Autowired
    private BoardRepository boardRepository;

    @Test
    @DisplayName("1. 전달된 글 번호보다 큰 레코드 검색")
    public void test() {
        boardRepository
                .findByBnoGreaterThan(150L)
                .forEach(System.out::println);
    }

    @Test
    @DisplayName("2. 전달된 글 번호, 작성자 기준")
    public void test2() {
        boardRepository
                .findBnoLessThanOrTitle(150L, "제목150")
                .forEach(System.out::println);
    }

    @Test
    @DisplayName("JPQL 문법 사용하기(1. 삭제하기)")
    public void test3() {
        boardRepository
                .delGratebybno(10L);
    }

    @Test
    @DisplayName("JPQL 문법 사용하기(2. 조건문 사용?)")
    public void test4() {
        boardRepository
                .delGratebybno(10L);
    }

    @Test
    @DisplayName("JPQL 문법 사용하기(3. 여러 조건 사용)")
    public void test5() {
        boardRepository
                .findByWhere(Board.builder().bno(10L).title("제목2").writer("작성자20").build())
                .forEach(System.out::println);
    }
}
