package com.demo.batch.Util;

import org.apache.log4j.xml.DOMConfigurator;

import java.net.URL;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger ;
import org.apache.log4j.PropertyConfigurator;

public class LoggerUtil {
	protected final static String LOGCONF = "logger-config.xml" ;
	static {
		
		initLogger();
	}

	private static void initLogger() { 
		DOMConfigurator.configure(LOGCONF);
	}


	public static void debug(Logger logger,String message)
	{
				logger.debug(message) ;
	}
	public static void info(Logger logger,String message)
	{
		logger.info(message) ;
	}
}
