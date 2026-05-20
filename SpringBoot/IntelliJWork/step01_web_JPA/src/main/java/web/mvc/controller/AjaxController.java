package web.mvc.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
public class AjaxController {
    @GetMapping("ajax")
    public List<String> ajax() {
        log.info("ajax");
        return List.of("A", "B", "C", "D");
    }
}