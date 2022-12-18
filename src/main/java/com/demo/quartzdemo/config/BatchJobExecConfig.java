package com.demo.quartzdemo.config;

import javax.sql.DataSource;

import org.springframework.batch.core.configuration.JobRegistry;
import org.springframework.batch.core.configuration.StepRegistry;
import org.springframework.batch.core.configuration.support.JobRegistryBeanPostProcessor;
import org.springframework.batch.core.configuration.support.MapJobRegistry;
import org.springframework.batch.core.configuration.support.MapStepRegistry;
import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.batch.core.explore.support.JobExplorerFactoryBean;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.launch.support.SimpleJobOperator;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.dao.DefaultExecutionContextSerializer;
import org.springframework.batch.core.repository.support.JobRepositoryFactoryBean;
import org.springframework.batch.support.transaction.ResourcelessTransactionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@PropertySource(value = "classpath:db-config.properties")
public class BatchJobExecConfig {

	@Autowired
	private transient Environment env;

	@Bean
	public ThreadPoolTaskExecutor threadPoolTaskExecutor() {
		ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
		threadPoolTaskExecutor.setCorePoolSize(2);
		threadPoolTaskExecutor.setMaxPoolSize(2);
		threadPoolTaskExecutor.setThreadPriority(5);
		threadPoolTaskExecutor.setThreadGroupName("SpringBatchQuartz");
		threadPoolTaskExecutor.afterPropertiesSet();

		return threadPoolTaskExecutor;
	}

	@Bean(name = "customJobRepo")
	@Primary
	public JobRepository jobRepository() throws Exception {

		JobRepositoryFactoryBean jobRepositoryFactoryBean = new JobRepositoryFactoryBean();
		jobRepositoryFactoryBean.setDatabaseType("h2");
		jobRepositoryFactoryBean.setDataSource(this.h2DataSource());
		jobRepositoryFactoryBean.setTransactionManager(transactionManager());
		jobRepositoryFactoryBean.afterPropertiesSet();

		return jobRepositoryFactoryBean.getObject();
	}

	@Bean
	public SimpleJobLauncher jobLauncher() throws Exception {
		SimpleJobLauncher simpleJobLauncher = new SimpleJobLauncher();
		simpleJobLauncher.setTaskExecutor(threadPoolTaskExecutor());
		simpleJobLauncher.setJobRepository(this.jobRepository());
		simpleJobLauncher.afterPropertiesSet();

		return simpleJobLauncher;
	}

	@Bean(name = "h2_ds")
	public DataSource h2DataSource() {

		DataSourceBuilder<?> dataSourceBuilder = DataSourceBuilder.create();
		dataSourceBuilder.driverClassName(this.env.getProperty("h2.driver"));
		dataSourceBuilder.url(this.env.getProperty("h2.url"));
		dataSourceBuilder.username(this.env.getProperty("h2.user"));
		dataSourceBuilder.password(this.env.getProperty("h2.password"));
		return dataSourceBuilder.build();
	}

	@Bean
	public PlatformTransactionManager transactionManager() {
		return new ResourcelessTransactionManager();
	}

	@Bean
	public JobExplorer jobExplorer() throws Exception {
		JobExplorerFactoryBean factoryBean = new JobExplorerFactoryBean();
		factoryBean.setDataSource(h2DataSource());
		factoryBean.setJdbcOperations(new JdbcTemplate(h2DataSource()));
		factoryBean.setSerializer(new DefaultExecutionContextSerializer());

		return factoryBean.getObject();
	}

	// @Override
	@Bean
	public JobRegistry jobRegistry() throws Exception {
		return new MapJobRegistry();
	}

	@Bean
	public StepRegistry stepRegistry() {
		return new MapStepRegistry();
	}

	@Bean
	public JobRegistryBeanPostProcessor jobRegistryBeanPostProcessor(JobRegistry jobRegistry) {
		JobRegistryBeanPostProcessor postProcessor = new JobRegistryBeanPostProcessor();
		postProcessor.setJobRegistry(jobRegistry);
		return postProcessor;
	}

	@Bean
	public SimpleJobOperator jobOperator() throws Exception {

		SimpleJobOperator jobOperator = new SimpleJobOperator();
		jobOperator.setJobExplorer(jobExplorer());
		jobOperator.setJobRepository(jobRepository());
		jobOperator.setJobRegistry(jobRegistry());
		jobOperator.setJobLauncher(jobLauncher());

		return jobOperator;
	}
	/*
	 * @Bean public DataSource dataSource() { EmbeddedDatabaseBuilder
	 * embeddedDatabaseBuilder = new EmbeddedDatabaseBuilder(); return
	 * embeddedDatabaseBuilder.addScript(
	 * "classpath:org/springframework/batch/core/schema-drop-h2.sql")
	 * .addScript("classpath:org/springframework/batch/core/schema-h2.sql").
	 * addScript("classpath:employee.sql")
	 * .setType(EmbeddedDatabaseType.H2).build(); }
	 */
}
