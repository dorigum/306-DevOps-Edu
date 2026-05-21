package web.mvc.service;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import web.mvc.entity.Board;
import web.mvc.repository.BoardRepository;

@Service
@RequiredArgsConstructor
public  class BoardServiceImpl implements BoardService {
   private final BoardRepository boardRepository;

   @PostConstruct
   public void init() {
       System.out.println("boardRepository : " + boardRepository);
   }

    @Override
    public void save(Board board) {
       boardRepository.save(board);
    }

    @Override
    public Board findById(Long bno) {
       return boardRepository.findById(1L).orElse(null);
    }
}
