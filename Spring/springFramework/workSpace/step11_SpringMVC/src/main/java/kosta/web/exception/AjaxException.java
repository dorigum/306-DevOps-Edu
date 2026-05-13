package kosta.web.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

// Ajax 전용 예외 클래스
@RequiredArgsConstructor
@Getter
public class AjaxException extends RuntimeException {
	private final ErrorInfo errorInfo;
}
