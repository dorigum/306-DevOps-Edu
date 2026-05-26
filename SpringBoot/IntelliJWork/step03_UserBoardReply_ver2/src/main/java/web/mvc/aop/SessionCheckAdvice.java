package web.mvc.aop;


import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import web.mvc.exception.BasicException;
import web.mvc.exception.ErrorCode;

/**
 *  board/* 의 등록 수정 삭제 다운로드 검색 조회등은 반드시 로그인된 사용자만 접근가능하도록 체크
 *   : 만약 인증을 안했다면 오류페이지로 이동 할수 있도록한다.
 * */

@Component // 생성
@Aspect // AOP 설정 <aop:...>
@RequiredArgsConstructor
public class SessionCheckAdvice {
    private final HttpServletRequest request2; // 주입


    @Before("execution(public * web.mvc.controller.FreeBoardController.*(..))")
    //public void before(JoinPoint joinPoint ) {
     public void before( ) {
        // session에 어떤 정보가 있는지 체크
        ServletRequestAttributes attributes =
                (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        //HttpSession session = request.getSession();
        HttpSession session = request2.getSession();

        if(session ==null || session.getAttribute("loginUser") == null) {
            throw new BasicException(ErrorCode.ACCESS_DENIED);
        }

    }
}










