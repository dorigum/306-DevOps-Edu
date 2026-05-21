package web.mvc;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import web.mvc.dto.BoardDTO;
import web.mvc.entity.Board;
import web.mvc.entity.QBoard;
import web.mvc.repository.BoardRepository;

import java.time.LocalDateTime;
import java.util.List;

import static web.mvc.entity.QBoard.board;

@SpringBootTest // 통합 테스트
@Slf4j
public class BoardQueryDSLTests {
    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private JPAQueryFactory jpaQueryFactory;

    @Test
    @DisplayName("QueryDSLTest")
    public void test() {
        log.info("boardRepository: {}", boardRepository); // springDataJPA
        log.info("jpaRepository: {}", jpaQueryFactory); // QueryDSL
    }

    // 검색
    @Test
    @DisplayName("querydsl_검색")
    public void test1() {
        QBoard qBoard = board;

        List<Board> list = jpaQueryFactory.selectFrom(board).fetch();
        System.out.println("-------------------------------");
        list.forEach(System.out::println);
    }

    @Test
    @DisplayName("querydsl_검색 조건")
    public void test2() {
        QBoard board = QBoard.board;

        List<Board> list =
                jpaQueryFactory
                        .selectFrom(board)
                        .where(board.bno.lt(30L).or(board.title.eq("제목80")))
                        .fetch();
        System.out.println("---------------");
        list.forEach(System.out::println);
    }

    /*
     * 삭제
     * jakarta.persistence.TransactionRequiredException: Executing an update/delete query
     * jpaQueryFactory를 이용해서 DML작업 할때는 반드시 @Transaction을 처리 해야한다.
     */
    @Test
    @DisplayName("querydsl_삭제")
    @Transactional
    @Rollback(false)
    public void test3() {
        QBoard board = QBoard.board;
        long re = jpaQueryFactory
                .delete(board)
                .where(board.bno.gt(130L))
                .execute(); // 리턴 long은 수행된 결과 레코드 수!
        System.out.println("---------------");
        System.out.println("re = " + re);
    }

    /*
     * 수정
     * jakarta.persistence.TransactionRequiredException: Executing an update/delete query
     * jpaQueryFactory를 이용해서 DML작업 할때는 반드시 @Transaction을 처리 해야한다.
     */
    @Test
    @DisplayName("querydsl_수정")
    @Transactional
    @Rollback(false)
    public void test4() {
        QBoard board = QBoard.board;
        long re = jpaQueryFactory
                .update(board)
                .set(board.writer, "이쁜이")
                .set(board.title, "수정되나?")
                .set(board.updatedAt, LocalDateTime.now()) // 직접 수정시간 설정
                .where(board.bno.eq(11L))
                .execute();
        System.out.println("---------------");
        System.out.println("re = " + re);
    }

    // -----------------------------------------------------------------
    // QuerydslPredicateExecutor<> 사용하기
    /*
     * interface에 QuerydslPredicateExecutor<> 상속받는다.
     *   - QuerydslPredicateExecutor안에서 제공하는 메소드를 사용해서 자바중심으로 조건(Predicate)을 만들수 있다.
     *   -Spring Data JPA + QueryDSL을 접목한 형태로 Repository에서 바로 QueryDSL의 `Predicate`를 실행할 수 있도록 지원한다.
     *   - JPAQueryFactory 없이 간단하게 Predicate로 해결
     *   ex)  ~.findAll(Predicate p)
     *
     *   참고 : https://www.notion.so/QuerydslPredicateExecutor-T-208a7a6c42ce80b290bde247b33a49ef?pvs=12
     */
    @Test
    public void queryDSL05() {
        QBoard board = QBoard.board;

        // BooleanBuilder는 Querydsl에서 동적 쿼리를 만들 때 조건(where절)을 유연하게 조립하기 위한 도우미 객체
        BooleanBuilder builder = new BooleanBuilder();

        // 1)
        builder.and(board.bno.eq(9L)); // b1_0.bno=?
        builder.or(board.title.like("%User1%"));

        // 2) insert_date between ? and ?
        /*
        LocalDateTime from = LocalDateTime.of(2025, 11, 1,0,0,0);
        LocalDateTime to = LocalDateTime.of( 2025, 11, 11,12,0,0);
        builder.and(board.createdAt.between(from, to)); //insert_date between ? and ?
        */

        // 3)
        //builder.and(board.writer.eq("user1")); // 대소문자 구분한다.

        // 4)
        //builder.and(board.writer.equalsIgnoreCase("user9")); // 대소문자 구분 안한다.

        // 5)
        //builder.and(board.writer.toUpperCase().eq("user9".toUpperCase()));

        // 6)
        //builder.and(board.writer.toUpperCase().eq("user9".toUpperCase())).or(board.bno.gt(140L));

        Iterable<Board> it = boardRepository.findAll(builder);

        // Iterable 를 List형태로 변환 하고 싶다.
        List<Board> list = Lists.newArrayList(it);

        list.forEach(System.out::println);
    }

    // ------------------------------------------------------------------
    /*
     * QueryDSL의 Projections
     * : QueryDSL의 Projections는 쿼리 결과를 DTO에 매핑할 때 사용하는 도구
     * : 참고 - https://www.notion.so/QueryDSL-Projections-208a7a6c42ce803cb746d4bfce586ee1
     */
    @Test
    public void translateDTO() {
        QBoard board = QBoard.board;
        List<BoardDTO> list =
                jpaQueryFactory
                        .select(
                                Projections.fields(
                                        BoardDTO.class,
                                        board.bno,
                                        board.title,
                                        board.writer,
                                        board.content,
                                        board.createdAt,
                                        board.updatedAt)
                        )
                        .from(board)
                        .fetch();

        list.forEach(b -> System.out.println(b));
    }
}