package com.demo.batch.config;

import java.time.LocalDateTime;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.job.flow.FlowExecutionStatus;
import org.springframework.batch.core.job.flow.JobExecutionDecider;

//@Component("deliveryTimeDecider")
public class DeliveryDecider implements JobExecutionDecider {

	@Override
	public FlowExecutionStatus decide(JobExecution jobExecution, StepExecution stepExecution) {
		// TODO Auto-generated method stub
		String result = LocalDateTime.now().getHour() < 12 ? "PRESENT" : "NOT PRESENT";
		return new FlowExecutionStatus(result);
	}

}
