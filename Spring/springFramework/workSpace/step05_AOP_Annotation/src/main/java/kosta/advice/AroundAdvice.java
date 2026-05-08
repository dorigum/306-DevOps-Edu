package kosta.advice;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

// 사전 사후 공통(부가) 기능
@Component("aroundAdvice")
@Aspect
@Order(2)
public class AroundAdvice {
	@Around("PointCutClass.logDef()")
	public Object around(ProceedingJoinPoint joinPoint) throws Throwable {

		System.out.println("AroundAdvice의 around 사전 처리입니다.\n");

		Object re = joinPoint.proceed();

		System.out.println("AroundAdvice의 around 사후 처리입니다.\n");

		return re;
	}

}
