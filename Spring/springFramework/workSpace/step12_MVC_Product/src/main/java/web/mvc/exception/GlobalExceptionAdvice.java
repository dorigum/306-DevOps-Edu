package web.mvc.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.slf4j.Slf4j;

// 프로젝트 내에서 발생한 예외를 전역으로 처리하는 클래스
@ControllerAdvice
@Slf4j
public class GlobalExceptionAdvice {

	@ExceptionHandler(MyErrorException.class)
	public ModelAndView error(MyErrorException e) {
		log.info("global error msg: {}", e.getMessage());
		
		// 예외가 발생했을 때 해야할 일(catch 영역)
		ModelAndView mv = new ModelAndView();
		mv.setViewName("error/error"); // WEB-INF/views/error/error.jsp
		
		mv.addObject("errCode", e.getMessage());
		mv.addObject("errCodeDuplicate", e.getMessage());
		mv.addObject("errPrice", e.getMessage());
		mv.addObject("statusUpdate", HttpStatus.BAD_REQUEST);
		
		return mv;
	}
	
//	@ExceptionHandler
}
