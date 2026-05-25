package web.mvc.service;

import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.mvc.domain.FreeBoard;
import web.mvc.exception.BasicException;
import web.mvc.exception.ErrorCode;
import web.mvc.repository.FreeBoardRepository;

@Service
@RequiredArgsConstructor
@Transactional
public class FreeBoardServiceImpl implements FreeBoardService {
    private final FreeBoardRepository freeBoardRepository;

    @Override
    @Transactional(readOnly = true)
    public List<FreeBoard> selectAll() {
        return freeBoardRepository.findAll(Sort.by(Sort.Direction.DESC, "bno"));
    }

    @Override
    @Transactional(readOnly = true)
    public Page<FreeBoard> selectAll(Pageable pageable) {
        return freeBoardRepository.findAll(pageable);
    }

    @Override
    public void insert(FreeBoard board) {
        freeBoardRepository.save(board);
    }

    @Override
    public FreeBoard selectBy(Long bno, boolean state) {
        FreeBoard board = freeBoardRepository.findById(bno)
                .orElseThrow(() -> new BasicException(ErrorCode.FAILED_DETAIL));

        if (state) {
            board.setReadnum(board.getReadnum() + 1);
        }

        return board;
    }

    @Override
    public FreeBoard update(FreeBoard board) {
        FreeBoard dbBoard = freeBoardRepository.findById(board.getBno())
                .orElseThrow(() -> new BasicException(ErrorCode.FAILED_UPDATE));

        if (!dbBoard.getPassword().equals(board.getPassword())) {
            throw new BasicException(ErrorCode.WRONG_PASS);
        }

        dbBoard.setSubject(board.getSubject());
        dbBoard.setContent(board.getContent());

        return dbBoard;
    }

    @Override
    public void delete(Long bno, String password) {
        FreeBoard dbBoard = freeBoardRepository.findById(bno)
                .orElseThrow(() -> new BasicException(ErrorCode.FAILED_DELETE));

        if (!dbBoard.getPassword().equals(password)) {
            throw new BasicException(ErrorCode.WRONG_PASS);
        }

        freeBoardRepository.delete(dbBoard);
    }
}
