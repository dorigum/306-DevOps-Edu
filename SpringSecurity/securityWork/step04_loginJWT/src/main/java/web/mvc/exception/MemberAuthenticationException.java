package web.mvc.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public class MemberAuthenticationException extends RuntimeException {
    private final ErrorCode errorCode;
//    private String message;
//    private HttpStatus httpStatus;
//    private String title;
//
//    public MemberAuthenticationException(String message, String title) {
//        this(message, title, HttpStatus.UNAUTHORIZED);
//    }
//
//    public MemberAuthenticationException(String message, String title, HttpStatus httpStatus) {
//        this.message = message;
//        this.title = title;
//        this.httpStatus = httpStatus;
//    }
//
//    public MemberAuthenticationException(ErrorCode errorCode) {
//    }
}