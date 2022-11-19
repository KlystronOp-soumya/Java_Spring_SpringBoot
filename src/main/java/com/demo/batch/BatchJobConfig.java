package com.demo.batch;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.flow.JobExecutionDecider;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.demo.batch.config.CustomBatchBasicConfigurer;
import com.demo.batch.config.DeliveryDecider;
import com.demo.batch.config.PackageAcceptanceDecider;
import com.demo.batch.listeners.CustomeStepListener;

@Configuration
@EnableBatchProcessing
public class BatchJobConfig {

	/* The Logger */
	private static final Logger LOGGER = LogManager.getLogger(BatchJobConfig.class);
	// The job decider
	/*
	 * @Autowired
	 * 
	 * @Qualifier("deliveryTimeDecider") private JobExecutionDecider decider;
	 * 
	 * @Autowired
	 * 
	 * @Qualifier("itemAcceptDecider") private JobExecutionDecider
	 * acceptanceDecider;
	 * 
	 */
	@Autowired
	private JobBuilderFactory jobBuilderFactory; // interface

	@Autowired
	private CustomBatchBasicConfigurer basicBatchConfig;

	@Autowired
	private StepBuilderFactory stepBuilderFactory; // interface

	@Bean
	public JobExecutionDecider decider() {
		return new DeliveryDecider();
	}

	@Bean
	public JobExecutionDecider acceptanceDecider() {
		return new PackageAcceptanceDecider();
	}

	// Listener
	@Bean
	public StepExecutionListener stepListener() {
		return new CustomeStepListener();
	}

	@Bean
	public Step refundCustomerStep() {
		return this.stepBuilderFactory.get("refundCustomerStep").tasklet(new Tasklet() {

			@Override
			public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
				System.out.println("Wrong item! Refunding the money to the customer");
				return RepeatStatus.FINISHED;
			}
		}).build();
	}

	@Bean
	public Step thankingCustomerStep() {
		return this.stepBuilderFactory.get("thankingCustomerStep").tasklet(new Tasklet() {

			@Override
			public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
				System.out.println("Customer took the package! Thanks!!");
				return RepeatStatus.FINISHED;
			}
		}).build();
	}

	@Bean
	public Step leavePackageAtDoorStep() {
		return this.stepBuilderFactory.get("leavePackageAtDoorStep").tasklet(new Tasklet() {

			@Override
			public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
				System.out.println("Leaving the package at Door for Customer");
				return RepeatStatus.FINISHED;
			}
		}).build();
	}

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
		}).listener(stepListener()).build();
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

	@Bean(name = "deliverPackageJob")
	public Job deliverPackageJob() {
		// applying the conditional flow
		LOGGER.info("Executing Delivery Package Job");
		Job job = null;
		try {

			// this.jobBuilderFactory = new
			// JobBuilderFactory(this.basicBatchConfig.batchConfigurer().getJobRepository());
			// adding the fail to stop the job
			/*
			 * job = this.jobBuilderFactory.get("deliverPackageJob").repository(this.
			 * basicBatchConfig.getJobRepository())
			 * .preventRestart().start(packageItemStep()).next(driveToAddressStep()).on(
			 * "FAILED")
			 * .to(storePackageStep()).from(driveToAddressStep()).on("*").to(decider()).on(
			 * "PRESENT")
			 * .to(givePackageToCustomerStep()).from(decider()).on("NOT PRESENT").to(
			 * leavePackageAtDoorStep())
			 * .next(acceptanceDecider()).on("ACCEPTED").to(thankingCustomerStep()).from(
			 * acceptanceDecider()) .on("REJECTED").to(refundCustomerStep()).end().build();
			 */

			// removing the storePackageStep
			job = this.jobBuilderFactory.get("deliverPackageJob").repository(this.basicBatchConfig.getJobRepository())
					.preventRestart().start(packageItemStep()).next(driveToAddressStep()).on("FAILED").fail()
					.from(driveToAddressStep()).on("*").to(decider()).on("PRESENT").to(givePackageToCustomerStep())
					.from(decider()).on("NOT PRESENT").to(leavePackageAtDoorStep()).next(acceptanceDecider())
					.on("VALID").to(thankingCustomerStep()).from(acceptanceDecider()).on("REJECTED")
					.to(refundCustomerStep()).end().build();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return job;
	}

	@Bean
	public Step getTheChasis() {
		return this.stepBuilderFactory.get("getCarChasis").tasklet(new Tasklet() {

			@Override
			public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception { // TODO
																														// Auto-generated
																														// method
																														// stub
				System.out.println("Chasis Put On The  Assembly Line");
				return RepeatStatus.FINISHED;
			}
		}).build();
	}

	@Bean
	@Qualifier("carManufacturingJob")
	public Job carManufactureJob() {
		return this.jobBuilderFactory.get("Car Manufacturing Job").flow(getTheChasis()).end().build();
	}

}
