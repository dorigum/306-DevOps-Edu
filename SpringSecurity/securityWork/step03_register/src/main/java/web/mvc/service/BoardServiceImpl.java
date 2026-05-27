package web.mvc.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.mvc.domain.Board;
import web.mvc.exception.BoardSearchNotException;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class BoardServiceImpl implements BoardService {

    @Transactional(readOnly = true)
    public List<Board> findAllBoard() throws BoardSearchNotException {
        List<Board> list = boardRepository.findAll();

        if (list == null || list.isEmpty())
            throw new BoardSearchNotException("전체 게시물이 없습니다.", "Not Found Board All");

        return list;
    }

    @Transactional
    public Board updateBoard(Long id, Board board) throws DMLException {
        Board boardEntity = boardRepository.findById(id)
                .orElseThrow(() -> new DMLException("글 번호 오류로 수정되지 않았습니다.", "Not Update"));

        boardEntity.setTitle(board.getTitle());
        boardEntity.setMember(board.getMember());

        return boardEntity;
    }
}
