package com.demo.cartapp;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.SQLExceptionSubclassTranslator;

@Configuration(proxyBeanMethods = false)
public class CartAppConfig {
	
	private  DataSource dataSource ;
	
	CartAppConfig(final DataSource dataSource)
	{
		this.dataSource = dataSource ;
	}
	
	@Bean("jdbcTemplate")
	public JdbcTemplate jdbcTemplate()
	{
		JdbcTemplate jdbcTemplate = new JdbcTemplate(this.dataSource, false) ;
		jdbcTemplate.setDatabaseProductName("H2");
		jdbcTemplate.setExceptionTranslator(new SQLExceptionSubclassTranslator());
		
		return jdbcTemplate ;
	}
	
	
	
}
