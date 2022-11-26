package com.Demo.SpringBatch_CsvToDB.utility.deciders;

import java.net.URL;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.job.flow.FlowExecutionStatus;
import org.springframework.batch.core.job.flow.JobExecutionDecider;

import com.Demo.SpringBatch_CsvToDB.utility.Constants;
import com.Demo.SpringBatch_CsvToDB.utility.listeners.CsvToDbStepListener;

public class CsvToDbDecider implements JobExecutionDecider {

	public FlowExecutionStatus decide(JobExecution jobExecution, StepExecution stepExecution) {
		// TODO Auto-generated method stub
		if (isCsvExists()) {
			return new FlowExecutionStatus("INPUT_CSV_EXISTS");
		} else
			return new FlowExecutionStatus("INPUT_CSV_ABSCENT");

	}

	private boolean isCsvExists() {
		URL url = CsvToDbStepListener.class.getClassLoader().getResource(Constants.INPUT_CSV_PATH.getValue());

		return (url != null) ? true : false;
	}
}
