package com.kim.app.common;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Service;

@Service
@Aspect
public class BeforeAdvice {
	
	@Before("PointcutCommon.beforePointcut()")
	public void printLog(JoinPoint jp) {
		
		
		System.out.println("====================");
		System.out.println("데이터출력시작");
	}
	
}
