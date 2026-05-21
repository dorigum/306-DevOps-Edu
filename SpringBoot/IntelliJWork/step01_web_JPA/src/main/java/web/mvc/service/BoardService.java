package web.mvc.service;

import web.mvc.entity.Board;

public interface BoardService {
    /*
     * 등록하기
     */
    void save(Board board);
    /*
     * 조회하기
     */
    Board findById(Long bno);
}