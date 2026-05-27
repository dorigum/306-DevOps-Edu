package web.mvc.advice;

import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import web.mvc.exception.BoardSearchNotException;
import web.mvc.exception.MemberAuthenticationException;

import java.time.LocalDateTime;

@RestControllerAdvice
public class DefaultExceptionAdvice {
    @ExceptionHandler({MemberAuthenticationException.class})
    public ProblemDetail signInExceptionHandle(MemberAuthenticationException e) {
        ProblemDetail problemDetail = ProblemDetail.forStatus(e.getErrorCode().getHttpStatus());

        problemDetail.setTitle(e.getErrorCode().getTitle());
        problemDetail.setDetail(e.getErrorCode().getMessage());
        problemDetail.setProperty("timestamp", LocalDateTime.now());

        return problemDetail;
    }

    @ExceptionHandler({BoardSearchNotException.class})
    public ProblemDetail boardSearchExceptionHandle(BoardSearchNotException e) {
        ProblemDetail problemDetail = ProblemDetail.forStatus(e.getHttpStatus().value());

        problemDetail.setTitle(e.getTitle());
        problemDetail.setDetail(e.getMessage());
        problemDetail.setProperty("timestamp", LocalDateTime.now());

        return problemDetail;
    }

    @ExceptionHandler(Exception.class)
    public ProblemDetail exceptionHandle(Exception e) {
        ProblemDetail problemDetail = ProblemDetail.forStatus(600);

        problemDetail.setTitle("DB Error");
        problemDetail.setDetail("예외가 발생했습니다.");
        problemDetail.setProperty("timestamp", LocalDateTime.now());

        return problemDetail;
    }
}