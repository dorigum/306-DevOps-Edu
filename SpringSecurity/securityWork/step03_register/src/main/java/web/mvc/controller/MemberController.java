package web.mvc.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import web.mvc.domain.Member;
import web.mvc.service.MemberService;

@RestController
@Slf4j
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/index")
    public String index() {
        log.info("index 요청됨");
        return "Spring security Setting 완료!";
    }

    // 아이디 중복 체크
    @GetMapping("/members/{id}")
    public String duplicateIdCheck(@PathVariable String id) {
        log.info("id : {}", id);
        return memberService.duplicateCheck(id);
    }

    // 회원 가입
    @PostMapping("members")
    public String signUp(@RequestBody Member member) {
        memberService.signUp(member);
        return "OK";
    }

    @RequestMapping("/test")
    public String test() {
        log.info("------test 요청됨------");
        return "Spring security Setting 완료!";
    }
}