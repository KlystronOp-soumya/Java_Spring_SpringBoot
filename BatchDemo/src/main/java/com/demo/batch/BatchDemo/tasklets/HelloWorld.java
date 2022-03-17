package com.demo.batch.BatchDemo.tasklets;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;

import com.demo.batch.Util.LoggerUtil;

public class HelloWorld implements Tasklet {

	@Autowired
	private transient DataSource dataSource ;
	private static final Logger LOGGER =  Logger.getLogger(HelloWorld.class) ;
	@Override
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
		// TODO Auto-generated method stub
		long timeStamp = (long) chunkContext.getStepContext().getJobParameters().get("timestamp") ;
		String runDateString = chunkContext.getStepContext().getJobParameters().get("run.date").toString() ;
		
		LoggerUtil.info(LOGGER, "Tasklet executed");	
		LoggerUtil.info(LOGGER, String.valueOf(timeStamp));
		System.out.println("Tasklet Execution");
		System.out.println("Showing the Job parameters:");
		
		System.out.println("Batch run date is : "+runDateString);
		
		return RepeatStatus.FINISHED;
	}

}
