package com.demo.batch.config;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.job.flow.FlowExecutionStatus;
import org.springframework.batch.core.job.flow.JobExecutionDecider;

//@Component("itemAcceptDecider")
public class PackageAcceptanceDecider implements JobExecutionDecider {

	@Override
	public FlowExecutionStatus decide(JobExecution jobExecution, StepExecution stepExecution) {
		// TODO Auto-generated method stub
		// boolean isRightItem = new Random().nextFloat() < 0.7f ? true : false;
		boolean isRightItem = true;
		if (isRightItem)
			return new FlowExecutionStatus("ACCEPTED");
		else {
			return new FlowExecutionStatus("REJECTED");
		}
	}

}
