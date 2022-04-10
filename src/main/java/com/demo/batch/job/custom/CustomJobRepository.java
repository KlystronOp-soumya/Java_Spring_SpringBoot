package com.demo.batch.job.custom;

import org.springframework.batch.core.configuration.annotation.BatchConfigurer;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.JobRepositoryFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import com.demo.batch.config.DataSourceConfig;

@Configuration
public class CustomJobRepository {

	@Autowired
	private BatchConfigurer batchConfigurer;

	@Autowired
	private transient DataSourceConfig dataSource;

	@Autowired
	private transient PlatformTransactionManager platformTransactionManager;

	@Bean
	public JobRepository createJobRepository() throws Exception {
		JobRepositoryFactoryBean factory = new JobRepositoryFactoryBean();
		factory.setDataSource(this.dataSource.dataSourceMySql());
		factory.setTransactionManager(platformTransactionManager);
		factory.setIsolationLevelForCreate("ISOLATION_SERIALIZABLE");
		/* These values will be the default ones */
		// factory.setTablePrefix("BATCH_");
		// factory.setMaxVarCharLength(1000);
		return factory.getObject();
	}

	@Bean
	public JobLauncher getJobLauncher() throws Exception {
		// TODO Auto-generated method stub
		SimpleJobLauncher jobLauncher = new SimpleJobLauncher();
		jobLauncher.setJobRepository(createJobRepository());
		jobLauncher.afterPropertiesSet();
		return jobLauncher;
	}
}
