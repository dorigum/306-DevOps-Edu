package kosta.test.tv.advice;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import java.io.FileWriter;
import java.io.IOException;

@Component
@Aspect
public class LogAdvice {
	private FileWriter fw;

	public LogAdvice() throws IOException {
		fw = new FileWriter("info.txt");
	}

	// 공통 기능 구현하기
	// around: 사전/사후 처리
	// 기능 호출/인수 개수/리턴 값/실행 시간 반환
	@Around("execution(* kosta.test.service.Player.*(..))")
	public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
		long start = System.currentTimeMillis();

		Object result = joinPoint.proceed();

		long end = System.currentTimeMillis();

		String methodName = joinPoint.getSignature().getName();

		int argCount = joinPoint.getArgs().length;

		fw.write(methodName + " 호출" + " / 인수 개수 : " + argCount + " / 리턴 값: " + result + " / 총 실행 시간 : " + (end - start)
				+ "ms\n");
		fw.flush();

		return result;
	}
}