package kosta.web.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import kosta.web.dto.ResponseDTO;

// 비동기 통신(Ajax)에서 발생하는 예외 처리 클래스

@RestControllerAdvice
public class RestGlobalExceptionAdvice {
//	@ExceptionHandler(AjaxException.class)
//	public String error(AjaxException e) {
//		
//		return "예외 정보";
//	}
	
	@ExceptionHandler(AjaxException.class)
	public ResponseEntity<?> error(AjaxException e) {
		
		ResponseDTO dto = ResponseDTO.builder()
				.status(e.getErrorInfo().getStatus())
				.msg(e.getErrorInfo().getMessage())
				.build();
		
		return ResponseEntity
				.status(e.getErrorInfo().getStatus())
				.body(dto);
	}
}
