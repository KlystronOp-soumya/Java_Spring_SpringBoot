package com.demo.aopdemo.aspects;

import java.util.Arrays;

import javax.persistence.JoinTable;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogAspect {

	private static final Logger LOGGER = LoggerFactory.getLogger(LogAspect.class) ;
	
	@Around("@annotation(com.demo.aopdemo.aspectanno.LogExecTime)")
	public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
	    long start = System.currentTimeMillis();

	    Object proceed = joinPoint.proceed();

	    long executionTime = System.currentTimeMillis() - start;

	    System.out.println(joinPoint.getSignature() + " executed in " + executionTime + "ms");
	    return proceed;
	}
	
	@Around("@annotation(com.demo.aopdemo.aspectanno.Loggable)")
	public Object loggable(ProceedingJoinPoint joinPoint) throws Throwable{
		
		Object args[] = joinPoint.getArgs() ;
		String packageName = joinPoint.getSignature().getDeclaringTypeName() ;
		String methodName = joinPoint.getSignature().getName() ;
		infoLog(packageName, methodName, args);
		return joinPoint.proceed() ;
	}
	
	private void debugLog()
	{
		
	}
	private void infoLog(final String packageName , final String methodName , final Object[] args)
	{
		LOGGER.info("Executed >> [ " + packageName + " ] :: " + methodName + " arglist: [ " + (args.length == 0 ?  "No argumetns" : Arrays.asList(args)) + " ] ");
	}
}
