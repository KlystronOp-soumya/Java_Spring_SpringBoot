package com.demo;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class RunApp {

	public static void main(String[] args) {
		System.out.println("Hello World");
		System.out.println("Loading classpathxml");
		ClassPathXmlApplicationContext applicationContext = null, batchJobApplicationContext = null;
		JobExecution execution = null;
		try {
			applicationContext = new ClassPathXmlApplicationContext("resources/contexts/appcontext/appContext.xml");
			// get the batch job context
			batchJobApplicationContext = new ClassPathXmlApplicationContext(
					"resources/contexts/batchcontext/batch-job-config.xml");

			JobLauncher jobLauncher = (JobLauncher) batchJobApplicationContext.getBean("jobLauncher");

			Job job = (Job) batchJobApplicationContext.getBean("parcelDeliveryMangamentJob");

			JobParameters jobParameters = new JobParametersBuilder().addLong("batchTime", System.currentTimeMillis())
					.toJobParameters();

			JobExplorer jobExplorer = (JobExplorer) batchJobApplicationContext.getBean("jobExplorer");

			execution = jobLauncher.run(job, jobParameters);

			if (!execution.isRunning()) {
				if (execution.getExitStatus() == ExitStatus.FAILED) {
					System.exit(-1);
				}
			} else {
				System.out.println(
						"Job: " + execution.getJobInstance().getJobName() + " completed: " + execution.getExitStatus());
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			applicationContext.close();
			batchJobApplicationContext.close();
		}
	}

}
