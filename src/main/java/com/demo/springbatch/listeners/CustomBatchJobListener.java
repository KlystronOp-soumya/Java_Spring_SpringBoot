package com.demo.springbatch.listeners;

import java.sql.SQLException;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

public class CustomBatchJobListener implements JobExecutionListener {

	
	
	public void beforeJob(JobExecution jobExecution) {
		// TODO Auto-generated method stub
		
	}

	public void afterJob(JobExecution jobExecution) {
		// TODO Auto-generated method stub

	}

}
