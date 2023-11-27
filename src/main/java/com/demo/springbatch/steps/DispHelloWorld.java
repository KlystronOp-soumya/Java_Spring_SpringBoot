package com.demo.springbatch.steps;

import java.util.Calendar;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

public class DispHelloWorld implements Tasklet {

	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
		
		System.out.println("Hello World" + Calendar.getInstance());
		
		return RepeatStatus.FINISHED;
	}

}
