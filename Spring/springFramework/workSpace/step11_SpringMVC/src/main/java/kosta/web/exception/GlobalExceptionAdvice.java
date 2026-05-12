package kosta.web.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.slf4j.Slf4j;

/*
 * 전체 프로젝트에서 발생한 예외를 전역으로 처리하는 클래스
 */

@ControllerAdvice // 생성
@Slf4j
public class GlobalExceptionAdvice {
	
	@ExceptionHandler(value = { NumberFormatException.class })
	public ModelAndView error(NumberFormatException e) {

		log.info("global error Msg: {}", e.getMessage());

		// 예외가 발생했을 때 해야할 일(catch 영역)
		ModelAndView mv = new ModelAndView();
		mv.setViewName("error/basic"); // WEB-INF/views/error/basic.jsp
		
		mv.addObject("errMsg", e.getMessage());
		mv.addObject("errClass", e.getClass().getName());
		mv.addObject("statusCode", HttpStatus.BAD_REQUEST);

		return mv;
	}
	
	@ExceptionHandler({BasicException.class})
	public ModelAndView error(BasicException e) {
		log.info("error BasicException: {}", e.getErrorInfo().getMessage());
		
		// WEB-INF/views/error/error.jsp 이동
		return new ModelAndView("error/error", "errorInfo", e.getErrorInfo());
	}
}