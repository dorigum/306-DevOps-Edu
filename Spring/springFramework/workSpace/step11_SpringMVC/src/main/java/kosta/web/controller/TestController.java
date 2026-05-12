package kosta.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller // 생성
public class TestController {
	@RequestMapping("/test.do")
	public ModelAndView aa() {
		System.out.println("TestController의 test.do 요청");

		// 뷰 쪽으로 전달할 데이터가 존재
		return new ModelAndView("result", "message", "Spring 재미있다!"); // view 이름의 결과 prefix + view 이름 + suffix 조합
	}

	@RequestMapping("/test2.do")
	public ModelAndView aa(String no) {
		System.out.println("TestController의 test2.do 요청");

		int convert = Integer.parseInt(no);

		return new ModelAndView("result", "message", "Spring 재미있다!"); // view 이름의 결과 prefix + view 이름 + suffix 조합
	}
}
