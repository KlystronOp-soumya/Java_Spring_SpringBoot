package com.demo.todoapp;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionManager;

import com.zaxxer.hikari.HikariDataSource;

@Configuration( value = "todoAppConfig",proxyBeanMethods = false)
public class ToDoAppConfig {
	
	@Bean("transactionManager")
	TransactionManager transactionManager(final HikariDataSource dataSource) {
		DataSourceTransactionManager transactionManager = new DataSourceTransactionManager(dataSource) ;
		transactionManager.setRollbackOnCommitFailure(true);
		return transactionManager;
	}
	
	@Bean
	NamedParameterJdbcTemplate namedParameterJdbcTemplate(final DataSource dataSource)
	{
		NamedParameterJdbcTemplate jdbcTemplate = new NamedParameterJdbcTemplate(dataSource) ;
		return jdbcTemplate ;
	}

}
