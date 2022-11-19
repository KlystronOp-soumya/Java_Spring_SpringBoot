package com.demo.batch;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.demo.batch.config.CustomJobParameterConfig;

@Component
public class BatchRunnerApp implements CommandLineRunner {

	/* The Logger */
	private static final Logger LOGGER = LogManager.getLogger(BatchRunnerApp.class);
	@Autowired
	JobLauncher jobLauncher;

	@Autowired
	@Qualifier("carManufacturingJob")
	Job job; // Bean was created in the BatchJobConfig

	@Autowired
	private CustomJobParameterConfig jobparameters;

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub

		/*
		 * ApplicationContext context = new
		 * AnnotationConfigApplicationContext(BatchJobConfig.class); Job myJob; try {
		 * 
		 * List<String> jobNames = new ArrayList<>(); jobNames.add("deliverPackageJob");
		 * jobNames.add("carManufactureJob");
		 * 
		 * for (String eachJob : jobNames) { if (context.containsBean(eachJob)) { myJob
		 * = (Job) context.getBean(eachJob);
		 */

		// execute the Job
		try {
			// ApplicationContext context = new
			// AnnotationConfigApplicationContext(BatchJobConfig.class);
			LOGGER.info("Job was started....");
			LOGGER.info("Job Name: " + job.getName());

			JobExecution jobExecution = jobLauncher.run(job, this.jobparameters.getCustomJobParameters());

			LOGGER.info("Job name: " + job.getName());
			LOGGER.info("Job Starting time: " + jobExecution.getStartTime());
			LOGGER.info("Current Execution Status Of Job: " + jobExecution.getStatus());
			LOGGER.info("Job Ending time: " + jobExecution.getEndTime());
			LOGGER.info("Current Execution Status Of Job: " + jobExecution.getStatus());
			/*
			 * else
			 * 
			 * { throw new JobStartException("the Job with the name: " + eachJob +
			 * "is not present in the context"); }}
			 */

		} catch (Exception e) {
			// TODO: handle exception

			System.err.println(e);
			System.exit(-1);
		} /*
			 * finally { ((AbstractApplicationContext) context).close(); }
			 */

	}

}
