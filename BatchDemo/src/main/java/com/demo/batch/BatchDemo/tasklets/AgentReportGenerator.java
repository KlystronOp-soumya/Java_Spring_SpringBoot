package com.demo.batch.BatchDemo.tasklets;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

import com.demo.batch.BatchDemo.services.intf.ReportGenerator;

public class AgentReportGenerator implements Tasklet, ReportGenerator {

	// Get data from DAO class

	@Override
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void generateExcelReport() {
		// TODO Auto-generated method stub

	}

	@Override
	public void generateJasperReport() {
		// TODO Auto-generated method stub

	}

	@Override
	public void generateCsvReport() {
		// TODO Auto-generated method stub

	}

}
