package web.mvc.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.slf4j.Slf4j;

// 프로젝트 내의 여러 Controller에서 발생한 예외를 전역으로 처리하는 클래스
@ControllerAdvice
@Slf4j
public class GlobalExceptionAdvice {

	@ExceptionHandler(MyErrorException.class)
	public ModelAndView error(MyErrorException e) {
		ErrorCode errorCode = e.getErrorCode();

		log.info("global error status: {}, msg = {}",
				errorCode.getStatus(), errorCode.getMsg());

		// 예외가 발생했을 때 해야할 일(catch 영역)
		ModelAndView mv = new ModelAndView();
		mv.setViewName("error"); // WEB-INF/views/error.jsp
		mv.addObject("errStatus", errorCode.getStatus());
		mv.addObject("errMessage", errorCode.getMsg());

		return mv;
	}
}
