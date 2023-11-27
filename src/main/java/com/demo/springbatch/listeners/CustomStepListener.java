package com.demo.springbatch.listeners;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;

public class CustomStepListener implements StepExecutionListener {

	public void beforeStep(StepExecution stepExecution) {
		// TODO Auto-generated method stub

	}

	public ExitStatus afterStep(StepExecution stepExecution) {
		// TODO Auto-generated method stub
		return null;
	}

}
