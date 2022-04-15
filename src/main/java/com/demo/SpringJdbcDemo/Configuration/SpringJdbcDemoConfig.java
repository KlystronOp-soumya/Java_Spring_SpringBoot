package com.demo.SpringJdbcDemo.Configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import com.demo.SpringJdbcDemo.service.AgentService;
import com.demo.SpringJdbcDemo.service.TestService;

@Configuration
public class SpringJdbcDemoConfig {
	protected transient final String schemaPath = "classpath:database/create_drop.sql";
	protected transient final String insertPath = "classpath:database/schema.sql";

	@Bean("testServiceBean")
	public TestService testService() {
		return new TestService();
	}

	@Bean("agentServiceBean")
	public AgentService agentService() {
		return new AgentService();
	}

	@Bean
	public ResourceDatabasePopulator resourceDatabasePopulator() {

		ResourceDatabasePopulator rsrcDbPopulator = new ResourceDatabasePopulator();
		// other configurations
		return rsrcDbPopulator;
	}
}
