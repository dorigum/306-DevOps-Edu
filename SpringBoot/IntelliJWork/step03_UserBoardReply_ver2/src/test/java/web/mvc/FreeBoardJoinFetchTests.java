package web.mvc;


import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import web.mvc.domain.FreeBoard;
import web.mvc.repository.FreeBoardRepository;

import java.util.List;

@SpringBootTest
//@DataJpaTest
//@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
@Transactional
public class FreeBoardJoinFetchTests {

    @Autowired
    private FreeBoardRepository freeRep;

    /*
     * FreeBoard에서 Reply의 관계는  @OneToMany이므로 FetchType.LAZY(지연로딩) 이다.
     * 먼저, FreeBoard 검색을 한 다음,
     * b.getReplyList().size() 요청으로 인해
     * 각 부모글의 글의 개수만큼 reply테이블의 select을 실행한다 - 성능이슈(많은 select요청)
     *  :해결 방안
     *     join fetch 사용
     */

    @Test
    @DisplayName("findAll()사용")
    void findAll() {
        List<FreeBoard> list = freeRep.findAll(); // FreeBoard 레코드 수 만큼 reply select (1 + N)
        System.out.println("list.size = " + list.size());
//      System.out.println("댓글....");
//      list.forEach(b -> System.out.println(b.getBno() + " = " + b.getRepliesList().size()));
    }

    // --------------------------------------------------------
    // 직접 조인 쿼리를 작성했으나, N+1 문제는 여전히 존재
    @Test
    @DisplayName("직접 조인 join00 호출 사용")
    void join00() {
        List<FreeBoard> list = freeRep.join00(); // FreeBoard 레코드 수 만큼 reply select (1 + N)
        System.out.println("list.size = " + list.size());
        System.out.println("댓글....");
        list.forEach(b -> System.out.println(b.getBno() + " = " + b.getRepliesList().size()));
    }

    // --------------------------------------------------------
    // join fetch 사용
    @Test
    @DisplayName("조인 fetch join01호출 사용")
    void join01() {
        List<FreeBoard> list = freeRep.join01(); // FreeBoard 레코드 수 만큼 reply select (1 + N)
        System.out.println("list.size = " + list.size());
        System.out.println("댓글....");
        list.forEach(b -> System.out.println(b.getBno() + " = " + b.getRepliesList().size()));
    }

    // --------------------------------------------------------
    // EntityGraph 사용
    @Test
    @DisplayName("EntityGraph join02호출 사용")
    void join02() {
        List<FreeBoard> list = freeRep.join02(); // FreeBoard 레코드 수 만큼 reply select (1 + N)
        System.out.println("list.size = " + list.size());
        System.out.println("댓글....");
        list.forEach(b -> System.out.println(b.getBno() + " = " + b.getRepliesList().size()));
    }

    // --------------------------------------------------------
    /*
     * JPQL join fetch에서 페이징 처리 해보자
     */
    @Test
    @DisplayName("join fetch, Page 리턴 사용")
    // 댓글 하나씩은 매칭, 나머지는 count
    // 45번 댓글 3개 중 2개, 44번 댓글 2개 중 1개 count: 48개
    void join04() {
        Pageable pageable =
                PageRequest.of(0, 5, Sort.Direction.DESC, "bno");
        //Page<FreeBoard> page = freeRep.findAll(pageable);
        Page<FreeBoard> page = freeRep.join04(pageable);

        System.out.println("***************************");
        System.out.println("page.getNumber() = " + page.getNumber());
        System.out.println("page.getSize() = " + page.getSize());
        System.out.println("page.getTotalPages() = " + page.getTotalPages());
        System.out.println("page.previousPageable() = " + page.previousPageable());
        System.out.println("page.nextPageable() = " + page.nextPageable());

        System.out.println("page.isFirst() = " + page.isFirst());
        System.out.println("page.isLast() = " + page.isLast());

        System.out.println("page.hasPrevious() = " + page.hasPrevious());
        System.out.println("page.hasNext() = " + page.hasNext());
        System.out.println("*****************************************");

        System.out.println("list.size = " + page.getContent().size());
        //list.forEach(b->System.out.println(b.getBno() +" = " + b.getReplyList().size()));

        page.getContent().forEach(b -> {
            System.out.println(b.getBno() + " | " + b.getSubject());
            b.getRepliesList().forEach(r -> {
                System.out.println("====> " + r.getRno() + " | " + r.getContent() + " | " + r.getRno());
            });
            System.out.println();
        });
    }


    /*
     * JPQL문법을 이용하여
     * join fetch + 페이징 처리 쿼리
     *     @Query(value = "select distinct f from FreeBoard f  left join fetch f.repliesList",
     *     countQuery = "select count(distinct f.bno) from FreeBoard f left join f.repliesList" )
     *     Page<FreeBoard> join05(Pageable page);
     */
    @Test
    @DisplayName("join fetch + countQuery")
    // 45개 출력
    void join05() {
        Pageable pageable =
                PageRequest.of(0, 5, Sort.Direction.DESC, "bno");
        // countQuery적용
        Page<FreeBoard> page = freeRep.join05(pageable);

        System.out.println("***************************");
        System.out.println("page.getNumber() = " + page.getNumber());
        System.out.println("page.getSize() = " + page.getSize());
        System.out.println("page.getTotalPages() = " + page.getTotalPages());
        System.out.println("page.previousPageable() = " + page.previousPageable());
        System.out.println("page.nextPageable() = " + page.nextPageable());

        System.out.println("page.isFirst() = " + page.isFirst());
        System.out.println("page.isLast() = " + page.isLast());

        System.out.println("page.hasPrevious() = " + page.hasPrevious());
        System.out.println("page.hasNext() = " + page.hasNext());
        System.out.println("*****************************************");

        System.out.println("list.size = " + page.getContent().size());
        //list.forEach(b->System.out.println(b.getBno() +" = " + b.getReplyList().size()));

        page.getContent().forEach(b -> {
            System.out.println(b.getBno() + " | " + b.getSubject());
            b.getRepliesList().forEach(r -> {
                System.out.println("====> " + r.getRno() + " | " + r.getContent() + " | " + r.getRno());
            });
            System.out.println();
        });
    }
} // 클래스 끝