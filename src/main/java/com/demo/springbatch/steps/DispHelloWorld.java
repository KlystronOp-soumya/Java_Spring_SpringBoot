package com.demo.springbatch.steps;

import java.util.Calendar;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

public class DispHelloWorld implements Tasklet {

	@SuppressWarnings("deprecation")
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
		Calendar calendar = Calendar.getInstance() ;
		System.out.println("Executing the Job");
		System.out.println("Execution Time: Seconds: " + calendar.getTime().getSeconds());
		
		
		return RepeatStatus.FINISHED;
	}

}
