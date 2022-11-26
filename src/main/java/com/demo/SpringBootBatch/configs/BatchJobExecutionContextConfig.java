package com.demo.SpringBootBatch.configs;

import javax.sql.DataSource;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.JobRepositoryFactoryBean;
import org.springframework.batch.support.transaction.ResourcelessTransactionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.cloud.task.configuration.EnableTask;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@EnableBatchProcessing
@EnableTask
@Import({ BatchJobConfig.class })
@PropertySources({ @PropertySource("classpath:db-config.properties") })
public class BatchJobExecutionContextConfig {

	@Autowired
	private transient Environment env;

	@Bean
	public ThreadPoolTaskExecutor threadPoolTaskExecutor() {
		ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
		threadPoolTaskExecutor.setCorePoolSize(2);
		// threadPoolTaskExecutor.setMaxPoolSize(6);
		// threadPoolTaskExecutor.setThreadPriority(0);
		threadPoolTaskExecutor.setThreadGroupName("DB_TO_CSV_THREADS");
		threadPoolTaskExecutor.afterPropertiesSet();

		return threadPoolTaskExecutor;
	}

	@Bean
	public JobRepository jobRepository() throws Exception {

		JobRepositoryFactoryBean jobRepositoryFactoryBean = new JobRepositoryFactoryBean();
		jobRepositoryFactoryBean.setDatabaseType("mysql");
		jobRepositoryFactoryBean.setDataSource(this.mysqlDataSource());
		jobRepositoryFactoryBean.setTransactionManager(transactionManager());
		jobRepositoryFactoryBean.afterPropertiesSet();

		return (JobRepository) jobRepositoryFactoryBean.getObject();
	}

	@Bean
	public SimpleJobLauncher jobLauncher() throws Exception {
		SimpleJobLauncher simpleJobLauncher = new SimpleJobLauncher();
		simpleJobLauncher.setTaskExecutor(threadPoolTaskExecutor());
		simpleJobLauncher.setJobRepository(this.jobRepository());
		simpleJobLauncher.afterPropertiesSet();

		return simpleJobLauncher;
	}

	@Bean(name = "mysql_ds")
	@Primary
	public DataSource mysqlDataSource() {

		DataSourceBuilder<?> dataSourceBuilder = DataSourceBuilder.create();
		dataSourceBuilder.driverClassName(this.env.getProperty("mysql.driver"));
		dataSourceBuilder.url(this.env.getProperty("mysql.url"));
		dataSourceBuilder.username(this.env.getProperty("mysql.user"));
		dataSourceBuilder.password(this.env.getProperty("mysql.password"));

		return dataSourceBuilder.build();

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

	/*
	 * @Bean public DataSource dataSource() { EmbeddedDatabaseBuilder
	 * embeddedDatabaseBuilder = new EmbeddedDatabaseBuilder(); return
	 * embeddedDatabaseBuilder.addScript(
	 * "classpath:org/springframework/batch/core/schema-drop-h2.sql")
	 * .addScript("classpath:org/springframework/batch/core/schema-h2.sql").
	 * addScript("classpath:employee.sql")
	 * .setType(EmbeddedDatabaseType.H2).build(); }
	 */
	@Bean
	public JdbcTemplate jdbcTemplate() {
		return new JdbcTemplate(this.mysqlDataSource());
	}

	/*
	 * @Bean public JdbcCursorItemReader<Associates> associateItemReader() {
	 * 
	 * JdbcCursorItemReader<Associates> associateItemReader = new
	 * JdbcCursorItemReader<Associates>();
	 * associateItemReader.setDataSource(this.mysqlDataSource());
	 * 
	 * return associateItemReader; }
	 */

	/*
	 * @Bean public FlatFileItemWriter<AssociateDebit> associateDebitItemWirter() {
	 * return new
	 * FlatFileItemWriterBuilder<AssociateDebit>().name("associateDebitWriter").
	 * build(); }
	 */

	/*
	 * @Bean public BeanWrapperFieldExtractor<AssociateDebit>
	 * associateBeanFieldSeExtractor() { return new
	 * BeanWrapperFieldExtractor<AssociateDebit>(); }
	 */

	/*
	 * @Bean public LineAggregator<AssociateDebit> delimitedLineAggregator() {
	 * return new DelimitedLineAggregator<AssociateDebit>(); }
	 */

}
