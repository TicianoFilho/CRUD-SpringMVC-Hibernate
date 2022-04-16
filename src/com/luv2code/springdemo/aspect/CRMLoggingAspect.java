package com.luv2code.springdemo.aspect;

import java.util.logging.Logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CRMLoggingAspect {
	
	Logger myLogger = Logger.getLogger(this.getClass().getName());

	@Pointcut("execution(* com.luv2code.springdemo.controller.*.*(..))")
	private void forControllerPackage() {
		
	}
	
	@Pointcut("execution(* com.luv2code.springdemo.dao.*.*(..))")
	private void forDaoPackage() {
		
	}
	
	@Pointcut("execution(* com.luv2code.springdemo.service.*.*(..))")
	private void forServicePackage() {
		
	}
	
	@Pointcut("forControllerPackage() || forDaoPackage() || forServicePackage()")
	private void forAppFlow() {
		
	}
	
	//Add @Before Advice
	@Before("forAppFlow()")
	public void beforeAdvice(JoinPoint theJoinPoint) {
		
		//display method we're calling
		String theMethod = theJoinPoint.getSignature().toShortString();
		myLogger.info("====>>> inside of @Before: calling method: " + theMethod);
		
		//get the method arguments and display it
		Object[] arguments = theJoinPoint.getArgs();
		
		for (Object arg : arguments) {
			myLogger.info("====>>> argument: " + arg);
		}
		
	}
	
	//Add @AfterReturning Advice
	@AfterReturning(pointcut = "forAppFlow()", returning = "theResult")
	public void afterReturningAdvice(JoinPoint theJoinPoint, Object theResult) {
		
		//get the method name and display it
		String theMethod = theJoinPoint.getSignature().toShortString();
		myLogger.info("====>>> inside of @AfterReturning: from method: " + theMethod);
		
		//Display data return
		myLogger.info("====>>> result: " + theResult);
		
	}

}
