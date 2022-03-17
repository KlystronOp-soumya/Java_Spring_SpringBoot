package com.linkedin.batch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
//added manually
@EnableBatchProcessing
@Configuration
@PropertySource("classpath:application.properties") 
/* public class LinkedinBatchApplication implements CommandLineRunner { */
	public class LinkedinBatchApplication{
	@Autowired
	public transient JobBuilderFactory jobBuilderFactory;
	
	@Autowired
	public transient StepBuilderFactory stepBuilderFactory;
	
	/*
	 * @Bean public DataSource dataSource() {
	 * 
	 * DriverManagerDataSource dataSource = new DriverManagerDataSource();
	 * dataSource.setDriverClassName("org.h2.Driver");
	 * dataSource.setUrl("jdbc:h2:~/mydb;DB_CLOSE_ON_EXIT=FALSE;AUTO_SERVER=TRUE");
	 * dataSource.setUsername("SA"); dataSource.setPassword("");
	 * 
	 * 
	 * return dataSource; }
	 */
	
	@Bean
	public Step packageItemStep()
	{
		return this.stepBuilderFactory.get("packageItemStep").tasklet(new Tasklet() {

			@Override
			public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
				// TODO Auto-generated method stub
				System.out.println("The item has been pakcaged");
				return RepeatStatus.FINISHED;
			}
			
		}).build();//or a class that implements the tasklet
	}
	
	@Bean
	public Step dispatchItemStep()
	{
		return this.stepBuilderFactory.get("dispatchItemStep").tasklet(new Tasklet() {

			@Override
			public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
				// TODO Auto-generated method stub
				System.out.println("The item has been dispatched");
				return RepeatStatus.FINISHED;
			}
			
		}).build();//or a class that implements the tasklet
	}
	
	@Bean
	public Job deliverPackageJob()
	{
		 
		return  this.jobBuilderFactory.get("deliverPackageJob").start(this.packageItemStep()).next(this.dispatchItemStep()).build() ;
	}
	public static void main(String[] args) {
		SpringApplication.run(LinkedinBatchApplication.class, args);
	}

	/*
	 * @Override public void run(String... args) throws Exception { // TODO
	 * Auto-generated method stub JobParameters jobParameters = new
	 * JobParametersBuilder().addLong("time",
	 * System.currentTimeMillis()).toJobParameters();
	 * jobLauncher.run(this.processJob, jobParameters);
	 * 
	 * }
	 */
}
