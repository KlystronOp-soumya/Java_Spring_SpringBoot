package com.demo.batch.listeners;

import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;

public class CustomJobListener implements JobExecutionListener {

	@Override
	public void beforeJob(JobExecution jobExecution) {
		// TODO Auto-generated method stub

	}

	@Override
	public void afterJob(JobExecution jobExecution) {
		// TODO Auto-generated method stub
		if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
			// job success
		} else if (jobExecution.getStatus() == BatchStatus.FAILED) {
			// job failure
		}

	}

}
