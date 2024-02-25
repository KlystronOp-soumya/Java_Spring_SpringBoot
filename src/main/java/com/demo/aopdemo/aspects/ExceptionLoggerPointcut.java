package com.demo.aopdemo.aspects;

import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ExceptionLoggerPointcut {
	private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionLoggerPointcut.class) ;
	  @AfterThrowing(pointcut = "execution(* com.demo.aopdemo.aspects.*.*(..))", throwing = "ex")
	    public void logError(Exception ex) {
		  System.out.println("ITS HERE ONLY");
		  	LOGGER.error(ex.getCause().toString());
	        LOGGER.error(ex.getMessage());
	    }
	  /*@AfterThrowing(pointcut = "execution(* com.example..*.*(..))", throwing = "ex")
	    public void handleExceptions(Exception ex) {
	        logErrorBasedOnExceptionType(ex);
	    }

	    private void logErrorBasedOnExceptionType(Exception ex) {
	        if (ex instanceof IOException ioEx) {
	            log.error("IOException occurred: {}", ioEx.getMessage());
	        } else if (ex instanceof SQLException sqlEx) {
	            log.error("SQLException occurred: {}", sqlEx.getMessage());
	        } else {
	            log.error("Exception occurred: {}", ex.getMessage());
	        }
	    }*/
}
