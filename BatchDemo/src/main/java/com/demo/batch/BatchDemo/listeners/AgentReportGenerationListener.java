package com.demo.batch.BatchDemo.listeners;

import java.io.File;
import java.io.FileNotFoundException;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;

import com.demo.batch.BatchDemo.enums.Constants;

public class AgentReportGenerationListener implements StepExecutionListener {

	@Override
	public void beforeStep(StepExecution stepExecution) {
		// TODO Auto-generated method stub
		// Before executing the Step check if the path exists
		try {
			File file = new File(Constants.EXCEL_REPORT_PATH.value);
			if (!file.exists())
				throw new FileNotFoundException();
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	@Override
	public ExitStatus afterStep(StepExecution stepExecution) {
		// TODO Auto-generated method stub
		// check whether the file was created
		try {
			File file = new File(Constants.EXCEL_REPORT_PATH.value);
			if (file.exists())
				return ExitStatus.COMPLETED;

			// if the file was created successfully
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return ExitStatus.FAILED;
	}

}
