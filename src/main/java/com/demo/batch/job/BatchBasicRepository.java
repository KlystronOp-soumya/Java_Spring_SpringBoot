//package com.demo.batch.job;
//
//import org.springframework.batch.core.configuration.annotation.BatchConfigurer;
//import org.springframework.batch.core.explore.JobExplorer;
//import org.springframework.batch.core.explore.support.JobExplorerFactoryBean;
//import org.springframework.batch.core.launch.JobLauncher;
//import org.springframework.batch.core.launch.support.SimpleJobLauncher;
//import org.springframework.batch.core.repository.JobRepository;
//import org.springframework.batch.core.repository.dao.Jackson2ExecutionContextStringSerializer;
//import org.springframework.batch.core.repository.support.JobRepositoryFactoryBean;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.transaction.PlatformTransactionManager;
//
//import com.demo.batch.config.DataSourceConfig;
//
//@Configuration
//public class BatchBasicRepository implements BatchConfigurer {
//
//	@Autowired
//	private DataSourceConfig dataSource;
//
//	@Autowired
//	private PlatformTransactionManager platformTransactionManager;
//
//	@Override
//	public JobRepository getJobRepository() throws Exception {
//		// TODO Auto-generated method stub
//		return createJobRepository();
//	}
//
//	@Override
//	public PlatformTransactionManager getTransactionManager() throws Exception {
//		// TODO Auto-generated method stub
//		return this.platformTransactionManager;
//	}
//
//	@Override
//	public JobLauncher getJobLauncher() throws Exception {
//		// TODO Auto-generated method stub
//		return createJobLauncher();
//	}
//
//	@Override
//	public JobExplorer getJobExplorer() throws Exception {
//		// TODO Auto-generated method stub
//		JobExplorerFactoryBean factoryBean = new JobExplorerFactoryBean();
//		factoryBean.setDataSource(this.dataSource.dataSourceMySql());
//		factoryBean.setJdbcOperations(new JdbcTemplate(this.dataSource.dataSourceMySql()));
//		factoryBean.setSerializer(new Jackson2ExecutionContextStringSerializer());
//		// factoryBean.afterPropertiesSet();
//		factoryBean.setTablePrefix("BATCH_");
//		return factoryBean.getObject();
//	}
//
//	private JobRepository createJobRepository() throws Exception {
//		JobRepositoryFactoryBean factory = new JobRepositoryFactoryBean();
//		factory.setDataSource(this.dataSource.dataSourceMySql());
//		factory.setDatabaseType("mysql");
//		factory.setTransactionManager(getTransactionManager());
//		factory.setJdbcOperations(new JdbcTemplate(this.dataSource.dataSourceMySql()));
//		factory.setSerializer(new Jackson2ExecutionContextStringSerializer());
//		// factory.afterPropertiesSet();
//
//		factory.setTablePrefix("BATCH_");
//		factory.setMaxVarCharLength(1000);
//
//		return factory.getObject();
//	}
//
//	private JobLauncher createJobLauncher() throws Exception {
//		SimpleJobLauncher jobLauncher = new SimpleJobLauncher();
//		jobLauncher.setJobRepository(getJobRepository());
//		// jobLauncher.afterPropertiesSet();
//		return jobLauncher;
//	}
//
//}
