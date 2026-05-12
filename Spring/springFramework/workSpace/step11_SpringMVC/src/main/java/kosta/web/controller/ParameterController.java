package kosta.web.controller;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kosta.web.dto.UserDTO;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/param")
@Slf4j // private static final org.slf4j.Logger log =
		// org.slf4j.LoggerFactory.getLogger(LogExample.class);
public class ParameterController {
//	Logger log = LoggerFactory.getLogger(getClass());

	@GetMapping("/a.do")
//	public String test(String name, int age) {
	public String test(String name, Integer age) { // 없으면 null, 있으면 값으로 대응
		log.info("ParameterController a.do 요청됨");
		log.info("name = {}, age = {}", name, age);

		return "result"; // /WEB-INF/views/result.jsp 이동
	}

	@RequestMapping("/b.do")
	public String bb(@RequestParam(value = "userId", required = false) String id,
					 @RequestParam(defaultValue = "0") int age) {
		log.info("param./b.do 요청됨");
		log.info("id = {}, age = {}", id, age);

		return "result"; // result: view의 이름
	}
	
	/* 
	 * void이므로 view page /WEB-INF/views/param/user.jsp 이동
	 * 
	 * 인수로 parameter로 전달되어지는 이름과 속성이 일치하는 객체 타입을 선언하면
	 * 값들이 Setter 메소드에 바인딩된다.
	 * 
	 * ★★★★★★★★★
	 * 바인딩된 객체 타입은 view쪽으로 전달된다!(Model에 저장이 된다.)
	 * 뷰에서 ${객체이름.속성}으로 접근 가능 -> 객체 이름은 객체 이름 첫 글자가 '소문자'
	 * ex) UserDTO는 ${userDTO.속성} -> 이렇게 하면 userDTO.getXxx() 호출된다.
	 * 
	 * 
	 * 매개변수 옆에 @ModelAttribute("dto")를 선언하면,
	 * 뷰에서 ${dto.속성}
	 */
	@PostMapping("/user.do")
//	public void user(UserDTO dto) {
	public void user(@ModelAttribute("dto") UserDTO dto) {
		log.info("dto = {}", dto); // dto.toString()
	}
	
	/*
	 * 현재 컨트롤러를 실행하는 모든 뷰의 공유 데이터 설정
	 */
	@ModelAttribute("msg") // 뷰에서 ${msg}
	public String message() {
		return "졸지 맙시다!!";
	}
	
	@ModelAttribute("hobbysList") // 뷰에서 ${hobbysList}
	public List<String> list() {
		return Arrays.asList("A", "B", "C");
	}
} // class end