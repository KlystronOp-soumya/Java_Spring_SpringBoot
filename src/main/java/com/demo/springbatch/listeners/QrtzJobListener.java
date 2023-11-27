package com.demo.springbatch.listeners;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobListener;

public class QrtzJobListener implements JobListener {

	public String getName() {
		// TODO Auto-generated method stub
		return "daily_Demo_Job_Listener";
	}

	public void jobToBeExecuted(JobExecutionContext context) {
		// TODO Auto-generated method stub

	}

	public void jobExecutionVetoed(JobExecutionContext context) {
		// TODO Auto-generated method stub

	}

	public void jobWasExecuted(JobExecutionContext context, JobExecutionException jobException) {
		// TODO Auto-generated method stub

	}

}
