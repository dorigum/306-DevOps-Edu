package kosta.advice;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component("sampleAdvice")
@Aspect
@Order(1)
public class SampleAdvice {
	// 사전 처리
	@Before("PointCutClass.logDef()")
	public void before(JoinPoint joinPoint) {
		String methodName = joinPoint.getSignature().getName();
		System.out.println(methodName + "의 사전 처리 before 입니다!\n");
	}

	// 사후 처리(정상 동작할 경우)
	@AfterReturning(pointcut = "PointCutClass.logDef()", returning = "obj")
	public void afterReturing(JoinPoint joinPoint, Object obj) {
		System.out.println("정상적으로 동작했을 때의 사후 처리입니다~");

		String methodName = joinPoint.getSignature().getName();
		System.out.println(methodName + "의 리턴값은 " + obj + "\n");
	}

	// 사후 처리(예외가 발생했을 때)
	@AfterThrowing(pointcut = "PointCutClass.logDef()", throwing = "e")
	public void afterThrowing(Throwable e) {
		System.out.println("예외가 발생했을 때 사후 처리입니다~");
		System.out.println("예외 정보 e = " + e);
	}

	// 사후 처리(예외 발생 여부 상관없이 무조건 실행)
	@After("PointCutClass.logDef()")
	public void afterFinally() {
		System.out.println("예외 발생 여부 상관없이 무조건 사후 처리 실행해요!");
	}
}
