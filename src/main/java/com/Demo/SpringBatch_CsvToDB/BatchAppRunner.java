package com.Demo.SpringBatch_CsvToDB;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

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

import com.Demo.SpringBatch_CsvToDB.utility.CustomException;

@Component
public class BatchAppRunner implements CommandLineRunner {
	/* The Logger */
	private static final Logger LOGGER = LogManager.getLogger(BatchAppRunner.class);

	// Inject dependencies
	@Autowired
	private JobLauncher jobLauncher;

	@Autowired
	private transient Job job;

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		LOGGER.info("Running the batch in CommandLineRunner");
		Map<String, JobParameter> jobParam = null;
		try {
			jobParam = new HashMap<String, JobParameter>();
			jobParam.put("Location", new JobParameter(Calendar.getInstance().getTimeZone().toString()));
			jobParam.put("currentTime", new JobParameter(System.currentTimeMillis()));

			LOGGER.info("job parameters created");

			JobExecution jobExecution = jobLauncher.run(job, new JobParameters(jobParam));

			if (jobExecution.getExitStatus() == ExitStatus.FAILED)
				throw new CustomException("Step has been failed");

			LOGGER.debug("Job has been executed!!");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			System.exit(-1);
		}
	}

}
