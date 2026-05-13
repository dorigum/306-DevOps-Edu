package kosta.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import kosta.web.dto.UserDTO;
import lombok.extern.slf4j.Slf4j;

// @Controller
@RestController // A convenience annotation that is itself annotated with @Controller and @ResponseBody.
@Slf4j
public class ResponseBodyController {
	@GetMapping(value = "/responseBody.do", produces = { "text/plain; charset = UTF-8" })
	// @ResponseBody // 리턴값이 view가 아닌 HttpMessageConverter 동작에 의해 응답 body가 된다.(데이터)
	public String aa() {
		log.info("responseBody.do 요청됨");

		return "배고프다~~ = I am hungry";
	}

	/*
	 * 객체를 리턴하고 싶다면, 아래 코드 추가(단, Spring Boot는 자동 포함!)
	 * 
	 * <dependency>
	 * <groupId>com.fasterxml.jackson.core</groupId>
	 * <artifactId>jackson-databind</artifactId>
	 * <version>2.18.4</version>
	 * </dependency>
	 */
	@GetMapping("/responseBody2.do")
	// @ResponseBody // HttpMessageConverter 동작에 의해 응답 body가 된다.(데이터) - application/json
	public UserDTO bb() {
		log.info("responseBody2.do 요청됨");

		// return new UserDTO();
		return UserDTO.builder()
				.addr("서울")
				.id("koo")
				.age(20)
				.name("도연")
				.build();
	}
}
