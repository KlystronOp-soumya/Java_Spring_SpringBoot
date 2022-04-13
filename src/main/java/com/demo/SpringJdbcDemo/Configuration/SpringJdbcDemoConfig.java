package com.demo.SpringJdbcDemo.Configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.demo.SpringJdbcDemo.service.TestService;

@Configuration
public class SpringJdbcDemoConfig {
	@Bean("testServiceBean")
	public TestService testService() {
		return new TestService();
	}
}
