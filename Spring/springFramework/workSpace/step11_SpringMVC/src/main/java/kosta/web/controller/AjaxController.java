package kosta.web.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import kosta.web.dto.ProductDTO;
import kosta.web.dto.UserDTO;
import kosta.web.exception.AjaxException;
import kosta.web.exception.ErrorInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController // @Controller + @ResponseBody
@Slf4j
@RequiredArgsConstructor
public class AjaxController {

	private final List<ProductDTO> productList;

	/*
	@PostMapping("/ajax.do")
	public UserDTO ajax(String email, String pwd) {
		log.info("ajax.do 요청됨");
		log.info("email: {}, pwd: {}", email, pwd);

		return UserDTO.builder()
				.addr("오리")
				.id("jang")
				.age(20)
				.name("미미")
				.build();
	} */

	
	@PostMapping("/ajax.do")
	public ResponseEntity<?> ajax(String email, String pwd) {
		log.info("ajax.do 요청됨");
		log.info("email: {}, pwd: {}", email, pwd);

		return ResponseEntity
				.ok(UserDTO.builder()
				.addr("오리")
				.id("jang")
				.age(20)
				.name("미미")
				.build());
	}
	
	/*
	 * front 단에서
	 * headers: {
	 *     "Content-Type": "application/json"
	 *  },
	 *  body: JSON.stringify(jsonData)
	 * 설정이 되면 @RequestBody 필요
	 */
	
	/*
	@PostMapping("/ajax2.do")
//	public String ajax2(UserDTO userDTO) { 
	public String ajax2(@RequestBody UserDTO userDTO) {
		log.info("userDTO = {}", userDTO);
		
		return "success";
	}
	*/
	
	@PostMapping("/ajax2.do")
	public ResponseEntity<?> ajax2(@RequestBody UserDTO userDTO) {
		log.info("userDTO = {}", userDTO);
		
		return ResponseEntity.ok("success");
	}

	/*
	@PostMapping("/ajax3.do")
	public List<ProductDTO> ajax3(@RequestBody UserDTO userDTO) {
		log.info("userDTO = {}", userDTO);
		
		if(userDTO.getAge() < 18)
			throw new AjaxException(ErrorInfo.INVALID_AGE);
		
		return productList;
	}
	*/
	
	@PostMapping("/ajax3.do")
	public ResponseEntity<?> ajax3(@RequestBody UserDTO userDTO) {
		log.info("userDTO = {}", userDTO);
		
		if(userDTO.getAge() < 18)
			throw new AjaxException(ErrorInfo.INVALID_AGE);
		
		return ResponseEntity.ok(productList);
	}
}