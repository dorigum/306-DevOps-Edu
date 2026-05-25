package web.mvc.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import web.mvc.domain.FreeBoard;
import web.mvc.service.FreeBoardService;

@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class FreeBoardController {
    private final FreeBoardService freeBoardService;

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
