package com.demo.SpringBootBatch.listeners;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;

public class DbToCSV_JobListener implements JobExecutionListener {
	/* The Logger */
	private static final Logger LOGGER = LogManager.getLogger(DbToCSV_JobListener.class);

	@Override
	public void beforeJob(JobExecution jobExecution) {
		// TODO Auto-generated method stub
		LOGGER.info("Before Job Listener");
	}

	@Override
	public void afterJob(JobExecution jobExecution) {
		// TODO Auto-generated method stub
		LOGGER.info("After Job Listener");
		System.out.println("Job Exit Status: " + jobExecution.getExitStatus() + "\nJob Start Time: "
				+ jobExecution.getStartTime() + "" + "\nJob Status:" + jobExecution.getStatus() + "\n Job End Time: "
				+ jobExecution.getEndTime());
	}

}
