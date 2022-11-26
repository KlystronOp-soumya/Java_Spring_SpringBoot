package com.Demo.SpringBatch_CsvToDB.utility.listeners;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;

public class CsvToDbJobListener implements JobExecutionListener {

	public void beforeJob(JobExecution jobExecution) {
		// TODO Auto-generated method stub
		// check if the file exists before executing the Job
		System.out.println("Starting the job: Creation Time: " + jobExecution.getCreateTime());

	}

	public void afterJob(JobExecution jobExecution) {
		// TODO Auto-generated method stub
		System.out.println("After Job: ");
		System.out.println("Job Exit Status: " + jobExecution.getExitStatus() + "\nJob Start Time: "
				+ jobExecution.getStartTime() + "" + "\nJob Status:" + jobExecution.getStatus() + "\n Job End Time: "
				+ jobExecution.getEndTime());

	}

}
