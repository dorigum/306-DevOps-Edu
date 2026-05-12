package kosta.web.controller;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import kosta.web.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller // 생성
@Slf4j
@RequiredArgsConstructor
public class ExceptionController {

	private final UserService userService;

	@RequestMapping("/exception.do")
	public String test(String no) {
		log.info("no = {}", no);

		// 문자열 -> 숫자 변환 작업
		int conovertNo = Integer.parseInt(no);
		log.info("conovertNo = {}", conovertNo);

		int result = 100 / conovertNo;
		log.info("result = {}", result);

		return "result"; // WEB-INF/views/result.jsp
	}

	/*
	 * 나이 체크 기능
	 */
	@RequestMapping("ageCheck.do")
	public String ageCheck(@RequestParam(defaultValue = "0") int age) {
		log.info("age = {}", age);

		// 전달받은 age를 서비스에 전달해서 호출한다.
		userService.ageCheck(age);

		return "result";
	}

	/*
	 * 아이디 체크 기능
	 */
	@GetMapping("/idCheck.do")
	public String idCheck(String id) {
		log.info("id = {}", id);

		userService.idCheck(id);

		return "result";
	}

	/*
	 * 만약 현재 컨트롤러에서 NumberFormatException이 발생하면 error 메소드 실행
	 */
	@ExceptionHandler({ NumberFormatException.class, ArithmeticException.class })
	public ModelAndView error(Exception e) {
		log.info("error Msg: {}", e.getMessage());

		// 예외가 발생했을 때 해야할 일(catch 영역)
		ModelAndView mv = new ModelAndView();

		mv.setViewName("error/errorView");
		mv.addObject("errMsg", e.getMessage());
		mv.addObject("errClass", e.getClass().getName());
		mv.addObject("statusCode", HttpStatus.BAD_REQUEST);

		return mv;
	}
}