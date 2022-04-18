package com.demo.batch.BatchDemo.listeners;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.stereotype.Component;

@Component
public class CustomJobLoggerListener implements JobExecutionListener {

	@Override
	public void beforeJob(JobExecution jobExecution) {
		// TODO Auto-generated method stub
		System.out.println("Job to be executed: " + jobExecution.getJobInstance().getJobName());
		// System.out.println("Current Job parameters are :
		// "+jobExecution.getJobParameters().getString());
	}

	@Override
	public void afterJob(JobExecution jobExecution) {
		// TODO Auto-generated method stub
		// this will be called always
		// any kind of clean ups or reset can be done here
	}

}
