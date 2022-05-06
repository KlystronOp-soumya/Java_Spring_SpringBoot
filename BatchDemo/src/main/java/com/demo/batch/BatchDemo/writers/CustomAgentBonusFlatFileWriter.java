package com.demo.batch.BatchDemo.writers;

import org.apache.log4j.Logger;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.transform.LineAggregator;
import org.springframework.core.io.Resource;

import com.demo.batch.BatchDemo.entity.AgentBnsQualifEntity;

public class CustomAgentBonusFlatFileWriter extends FlatFileItemWriter<AgentBnsQualifEntity> {

	/* logger */
	private static final Logger LOGGER = Logger.getLogger(CustomAgentBonusFlatFileWriter.class);
	/*
	 * 
	 * Add this as the tasklet writer into XML
	 * 
	 * Create a seperate reader for this copy job; add that as well into XML
	 * 
	 */
	private transient Resource resource;
	private transient LineAggregator<AgentBnsQualifEntity> lineAggregator;
	private transient boolean shouldDeleteIfExists;

	@Override
	public void setResource(Resource resource) {
		LOGGER.info("Set resource for Flat File Writing");
		this.resource = resource;
	}

	@Override
	public void setLineAggregator(LineAggregator<AgentBnsQualifEntity> lineAggregator) {// UtilityClass Obj
		LOGGER.info("Set LineAggregator for Flat File Writing");
		this.lineAggregator = lineAggregator;
	}

	@Override
	public void setShouldDeleteIfExists(boolean shouldDeleteIfExists) {
		this.shouldDeleteIfExists = shouldDeleteIfExists;
	}

}
