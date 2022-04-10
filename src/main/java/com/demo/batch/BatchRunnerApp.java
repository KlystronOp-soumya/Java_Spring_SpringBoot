package com.demo.batch;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParametersIncrementer;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.demo.batch.config.CustomJobParameterConfig;

@Component
@EnableBatchProcessing
public class BatchRunnerApp implements CommandLineRunner {

	/* The Logger */
	private static final Logger LOGGER = LogManager.getLogger(BatchRunnerApp.class);
	@Autowired
	JobLauncher jobLauncher;

	@Autowired
	Job job;

	@Autowired
	private CustomJobParameterConfig jobparameters;

	@SuppressWarnings("unused")
	@Autowired
	private JobParametersIncrementer jobParametersIncrementer;

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		try {
			LOGGER.info("Job was started....");
			JobExecution jobExecution = jobLauncher.run(job, this.jobparameters.getCustomJobParameters());
			LOGGER.info("Job Starting time: " + jobExecution.getStartTime());
			LOGGER.info("Current Execution Status Of Job: " + jobExecution.getStatus());

			while (jobExecution.isRunning()) {
				System.out.println("....");
			}
			LOGGER.info("Job Ending time: " + jobExecution.getEndTime());
			LOGGER.info("Current Execution Status Of Job: " + jobExecution.getStatus());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

}
