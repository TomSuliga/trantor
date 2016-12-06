package org.suliga.trantor.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

// Both @Aspect and @Component are required
// Will execute on the interface methods
// Name such as MineSweeper is the concrete class, not the interface name
@Aspect
@Component
public class MyAspect {
	@Before("execution(* org.suliga..*MineSweeper.*(..))")
	public void logServiceAccess1(JoinPoint joinPoint) {
		System.out.println("MyAspect - Before: " + joinPoint);
	}
	
	@After("execution(* org.suliga..*(int, int))")
	public void logServiceAccess2(JoinPoint joinPoint) {
		System.out.println("MyAspect - After: " + joinPoint);
	}
}
