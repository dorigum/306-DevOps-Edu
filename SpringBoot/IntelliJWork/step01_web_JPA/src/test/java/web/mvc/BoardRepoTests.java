package web.mvc;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Rollback;
import web.mvc.entity.Board;
import web.mvc.repository.BoardRepository;
import web.mvc.service.BoardService;

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

    /* @Autowired
    private BoardService boardService; */

    @Test
    @DisplayName("기본 test")
    public void test() {
        log.info("board test입니다.");
        log.info("boardRepo = " + boardRepo);
        // log.info("boardService = " + boardService);
    }

    // 등록
    @DisplayName("게시물 등록")
    @Test
    public void test2() {
//        boardRepo.save(Board.builder()
//                .title("제목 1").writer("도연").content("내용")
//                .build());
//
//        boardRepo.save(Board.builder()
//                .title("인텔리제이 어려워ㅠ").writer("순이").content("ㅠㅠ")
//                .build());

        // 한번에 여러 개의 레코드를 등록
//        for (int i = 1; i <= 200; i++)
//            boardRepo.save(Board.builder()
//                    .title("제목" + i).writer("작성자" + i).content("내용" + i)
//                    .build());


        // save 메소드 update 기능 test 하기
        boardRepo.save(Board.builder()
                .bno(210L) // update도 같이 실행
                .title("제목").writer("작성자").content("내용")
                .build());

        log.info("end---------------------------");
    }

    // 전체 검색
    @Test
    @DisplayName("전체 검색")
    public void test3() {
        log.info("전체 검색");
        boardRepo.findAll().forEach(board -> log.info("board = {}", board));
    }

    // PK를 대상으로 검색하기
    @Test
    @DisplayName("PK 검색")
    public void test4() {
        log.info("PK 검색");
        // Board board = boardRepo.findById(100L).orElse(null);
        Board board = boardRepo.findById(100L)
                .orElseThrow(() -> new RuntimeException("100번 글이 존재하지 않습니다."));

        log.info("board = {}", board);
    }

    // PK를 대상으로 수정하기
    @Test
    @DisplayName("PK 대상 수정")
    public void test5() {
        boardRepo.findById(20L).ifPresent(board -> {
            board.setTitle("제목 수정");
            board.setContent("내용 수정");
        });

        log.info("수정 완료!!!");
    }

    // PK를 대상으로 삭제하기
    @Test
    @DisplayName("PK 대상 삭제")
    public void test6() {
        // boardRepo.deleteById(10L);
        boardRepo.findById(20L).ifPresent(board -> boardRepo.delete(board));

        log.info("삭제 완료!!!");
    }

    // 페이징 처리
    @Test
    @DisplayName("페이징 처리")
    public void test7() {
        //Pageable pageable =PageRequest.of(2, 5); // 페이지 번호 0부터 시작
        Pageable pageable = PageRequest.of(0, 10, Sort.by("bno").descending());

        Page<Board> page = boardRepo.findAll(pageable);
        System.out.println("***********************************");
        System.out.println("page.getTotalElements() = "+page.getTotalElements());
        System.out.println("page.getNumber() = "+page.getNumber());
        System.out.println("page.getSize() = "+page.getSize());
        System.out.println("page.getTotalPages() = "+page.getTotalPages());
        System.out.println("page.previousPageable() = "+page.previousPageable());
        System.out.println("page.nextPageable() = "+page.nextPageable());

        System.out.println("page.isFirst() = "+page.isFirst());
        System.out.println("page.isLast() = "+page.isLast());
        System.out.println("page.hasNext() = "+page.hasNext());
        System.out.println("page.hasPrevious() = "+page.hasPrevious());
        System.out.println("***********************************");
        page.getContent().forEach(System.out::println);
    }
}