package com.demo.taskdemo;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.task.configuration.EnableTask;
import org.springframework.cloud.task.configuration.TaskConfigurer;
import org.springframework.cloud.task.configuration.TaskLifecycleConfiguration;
import org.springframework.cloud.task.repository.TaskExplorer;
import org.springframework.cloud.task.repository.TaskRepository;
import org.springframework.cloud.task.repository.support.SimpleTaskExplorer;
import org.springframework.cloud.task.repository.support.SimpleTaskRepository;
import org.springframework.cloud.task.repository.support.TaskExecutionDaoFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration(proxyBeanMethods = false)
@EnableTask
@EnableBatchProcessing
@PropertySource(value = "classpath:db-config.properties")
@Import(value = BatchConfig.class)
public class AppConfig {

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Autowired
	private JobBuilderFactory jobBuilderFactory;

	@Autowired
	@Qualifier("dataSourceBean")
	private DataSource dataSource;

	@Autowired
	@Qualifier("txManager")
	private PlatformTransactionManager txManager;

	/*
	 * @Override public DataSource getTaskDataSource() { return this.dataSource; }
	 * 
	 * @Override public PlatformTransactionManager getTransactionManager() { return
	 * this.txManager; }
	 */
	@Bean
	public TaskExplorer taskExplorer() {
		return new SimpleTaskExplorer(new TaskExecutionDaoFactoryBean(dataSource));
	}

	@Bean
	public TaskRepository taskRepository() {
		return new SimpleTaskRepository(new TaskExecutionDaoFactoryBean(dataSource));
	}

	TaskLifecycleConfiguration taskLifecycleConfiguration;

	@Bean("customTaskConfigurer")
	public TaskConfigurer taskConfigurer() {
		CustomTask customTask = new CustomTask(dataSource, txManager, new TaskExecutionDaoFactoryBean(dataSource));
		return customTask;
	}

	@Bean(name = "batchTaskStepBean")
	public Step batchTaskStep() {
		return this.stepBuilderFactory.get("step_batchTaskDemo").<String, String>chunk(10)
				.reader(new ItemReader<String>() {

					@Override
					public String read()
							throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
						// TODO Auto-generated method stub
						System.out.println("Step :: Reading");
						return "Reading";
					}
				}).writer(new ItemWriter<String>() {

					@Override
					public void write(List<? extends String> items) throws Exception {
						// TODO Auto-generated method stub
						System.out.println("Step :: Writing\n");
						for (String string : items)
							System.out.println("Received: " + string);

					}
				}).processor(new ItemProcessor<String, String>() {

					@Override
					public String process(String item) throws Exception {
						// TODO Auto-generated method stub
						System.out.println("Received:: " + item);
						return "Processed";
					}
				}).build();
	}

	@Bean
	public Step batchTaskDemoStep() {
		return this.stepBuilderFactory.get("step_batchTaskDemoTasklet").tasklet(new Tasklet() {

			@Override
			public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
				// TODO Auto-generated method stub
				System.out.println("Tasklet executed");

				return RepeatStatus.FINISHED;
			}
		}).build();
	}

	@Bean("batchTaskDemo_Job")
	public Job job() {
		return this.jobBuilderFactory.get("batchTaskDemo_Job").start(this.batchTaskDemoStep()).build();
	}
}
