package com.demo.quartzdemo.config;

import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.configuration.JobLocator;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;


/*
 * This class is called in loop , multiple times by the scheduler
 * 
 * */
public class CustomQuartzJobConfig extends QuartzJobBean {
	private String jobName;
	private JobLauncher jobLauncher;
	private JobLocator jobLocator;
	
	@Autowired
	private transient SchedulerFactoryBean schedulerFactoryBean ;
	
	
	
	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public JobLauncher getJobLauncher() {
		return jobLauncher;
	}

	public void setJobLauncher(JobLauncher jobLauncher) {
		this.jobLauncher = jobLauncher;
	}

	public JobLocator getJobLocator() {
		return jobLocator;
	}

	public void setJobLocator(JobLocator jobLocator) {
		this.jobLocator = jobLocator;
	}

	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		
		try {
			Job job = jobLocator.getJob(jobName);
			JobDataMap jobDataMap = context.getMergedJobDataMap() ;
			
			JobParameters params = new JobParametersBuilder()
					.addString("JobID", String.valueOf(System.currentTimeMillis())).toJobParameters();
			//If no job or nothing is triggered , nonetheless this method will be executed
			//jobLauncher.run(job, params);
			
			
	
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
