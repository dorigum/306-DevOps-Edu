package web.mvc.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import web.mvc.domain.FreeBoard;

import java.util.List;
import java.util.Optional;

public interface FreeBoardRepository extends JpaRepository<FreeBoard, Long>,
        QuerydslPredicateExecutor<FreeBoard> {

    // JPQL 문법으로 직접 조인하기
    @Query("select f from FreeBoard f left join  f.repliesList")
    List<FreeBoard> join00();

    // join fetch
    @Query("select f from FreeBoard f left join fetch f.repliesList")
    List<FreeBoard> join01();

    // fetch join을 EntityGraph
    /*
     *  1) attributePaths = "repliesList": FreeBoard.repliesList를 함께 로딩
     *  2) type:
     *     - FETCH: 지정한 속성은 즉시 로딩, 나머지는 지연(default 무시)
     *     - LOAD(자주 씀): 지정한 속성만 즉시 로딩으로 "추가" (기본 페치전략 존중)
     */
    // EntityGraph는 JPA가 제공하는 어노테이션 기반의 즉시 로딩 설정 방법
    // @EntityGraph(attributePaths = "repliesList", type = EntityGraph.EntityGraphType.LOAD)
    @EntityGraph(attributePaths = "repliesList")
    @Query("select f from FreeBoard f")
    List<FreeBoard> join02();

    // ----------------------------------------------------------------
    // 페이징 처리와 함께 fetch 조인
    @Query("select f from FreeBoard f left join fetch f.repliesList")
    Page<FreeBoard> join04(Pageable page);

    // ----------------------------------------------------------------
    // fetch join + countquery 함께 사용
    @Query(value = "select distinct f from FreeBoard f  left join fetch f.repliesList",
            countQuery = "select count(distinct f.bno) from FreeBoard f left join  f.repliesList")
    Page<FreeBoard> join05(Pageable page);
}