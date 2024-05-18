package com.demo.quartzdemo.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.demo.quartzdemo.tasklets.TaskOne;
import com.demo.quartzdemo.tasklets.TaskTwo;

@Configuration
public class BatchJobConfig {

	@Autowired
	private JobBuilderFactory jobBuilderFactory;

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Bean(name = "TaskOneStep")
	public Step taskOneStep() {
		return this.stepBuilderFactory.get("Step_TaskOne").tasklet(new TaskOne()).build();
	}

	@Bean(name = "TaskTwoStep")
	public Step taskTwoStep() {
		return this.stepBuilderFactory.get("Step_TaskTwo").tasklet(new TaskTwo()).build();
	}

	@Bean(name = "demoJobOneBean")
	public Job demoJobOne() {
		return this.jobBuilderFactory.get("demoJobOne").start(taskOneStep()).next(taskTwoStep()).build();
	}

	/*@Bean(name = "demoJonTwoBean")
	public Job demoJobTwo() {
		return this.jobBuilderFactory.get("demoJobTwo").flow(taskTwoStep()).build().build();
	}*/
}
