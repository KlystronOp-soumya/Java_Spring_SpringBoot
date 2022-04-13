package com.demo.batch.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.batch.core.configuration.annotation.BatchConfigurer;
import org.springframework.batch.core.configuration.annotation.DefaultBatchConfigurer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.JobRepositoryFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CustomBatchBasicConfigurer extends DefaultBatchConfigurer {

	/* The Logger */
	private static final Logger LOGGER = LogManager.getLogger(CustomBatchBasicConfigurer.class);
	@Autowired
	private DataSourceConfig dataSource;

	/*
	 * The Bean annotation can not be put here To use a custom BatchConfigurer the
	 * context must contain precisely one, found 2 , was thrown
	 */
	public BatchConfigurer batchConfigurer() {
		return new DefaultBatchConfigurer(this.dataSource.dataSourceMySql());
	}

	@Override
	protected JobRepository createJobRepository() throws Exception {
		JobRepositoryFactoryBean factory = new JobRepositoryFactoryBean();
		LOGGER.info("Overridden CreateJobRepository");
		factory.setDataSource(this.dataSource.dataSourceH2());
		factory.setTransactionManager(batchConfigurer().getTransactionManager());
		factory.afterPropertiesSet();
		return factory.getObject();
	}
	// other methods can be overridden
}
