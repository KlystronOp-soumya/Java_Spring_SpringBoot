package com.demo.jdbcDemo;

import org.apache.log4j.Logger;

import com.demo.jdbcDemo.service.impl.AgentImpl;
import com.demo.jdbcDemo.service.intf.AgentIntf;
import com.demo.jdbcDemo.utils.DataBaseConfigurator;
import com.demo.jdbcDemo.utils.Log4jConfigurator;

/**
 * Hello world!
 *
 */
public class App 
{
	private static final Logger LOGGER = Logger.getLogger(App.class) ;
	
    public static void main( String[] args )
    {
    	LOGGER.info("Configuring the env");
    	intializeEnv();
    	LOGGER.info("Service called");
    	AgentIntf agtSrvice = new AgentImpl();
    	//agtSrvice.checkDBConnection();
    	agtSrvice.setRowSet();
    	agtSrvice.getActorsDetails().forEach(e->System.out.println(e));
        
    }
    
    private static void intializeEnv()
    {
    	Log4jConfigurator.initializeLogger();
        LOGGER.info("Logger was configured properly");
        DataBaseConfigurator.loadDBConfigs();
        LOGGER.info("Database props set successfully");
    }
}
