package com.demo.springbatch.jobconfig;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.configuration.JobLocator;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.NoSuchJobException;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.scheduling.quartz.QuartzJobBean;

public class LaunchJob extends QuartzJobBean {

	static final String JOB_NAME = "demoDailyJob";

	  private JobLocator jobLocator;

	  private JobLauncher jobLauncher;

	  public void setJobLocator(JobLocator jobLocator) {
		this.jobLocator = jobLocator;
	  }

	  public void setJobLauncher(JobLauncher jobLauncher) {
		this.jobLauncher = jobLauncher;
	  }

	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {

		//Get the job
		org.springframework.batch.core.Job job = null;
		//Build the job parameter
		JobParametersBuilder jobParametersBuilder = new JobParametersBuilder() ;
		JobParameters jobParameters = jobParametersBuilder.addLong("TIME_OF_EXEC", System.currentTimeMillis()).addString("JOB_NAME", JOB_NAME).toJobParameters() ;	
		try {
			job = this.jobLocator.getJob(JOB_NAME);
			JobExecution jobExecution = this.jobLauncher.run(job, jobParameters) ;
			do
			{
				if(jobExecution.getExitStatus() == ExitStatus.FAILED)
					System.exit(-1);
				if(jobExecution.getExitStatus() == ExitStatus.COMPLETED)
				{
					System.out.println("Job was completed");
					break;
				}
					
				
			}while(!jobExecution.isRunning()) ;
			
			
		} catch (NoSuchJobException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JobExecutionAlreadyRunningException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JobRestartException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JobInstanceAlreadyCompleteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JobParametersInvalidException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			
		}
		
	}

}
