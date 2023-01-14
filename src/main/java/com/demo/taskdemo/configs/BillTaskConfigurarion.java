package com.demo.taskdemo.configs;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.task.configuration.EnableTask;
import org.springframework.cloud.task.configuration.TaskConfigurer;
import org.springframework.cloud.task.repository.support.TaskExecutionDaoFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.support.JdbcTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import com.demo.taskdemo.taskdef.CustomTask;

/*
 * Configuartion class
 * 
 * Configures Datasource, PlatFormTransactionManager and injects beans
 * 
 * */
@Configuration
@EnableTask
@ComponentScan(basePackages = "com.demo.taskdemo")
@PropertySource(value = "classpath:db-config.properties")
public class BillTaskConfigurarion {

	@Autowired
	private Environment env;

	@Bean(name = "dataSource")
	@Primary
	public DataSource dataSource() {
		// TODO Auto-generated method stub

		final DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(env.getProperty("mysql.driver"));
		dataSource.setUrl(env.getProperty("mysql.url"));
		dataSource.setSchema("CYOLASBCOM");
		dataSource.setUsername(env.getProperty("mysql.user"));
		dataSource.setPassword(env.getProperty("mysql.password"));
		return dataSource;

	}

	@Bean(name = "txManager")
	@Primary
	public PlatformTransactionManager transactionManager() { // TODO Auto-generated method stub final
		JdbcTransactionManager transactionManager = new JdbcTransactionManager(dataSource());
		transactionManager.setDatabaseProductName("MySQL");
		transactionManager.afterPropertiesSet();
		return transactionManager;
	}

	/*
	 * Custom implementation for the Task Depends on datasource, transaction manager
	 * and TaskExecution
	 * 
	 */
	@Bean("customTaskConfigurer")
	public TaskConfigurer taskConfigurer() {
		CustomTask customTask = new CustomTask(dataSource(), transactionManager(),
				new TaskExecutionDaoFactoryBean(dataSource()));
		return customTask;
	}

}
