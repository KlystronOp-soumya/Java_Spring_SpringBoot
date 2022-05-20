package com.demo.batch.BatchDemo.listeners;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.beans.factory.annotation.Autowired;

public class StepListener implements StepExecutionListener {

	@Autowired
	private DataSource dataSource;

	@Override
	public void beforeStep(StepExecution stepExecution) {
		// TODO Auto-generated method stub
		System.out.println("Step to be executed: " + stepExecution.getStepName());

	}

	@Override
	public ExitStatus afterStep(StepExecution stepExecution) {
		// TODO Auto-generated method stub
		System.out.println("The step: " + stepExecution.getStepName() + " was successfully executed"
				+ stepExecution.getExitStatus());
		try {
			if (!this.dataSource.getConnection().isClosed())
				this.dataSource.getConnection().close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return stepExecution.getExitStatus();
	}

}
