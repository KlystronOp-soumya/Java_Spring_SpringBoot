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
import org.springframework.batch.core.job.builder.FlowBuilder;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.batch.core.job.flow.JobExecutionDecider;
import org.springframework.batch.core.job.flow.support.SimpleFlow;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;

import com.demo.batch.config.CustomBatchBasicConfigurer;
import com.demo.batch.config.DeliveryDecider;
import com.demo.batch.config.PackageAcceptanceDecider;
import com.demo.batch.config.ReceiptDecider;
import com.demo.batch.listeners.CustomeStepListener;
import com.demo.batch.listeners.FlowersSelectionStepExecutionListener;

/*
 * Nests Billing job as a nested job with Package Item and Delivery flow
 * 
 * */
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

	@Bean(name = { "deliveryDecider" })
	public JobExecutionDecider decider() {
		return new DeliveryDecider();
	}

	@Bean(name = "packageAcceptanceDecider")
	public JobExecutionDecider acceptanceDecider() {
		return new PackageAcceptanceDecider();
	}

	@Bean(name = "receiptDecider")
	public JobExecutionDecider receiptDecider() {
		return new ReceiptDecider();
	}

	// Listener
	@Bean
	public StepExecutionListener stepListener() {
		return new CustomeStepListener();
	}

	@Bean
	public StepExecutionListener selectFlowerListener() {
		return new FlowersSelectionStepExecutionListener();
	}

	@Bean
	public Step selectFlowersStep() {
		return this.stepBuilderFactory.get("selectFlowersStep").tasklet(new Tasklet() {

			@Override
			public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
				System.out.println("Gathering flowers for order.");
				return RepeatStatus.FINISHED;
			}

		}).listener(selectFlowerListener()).build();
	}

	@Bean
	public Step removeThornsStep() {
		return this.stepBuilderFactory.get("removeThornsStep").tasklet(new Tasklet() {

			@Override
			public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
				System.out.println("Remove thorns from roses.");
				return RepeatStatus.FINISHED;
			}

		}).build();
	}

	@Bean
	public Step arrangeFlowersStep() {
		return this.stepBuilderFactory.get("arrangeFlowersStep").tasklet(new Tasklet() {

			@Override
			public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
				System.out.println("Arranging flowers for order.");
				return RepeatStatus.FINISHED;
			}

		}).build();
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
	public Step thankCustomerStep() {
		return this.stepBuilderFactory.get("thankCustomerStep").tasklet(new Tasklet() {

			@Override
			public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
				System.out.println("Thanking the customer.");
				return RepeatStatus.FINISHED;
			}
		}).build();
	}

	@Bean
	public Step refundStep() {
		return this.stepBuilderFactory.get("refundStep").tasklet(new Tasklet() {

			@Override
			public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
				System.out.println("Refunding customer money.");
				return RepeatStatus.FINISHED;
			}
		}).build();
	}

	@Bean
	public Step leaveAtDoorStep() {
		return this.stepBuilderFactory.get("leaveAtDoorStep").tasklet(new Tasklet() {

			@Override
			public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
				System.out.println("Leaving the package at the door.");
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
			/*
			 * job = this.jobBuilderFactory.get("deliverPackageJob").repository(this.
			 * basicBatchConfig.getJobRepository())
			 * .preventRestart().start(packageItemStep()).next(driveToAddressStep()).on(
			 * "FAILED").fail()
			 * .from(driveToAddressStep()).on("*").to(decider()).on("PRESENT").to(
			 * givePackageToCustomerStep())
			 * .from(decider()).on("NOT PRESENT").to(leavePackageAtDoorStep()).next(
			 * acceptanceDecider())
			 * .on("VALID").to(thankingCustomerStep()).from(acceptanceDecider()).on(
			 * "REJECTED") .to(refundCustomerStep()).end().build();
			 */

			// adding the nested job step
			/*
			 * job =
			 * this.jobBuilderFactory.get("deliverPackageJob").start(packageItemStep()).on(
			 * "*").to(deliveryFlow()) .next(nestedBillingJobStep()).end().build();
			 */

			job = this.jobBuilderFactory.get("deliverPackageJob").start(packageItemStep())
					.split(new SimpleAsyncTaskExecutor()).add(deliveryFlow()).end().build();
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

	// Step included to show the SendInvoice nested job
	@Bean(name = "sendInvoice")
	public Step sendInvoiceStep() {
		return this.stepBuilderFactory.get("invoiceStep").tasklet(new Tasklet() {

			@Override
			public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
				System.out.println("Invoice is sent to the customer");
				return RepeatStatus.FINISHED;
			}
		}).build();
	}

	// step to build the nested job
	@Bean
	public Step nestedBillingJobStep() {
		return this.stepBuilderFactory.get("nestedBillingJob").job(billingJob()).build();
	}

	/*
	 * Type flow bean
	 * 
	 * This externalize certain steps which are common in different jobs
	 * 
	 * The delivery process/steps are commons for both the jobs
	 * 
	 */
	public Flow deliveryFlow() {
		return new FlowBuilder<SimpleFlow>("deliveryFlow").start(driveToAddressStep()).on("FAILED").fail()
				.from(driveToAddressStep()).on("*").to(decider()).on("PRESENT").to(givePackageToCustomerStep())
				.next(receiptDecider()).on("CORRECT").to(thankCustomerStep()).from(receiptDecider()).on("INCORRECT")
				.to(refundStep()).from(decider()).on("NOT_PRESENT").to(leaveAtDoorStep()).build();
	}

	@Bean(name = "billingFlow")
	public Flow billingFlow() {
		return new FlowBuilder<SimpleFlow>("billingFlow").start(sendInvoiceStep()).build();
	}

	@Bean(name = { "prepareFlowers" })
	public Job prepareFlowers() {
		return this.jobBuilderFactory.get("prepareFlowersJob").start(selectFlowersStep()).on("TRIM_REQUIRED")
				.to(removeThornsStep()).next(arrangeFlowersStep()).from(selectFlowersStep()).on("NO_TRIM_REQUIRED")
				.to(arrangeFlowersStep()).from(arrangeFlowersStep()).on("*").to(deliveryFlow()).end().build();
	}

	@Bean(name = "billing")
	public Job billingJob() {
		return this.jobBuilderFactory.get("billingJob").start(sendInvoiceStep()).build();
	}

	@Bean(value = "carManufacturingJob")
	public Job carManufactureJob() {
		return this.jobBuilderFactory.get("Car Manufacturing Job").flow(getTheChasis()).end().build();
	}

}
