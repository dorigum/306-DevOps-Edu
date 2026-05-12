package kosta.web.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class BasicException extends RuntimeException {
	private final ErrorInfo errorInfo;
}