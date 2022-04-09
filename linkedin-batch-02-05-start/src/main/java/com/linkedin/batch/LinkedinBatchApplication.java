package com.linkedin.batch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*
 * To run this particular batch
 * run: mvn clean compile package
 * then:java -jar "jar name" "item=name" "run.date=anydate"
 * Check the param values in the meta data table
 * 
 * */
@SpringBootApplication
@EnableBatchProcessing
@Configuration
public class LinkedinBatchApplication {

	@Autowired
	public JobBuilderFactory jobBuilderFactory;

	@Autowired
	public StepBuilderFactory stepBuilderFactory;

	@Bean
	public Step storePackageStep() {
		return this.stepBuilderFactory.get("storePackageStep").tasklet(new Tasklet() {

			@Override
			public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
				System.out.println("Storing the package while the customer address is located.");
				return RepeatStatus.FINISHED;
			}
		}).build();
	}

	@Bean
	public Step givePackageToCustomerStep() {
		return this.stepBuilderFactory.get("givePackageToCustomer").tasklet(new Tasklet() {

			@Override
			public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
				System.out.println("Given the package to the customer.");
				return RepeatStatus.FINISHED;
			}
		}).build();
	}

	@Bean
	public Step driveToAddressStep() {

		// lost flag
		boolean GOT_LOST = true;
		return this.stepBuilderFactory.get("driveToAddressStep").tasklet(new Tasklet() {

			@Override
			public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
				System.out.println("Successfully arrived at the address.");

				if (GOT_LOST) {
					throw new RuntimeException("Got lost driving to the address");
				}

				return RepeatStatus.FINISHED;
			}
		}).build();
	}

	@Bean
	public Step packageItemStep() {
		return this.stepBuilderFactory.get("packageItemStep").tasklet(new Tasklet() {

			@Override
			public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
				String item = chunkContext.getStepContext().getJobParameters().get("item").toString();
				String date = chunkContext.getStepContext().getJobParameters().get("run.date").toString();
				System.out.println("Item was packaged...");
				System.out.println(String.format("The %s has been packaged on %s.", item, date));
				return RepeatStatus.FINISHED;
			}
		}).build();
	}

	@Bean
	public Job deliverPackageJob() {
		// applying the conditional flow
		return this.jobBuilderFactory.get("deliverPackageJob").start(packageItemStep()).next(driveToAddressStep())
				.on("FAILED").to(storePackageStep()).from(driveToAddressStep()).on("*").to(givePackageToCustomerStep())
				.end().build();
	}

	/*
	 * @Bean public DataSource dataSource() { return new
	 * EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2)
	 * .addScript("/org/springframework/batch/core/schema-drop-h2.sql")
	 * .addScript("/org/springframework/batch/core/schema-h2.sql").build(); }
	 */

	// @Override
	/*
	 * protected JobRepository createJobRepository() throws Exception {
	 * JobRepositoryFactoryBean factory = new JobRepositoryFactoryBean();
	 * factory.setDataSource(dataSource);
	 * factory.setTransactionManager(transactionManager);
	 * factory.setIsolationLevelForCreate("ISOLATION_SERIALIZABLE");
	 * factory.setTablePrefix("BATCH_"); factory.setMaxVarCharLength(1000); return
	 * factory.getObject(); }
	 */

	/*
	 * protected JobLauncher createJobLauncher() throws Exception {
	 * SimpleJobLauncher jobLauncher = new SimpleJobLauncher();
	 * jobLauncher.setJobRepository(jobRepository);
	 * jobLauncher.afterPropertiesSet(); return jobLauncher; }
	 */

	public static void main(String[] args) {
		SpringApplication.run(LinkedinBatchApplication.class, args);
		// Not using the builder method
		/*
		 * Map<String, JobParameter> params = new HashMap<>(); params.put("item", new
		 * JobParameter(args[1])); params.put("run.date", new JobParameter(args[2]));
		 * JobParameters jobParameters = new JobParameters(params);
		 */
	}

}
