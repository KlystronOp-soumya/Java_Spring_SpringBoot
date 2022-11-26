package com.Demo.SpringBatch_CsvToDB.utility.listeners;

import java.net.URL;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;

import com.Demo.SpringBatch_CsvToDB.utility.Constants;
import com.Demo.SpringBatch_CsvToDB.utility.CustomException;

public class CsvToDbStepListener implements StepExecutionListener {

	public void beforeStep(StepExecution stepExecution) {
		// TODO Auto-generated method stub
		if (!isCsvExists()) // this check is for demo purpose. Check has been done using the decider
			try {
				stepExecution.setExitStatus(ExitStatus.FAILED);
				throw new CustomException("CSV is not present ");
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}

	}

	public ExitStatus afterStep(StepExecution stepExecution) {
		// TODO Auto-generated method stub
		if (stepExecution.getExitStatus() == ExitStatus.FAILED)
			return ExitStatus.FAILED;
		else {
			return ExitStatus.COMPLETED;
		}

	}

	private boolean isCsvExists() {
		URL url = CsvToDbStepListener.class.getClassLoader().getResource(Constants.INPUT_CSV_PATH.getValue());

		return (url != null) ? true : false;
	}
}
