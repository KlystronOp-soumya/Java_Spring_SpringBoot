package com.linkedin.batch;

/*
 * To run this commandLineRunner 
 * run the golas: mvn clean compile package
 * navigate to the target folder then 
 * type: java jar "jar name" MyBatchRunner **provide the batch parameters if required
 * 
 * 
 * */
import java.util.HashMap;
import java.util.Map;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class MyBatchRunner implements CommandLineRunner {

	@Autowired
	JobLauncher jobLauncher;
	@Autowired
	Job job;

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		Map<String, JobParameter> maps = new HashMap<>();
		maps.put("time", new JobParameter(System.currentTimeMillis()));
		JobParameters parameters = new JobParameters(maps);
		JobExecution jobExecution = jobLauncher.run(job, parameters);// here the job triggers the SpringBatchJobConfig
																		// file.
		// the inbuilt job config is disabled

		System.out.println("JobExecution: " + jobExecution.getStatus());

		System.out.println("Batch is Running...");
		while (jobExecution.isRunning()) {
			System.out.println("...");
		}

		// return jobExecution.getStatus();

	}

}
