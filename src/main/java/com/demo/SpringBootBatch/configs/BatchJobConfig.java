package com.demo.SpringBootBatch.configs;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.demo.SpringBootBatch.entities.AssociateDebit;
import com.demo.SpringBootBatch.entities.Associates;
import com.demo.SpringBootBatch.exceptions.CustomException;
import com.demo.SpringBootBatch.listeners.DbToCSV_JobListener;
import com.demo.SpringBootBatch.listeners.DbToCSV_StepListener;
import com.demo.SpringBootBatch.processors.AssociateDebitProcessor;
import com.demo.SpringBootBatch.readers.AssociateReader;
import com.demo.SpringBootBatch.writers.AssociateDebitItemWriter;

@Configuration
@ComponentScan(basePackages = "com.demo.SpringBootBatch")
public class BatchJobConfig {

	private JobBuilderFactory jobBuilderFactory;
	private StepBuilderFactory stepBuilderFactory;
	// private AssociateReader associateReader;

	@Autowired
	public void setJobBuilderFactory(JobBuilderFactory jobBuilderFactory) {
		this.jobBuilderFactory = jobBuilderFactory;
	}

	@Autowired
	public void setStepBuilderFactory(StepBuilderFactory stepBuilderFactory) {
		this.stepBuilderFactory = stepBuilderFactory;
	}

	// Configure the custom beans

	@Bean
	public AssociateReader associateReader() {
		return new AssociateReader();
	}

	/*
	 * @Autowired public void setAssociateReader(AssociateReader associateReader) {
	 * this.associateReader = associateReader; }
	 */

	@Bean
	public AssociateDebitProcessor associateDebitProcessor() {
		return new AssociateDebitProcessor();
	}

	@Bean
	public AssociateDebitItemWriter associateDebitWriter() {
		return new AssociateDebitItemWriter();
	}

	@Bean
	public JobExecutionListener jobListener() {
		return new DbToCSV_JobListener();
	}

	@Bean
	public StepExecutionListener stepListener() {
		return new DbToCSV_StepListener();
	}

	@Bean
	public Step step() throws CustomException {
		return this.stepBuilderFactory.get("DB_TO_CSV_STEP").<Associates, AssociateDebit>chunk(50)
				.reader(this.associateReader().itemReader()).processor(this.associateDebitProcessor())
				.writer(this.associateDebitWriter().itemWriter()).listener(this.stepListener()).build();

	}

	@Bean
	public Job job() throws CustomException {
		return this.jobBuilderFactory.get("databaseToCSVJob").incrementer(new RunIdIncrementer()).start(this.step())
				.listener(this.jobListener()).build();
	}

}
