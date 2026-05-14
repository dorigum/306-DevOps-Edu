package web.mvc.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionAdvice {
	@ResponseBody
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MyErrorException.class)
	public Map<String, Object> error(MyErrorException e) {
		Map<String, Object> map = new HashMap<>();
		ErrorCode errorCode = e.getErrorCode();

		map.put("status", errorCode.getStatus());
		map.put("message", errorCode.getMsg());

		return map;
	}

	@ResponseBody
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public Map<String, Object> invalidJson(HttpMessageNotReadableException e) {
		Map<String, Object> map = new HashMap<>();

		map.put("status", 400);
		map.put("message",
				"\uC694\uCCAD \uB370\uC774\uD130 \uD615\uC2DD\uC774 \uC798\uBABB\uB418\uC5C8\uC2B5\uB2C8\uB2E4.");

		return map;
	}
}