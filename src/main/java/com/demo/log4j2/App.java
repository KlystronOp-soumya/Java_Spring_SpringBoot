package com.demo.log4j2;

import java.io.File;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.LoggerContext;

/**
 * Hello world!
 *
 */
public class App 
{
	/*The Logger*/
	private static final Logger LOGGER = LogManager.getLogger(App.class) ;
    public static void main( String[] args )
    {
    	LoggerContext context = (LoggerContext) LogManager.getContext(false);
    	File file = new File("src/main/resources/logger-config.xml");
    	context.setConfigLocation(file.toURI());
    	LOGGER.info("This is Logged Hello World");
        System.out.println( "Hello World!" );
    }
}
