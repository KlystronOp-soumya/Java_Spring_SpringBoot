package com.demo.taskdemo;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component("batchRunner")
public class BatchRunner implements CommandLineRunner {

	private static final Logger LOGGER = LogManager.getLogger(BatchRunner.class);

	@Autowired
	private JobLauncher jobLauncher;

	@Autowired
	private Job job;

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		LOGGER.info("Running Batch Job");
		Map<String, JobParameter> jobParams = new HashMap<String, JobParameter>();
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("HH-MM-SS");
		JobParameters jobParameters;
		try {
			LOGGER.info("Setting Job parameters");
			jobParams.put("name", new JobParameter("batchTaskDemoApp"));
			jobParams.put("region", new JobParameter(TimeZone.getTimeZone("Asia/Kolkata").toString()));
			jobParams.put("time", new JobParameter(dateFormat.format(date)));

			jobParameters = new JobParameters(jobParams);

			JobExecution jobExecution = jobLauncher.run(job, jobParameters);

			if (jobExecution.getExitStatus() == ExitStatus.FAILED)
				throw new RuntimeException("There was a problem during Job execution");

			LOGGER.info("Job execution completed");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}
