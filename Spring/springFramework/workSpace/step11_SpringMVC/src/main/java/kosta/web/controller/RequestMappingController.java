package kosta.web.controller;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller // 생성
@RequestMapping("/rem")
public class RequestMappingController {

	/*
	 * @return: String을 하게 되면 리턴되는 문자열이 viewName이 된다!
	 */
	@RequestMapping("/a.do")
	public String aaa() {
		System.out.println("RequestMappingController a.do 요청됨");
		return "result"; // WEB-INF/views/result.jsp 이동
	}

	/*
	 * @ return: void인 경우는 요청한 URL 주소가 view의 이름이 된다.
	 * ex) b.do 라고 요청이 되면 /WEB-INF/views/b.jsp 이동
	 * 
	 * 여러 개의 요청이 하나의 메소드를 실행할 때 { } 안에 작성한다.
	 * 
	 * @param: Model 객체를 받으면 model에 정보 저장
	 * 		   그 객체가 view 쪽으로 전달되고, view에서 ${이름} 사용
	 */
	@RequestMapping({"/b.do", "c.do"})
	public void bb(Model model) {
		System.out.println("RequestMappingController bb 메소드 호출됨");
		
		model.addAttribute("hobbys", Arrays.asList("등산", "낚시", "골프")); // view에서 ${hobbys}
		model.addAttribute("info", "점심 메뉴는?"); // view에서 ${info}
	}
	
	/*
	 * 요청 방식(get or post 요청 처리하기)
	 * 
	 * @GetMapping
	 * @PostMapping
	 * @PutMapping / @PatchMapping
	 * @DeleteMapping
	 */
//	@RequestMapping("/test.do")
//	@GetMapping("/test.do") // get 방식 전용
	@PostMapping("/test.do") // post 방식 전용
	public ModelAndView test(HttpServletRequest request) {
		String name = request.getParameter("name");
		System.out.println("이름 = " + name);
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("result"); // WEB-INF/views/result.jsp 이동
		
		mv.addObject("message", "spring 연습"); // ${message}
		mv.addObject("age", 100); // ${age}
		
		return mv;
	}
	
	/*
	 * parameter의 정보에 따라 메소드 호출 여부
	 */
	@GetMapping(value="/a.do", params="id")
	public String test2(String id) {
		System.out.println("id = " + id);
		return "result"; // /WEB-INF/views/result.jsp 이동
	}
}
