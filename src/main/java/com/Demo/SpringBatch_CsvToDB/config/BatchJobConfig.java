package com.Demo.SpringBatch_CsvToDB.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.flow.JobExecutionDecider;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.Demo.SpringBatch_CsvToDB.domain.Associates;
import com.Demo.SpringBatch_CsvToDB.utility.CSVProcessor;
import com.Demo.SpringBatch_CsvToDB.utility.CSVReader;
import com.Demo.SpringBatch_CsvToDB.utility.CSVToDbWriter;
import com.Demo.SpringBatch_CsvToDB.utility.CustomException;
import com.Demo.SpringBatch_CsvToDB.utility.deciders.CsvToDbDecider;
import com.Demo.SpringBatch_CsvToDB.utility.listeners.CsvToDbJobListener;
import com.Demo.SpringBatch_CsvToDB.utility.listeners.CsvToDbStepListener;

/*
 * This class configures all the Steps and Job
 * 
 * */

@Configuration
public class BatchJobConfig {

	@Autowired
	private JobBuilderFactory jobBuilderFactory;

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Bean
	public CSVReader csvReader() {
		return new CSVReader();
	}

	@Bean
	public CSVProcessor csvProcessor() {
		return new CSVProcessor();
	}

	@Bean
	public CSVToDbWriter csvToDbWriter() {
		return new CSVToDbWriter();
	}

	@Bean
	public StepExecutionListener stepListener() {
		return new CsvToDbStepListener();
	}

	@Bean
	public JobExecutionListener jobListener() {
		return new CsvToDbJobListener();
	}

	@Bean
	public JobExecutionDecider jobDecider() {
		return new CsvToDbDecider();
	}

	@Bean
	public Step step() throws CustomException {
		return stepBuilderFactory.get("Step_CSV_TO_DB_LOADING").<Associates, Associates>chunk(20)
				.reader(this.csvReader().csvItemReader()).processor(csvProcessor())
				.writer(this.csvToDbWriter().itemWriter()).listener(stepListener()).build();
	}

	@Bean
	public Job readCSVFileJob() throws CustomException {
		return jobBuilderFactory.get("readCSVFileJob").incrementer(new RunIdIncrementer()).start(this.step())
				.listener(jobListener()).build();
	}

}
