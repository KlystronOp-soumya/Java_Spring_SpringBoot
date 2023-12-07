package com.demo.springbatch.listeners;

import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobListener;

public class QrtzJobListener implements JobListener {

	public String getName() {
		
		return "daily_Demo_Job_Listener";
	}

	public void jobToBeExecuted(JobExecutionContext context) {
		JobDataMap jobDataMap = context.getJobDetail().getJobDataMap() ;
		System.out.println("Job to be executed: " + jobDataMap.getString("QUARTZ_JOB_NAME"));
		System.out.println("Job group: " + jobDataMap.getString("QUARTZ_JOB_GROUP"));

	}

	public void jobExecutionVetoed(JobExecutionContext context) {
		

	}

	public void jobWasExecuted(JobExecutionContext context, JobExecutionException jobException) {
		
		JobDetail jobDetail = context.getJobDetail() ;
		System.out.println("Job was executed: " +context.getPreviousFireTime() );
		System.out.println("Job will be executed next: " + context.getNextFireTime() );

	}

}
