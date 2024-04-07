package com.demo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.demo.service.AgentServiceImpl;

public class RunApp {

	public static void main(String[] args) {
		System.out.println("Hello World");
		System.out.println("Loading classpathxml");
		ClassPathXmlApplicationContext applicationContext = null;
		try {
			applicationContext = new ClassPathXmlApplicationContext("resources/contexts/appcontext/appContext.xml");
			// get the bean
			AgentServiceImpl agentService = applicationContext.getBean("agentService", AgentServiceImpl.class);

			// call the method
			agentService.check();

		} catch (Exception e) {
			System.err.println(e.getMessage());
		} finally {
			applicationContext.close();
		}
	}

}
