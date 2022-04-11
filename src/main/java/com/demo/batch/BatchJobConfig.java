package com.demo.batch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.demo.batch.config.CustomBatchBasicConfigurer;

@Configuration
public class BatchJobConfig {

	@Autowired
	private JobBuilderFactory jobBuilderFactory; // interface

	@Autowired
	private CustomBatchBasicConfigurer basicBatchConfig;

	@Autowired
	private StepBuilderFactory stepBuilderFactory; // interface

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
		boolean GOT_LOST = false;
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
				/*
				 * String item =
				 * chunkContext.getStepContext().getJobParameters().get("item").toString();
				 * String date =
				 * chunkContext.getStepContext().getJobParameters().get("run.date").toString();
				 * System.out.println("Item was packaged...");
				 * System.out.println(String.format("The %s has been packaged on %s.", item,
				 * date));
				 */
				return RepeatStatus.FINISHED;
			}
		}).build();
	}

	@Bean
	public Job deliverPackageJob() {
		// applying the conditional flow
		Job job = null;
		try {

			// this.jobBuilderFactory = new
			// JobBuilderFactory(this.basicBatchConfig.batchConfigurer().getJobRepository());
			job = this.jobBuilderFactory.get("deliverPackageJob").repository(this.basicBatchConfig.getJobRepository())
					.preventRestart().start(packageItemStep()).next(driveToAddressStep()).on("FAILED")
					.to(storePackageStep()).from(driveToAddressStep()).on("*").to(givePackageToCustomerStep()).end()
					.build();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return job;
	}
}
