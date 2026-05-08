package kosta.advice;

import org.aspectj.lang.annotation.Pointcut;

// pointcut 정의하는 클래스
public class PointCutClass {
	// log 기록을 할 pointcut 정의
	@Pointcut("execution(* kosta.service.CustomerService.*(..))")
	public void logDef() {
	}

	// transaction 처리할 pointcut 정의
	@Pointcut("execution(* kosta.service.CustomerService.*(..))")
	public void tranDef() {
	}

	// timer 체크할 pointcut 정의
	@Pointcut("execution(* kosta.service.CustomerService.*(..))")
	public void timerCheck() {
	}
}
