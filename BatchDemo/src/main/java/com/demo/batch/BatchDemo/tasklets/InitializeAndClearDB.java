package com.demo.batch.BatchDemo.tasklets;

import org.apache.log4j.Logger;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

import com.demo.batch.BatchDemo.services.intf.InitAndClearIntf;

public class InitializeAndClearDB implements Tasklet {

	/* The logger */
	private static final Logger LOGGER = Logger.getLogger(InitializeAndClearDB.class);

	private transient InitAndClearIntf initAndClearSrvce;

	public InitializeAndClearDB(InitAndClearIntf initAndClearSrvce) {
		// TODO Auto-generated constructor stub
		this.initAndClearSrvce = initAndClearSrvce;
	}

	@Override
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
		// TODO Auto-generated method stub
		LOGGER.info("Executing Initialize And Clear Tasklet");
		initAndClearSrvce.clearAgentBnsQualif();

		LOGGER.info("Executed Initialize And Clear Tasklet");
		return RepeatStatus.FINISHED;
	}

}
