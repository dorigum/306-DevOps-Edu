package web.mvc.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import web.mvc.domain.User;
import web.mvc.service.UserService;

@Controller
@Slf4j
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    /*
     * header.jsp에
     */
    @GetMapping("/{url}")
    public void url() {
    }

    @PostMapping("/loginCheck")
    public String login(User user, HttpSession session) {
        log.info("user login userID = {}, pwd = {}", user.getUserId(), user.getPwd());

        User dbUser = userService.loginCheck(user);

        // 리턴한 사용자의 정보를 session 영역에 저장한다.
        // -> servlet 기반에서 HttpSession 객체를 제공한다.
        session.setAttribute("user", dbUser); // View에서 ${sessionScope.loginUser} 혹은 ${loginUser} 사용 가능

        // return "index"; // WEB-INF/views/index.jsp
        return "redirect:/"; // HomeController의 /요청이 실행된다. (Post Redirect Get 요청)
    }

    // 로그아웃
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        log.info("user logout");
        session.invalidate(); // 모든 세션의 정보를 무효화

        return "redirect:/";
    }
}