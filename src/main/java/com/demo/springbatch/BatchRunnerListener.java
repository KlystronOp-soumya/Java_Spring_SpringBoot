package com.demo.springbatch;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class BatchRunnerListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		System.out.println("BatchRunner context destroyed");
		
	}

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		
		//Object of Launch job
		System.out.println("BatchController context init");
		
	}
	
	
}
