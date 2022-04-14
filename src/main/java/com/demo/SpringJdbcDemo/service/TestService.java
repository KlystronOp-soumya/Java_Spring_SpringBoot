package com.demo.SpringJdbcDemo.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

@Component("testServiceBean")
public class TestService {

	/* The Logger */
	private static final Logger LOGGER = LogManager.getLogger(TestService.class);

	public void doGreet() {
		System.out.println("Hello World");
	}
}
