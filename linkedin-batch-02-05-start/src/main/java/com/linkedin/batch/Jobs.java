package com.linkedin.batch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParametersIncrementer;
import org.springframework.batch.core.JobParametersValidator;

public class Jobs implements Job {

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isRestartable() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void execute(JobExecution execution) {
		// TODO Auto-generated method stub

	}

	@Override
	public JobParametersIncrementer getJobParametersIncrementer() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JobParametersValidator getJobParametersValidator() {
		// TODO Auto-generated method stub
		return null;
	}

}
