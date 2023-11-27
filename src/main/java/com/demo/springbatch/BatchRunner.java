package com.demo.springbatch;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BatchRunner {
	
	public static void main(String[] args) {
		String springConfig = "QuartzJob/quartz-job-config.xml";
		ApplicationContext context = null ;
		try {
			

			context = new ClassPathXmlApplicationContext(springConfig);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			//System.exit(-1);
		}
	}
}
