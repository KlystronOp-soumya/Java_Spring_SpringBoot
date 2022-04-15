package com.demo.SpringJdbcDemo.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "agentServiceBean")
public class AgentService {
	/* The Logger */
	private static final Logger LOGGER = LogManager.getLogger(AgentService.class);

	// Injection
	@Autowired
	private transient TestService testService;

	/*
	 * Constructor Injection
	 * 
	 * @Autowired public AgentService(TestService testService) { // TODO
	 * Auto-generated constructor stub this.testService = testService; }
	 */

	public void doCheck() {
		LOGGER.info("Inside AgentService :: doCheck");
		this.testService.doGreet();
	}

	/*
	 * @Autowired public void setTestService(TestService testService) {
	 * this.testService = testService; }
	 */
}
