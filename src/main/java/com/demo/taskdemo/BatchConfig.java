package com.demo.taskdemo;

import javax.sql.DataSource;

import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.JobRepositoryFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.support.JdbcTransactionManager;
import org.springframework.jdbc.support.SQLErrorCodeSQLExceptionTranslator;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@Order(value = 1)
public class BatchConfig {

	@Autowired
	private Environment env;

	@Bean("dataSourceBean")
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

	@Bean("jobRepository")
	public JobRepository jobRepository() throws Exception {

		JobRepositoryFactoryBean jobRepositoryFactoryBean = new JobRepositoryFactoryBean();
		jobRepositoryFactoryBean.setDatabaseType("MYSQL");
		jobRepositoryFactoryBean.setDataSource(dataSource());
		jobRepositoryFactoryBean.setTransactionManager(transactionManager());
		jobRepositoryFactoryBean.afterPropertiesSet();

		return jobRepositoryFactoryBean.getObject();
	}

	@Bean("jobLauncher")
	public SimpleJobLauncher jobLauncher() throws Exception {
		SimpleJobLauncher simpleJobLauncher = new SimpleJobLauncher();
		simpleJobLauncher.setTaskExecutor(new SimpleAsyncTaskExecutor("batchTaskThread"));

		simpleJobLauncher.setJobRepository(this.jobRepository());
		simpleJobLauncher.afterPropertiesSet();

		return simpleJobLauncher;
	}

	@Bean(name = "txManager")
	public PlatformTransactionManager transactionManager() { // TODO Auto-generated method stub final
		JdbcTransactionManager transactionManager = new JdbcTransactionManager(dataSource());
		transactionManager.setDatabaseProductName("MySQL");
		transactionManager.afterPropertiesSet();
		return transactionManager;
	}

	@Bean
	public SQLErrorCodeSQLExceptionTranslator sqlErrorCodeSQLExceptionTranslator() {
		final SQLErrorCodeSQLExceptionTranslator exceptionTranslator = new SQLErrorCodeSQLExceptionTranslator();

		exceptionTranslator.setDatabaseProductName("MySQL");
		exceptionTranslator.setDataSource(dataSource());
		return exceptionTranslator;
	}

}
