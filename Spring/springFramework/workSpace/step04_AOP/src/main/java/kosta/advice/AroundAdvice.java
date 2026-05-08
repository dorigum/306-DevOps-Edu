package kosta.advice;

import org.aspectj.lang.ProceedingJoinPoint;

/*
 * 사전 사후 공통(부가) 기능
 */
public class AroundAdvice {

	public Object around(ProceedingJoinPoint joinPoint) throws Throwable {

		System.out.println("AroundAdvice의 around 사전 처리입니다.\n");

		Object re = joinPoint.proceed();

		System.out.println("AroundAdvice의 around 사후 처리입니다.\n");

		return re;
	}

}
