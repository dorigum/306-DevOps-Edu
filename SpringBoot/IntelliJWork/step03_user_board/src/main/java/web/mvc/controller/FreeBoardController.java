package web.mvc.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import web.mvc.domain.FreeBoard;
import web.mvc.dto.FreeBoardDTO;
import web.mvc.service.FreeBoardService;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
@Slf4j
public class FreeBoardController {
    private final FreeBoardService freeBoardService;

    private final ModelMapper modelMapper;

    // 페이징 처리 없을 때 전체 목록
    /* @GetMapping("/list")
    public void list(Model model) {
        log.info("board list");
        List<FreeBoard> freeBoardList = freeBoardService.selectAll();

        List<FreeBoardDTO> freeBoardDTOList =
                freeBoardList.stream()
                        .map(freeBoard -> modelMapper.map(freeBoard, FreeBoardDTO.class))
                        .collect(Collectors.toList()); // N+1 문제 발생

//        model.addAttribute("freeList", freeList);
        model.addAttribute("freeList", freeBoardDTOList);
    }
     */

    @GetMapping("/list")
    public String list(Model model) {
        model.addAttribute("freeList", freeBoardService.selectAll());
        return "board/list";
    }

    @GetMapping("/write")
    public String write() {
        return "board/write";
    }

    @PostMapping("/insert")
    public String insert(FreeBoard board) {
        freeBoardService.insert(board);
        return "redirect:/board/list";
    }

    @GetMapping("/read/{bno}")
    public String read(@PathVariable Long bno, Model model) {
        model.addAttribute("board", freeBoardService.selectBy(bno, true));
        return "board/read";
    }

    @PostMapping("/updateForm")
    public String updateForm(Long bno, Model model) {
        model.addAttribute("board", freeBoardService.selectBy(bno, false));
        return "board/update";
    }

    @PostMapping("/update")
    public String update(FreeBoard board) {
        FreeBoard updateBoard = freeBoardService.update(board);
        return "redirect:/board/read/" + updateBoard.getBno();
    }

    @PostMapping("/delete")
    public String delete(Long bno, String password) {
        freeBoardService.delete(bno, password);
        return "redirect:/board/list";
    }
}
