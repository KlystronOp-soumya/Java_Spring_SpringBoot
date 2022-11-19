package com.demo.batch.listeners;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.stereotype.Component;

@Component
public class CustomeStepListener implements StepExecutionListener {

	/* The Logger */
	private static final Logger LOGGER = LogManager.getLogger(CustomeStepListener.class);

	@Override
	public void beforeStep(StepExecution stepExecution) {
		// TODO Auto-generated method stub
		LOGGER.info("STEP TO BE EXECUTED: " + stepExecution.getStepName());

	}

	@Override
	public ExitStatus afterStep(StepExecution stepExecution) {
		// TODO Auto-generated method stub
		String stepName = stepExecution.getStepName();
		if (stepName.equalsIgnoreCase("givePackageToCustomer")) {
			System.out.println("Checking the package for any damage");
		}

		return new ExitStatus("VALID", "The valid order number");
	}

}
