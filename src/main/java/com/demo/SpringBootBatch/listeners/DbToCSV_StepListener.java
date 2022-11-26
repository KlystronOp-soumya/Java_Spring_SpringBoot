package com.demo.SpringBootBatch.listeners;

import java.net.URL;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;

import com.demo.SpringBootBatch.exceptions.CustomException;
import com.demo.SpringBootBatch.utility.Constants;

public class DbToCSV_StepListener implements StepExecutionListener {

	@Override
	public void beforeStep(StepExecution stepExecution) {
		// TODO Auto-generated method stub
		System.out.println("Before executing step: " + stepExecution.getStepName());
		if (!isCsvExists())
			try {
				stepExecution.setExitStatus(ExitStatus.FAILED);
				throw new CustomException("CSV is not present ");
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
	}

	@Override
	public ExitStatus afterStep(StepExecution stepExecution) {
		// TODO Auto-generated method stub
		System.out.println("After the Step: " + stepExecution.getStepName());
		if (stepExecution.getExitStatus() == ExitStatus.FAILED)
			return ExitStatus.FAILED;
		else {
			return ExitStatus.COMPLETED;
		}
	}

	private boolean isCsvExists() {
		URL url = DbToCSV_StepListener.class.getClassLoader().getResource(Constants.OUTPUT_CSV_PATH.getValue());

		return (url != null) ? true : false;
	}
}
