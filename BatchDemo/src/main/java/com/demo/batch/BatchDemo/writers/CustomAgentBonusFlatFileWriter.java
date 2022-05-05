package com.demo.batch.BatchDemo.writers;

import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.transform.LineAggregator;
import org.springframework.core.io.Resource;

import com.demo.batch.BatchDemo.entity.AgentBnsQualifEntity;

public class CustomAgentBonusFlatFileWriter extends FlatFileItemWriter<AgentBnsQualifEntity> {

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
		this.resource = resource;
	}

	@Override
	public void setLineAggregator(LineAggregator<AgentBnsQualifEntity> lineAggregator) {// UtilityClass Obj
		this.lineAggregator = lineAggregator;
	}

	@Override
	public void setShouldDeleteIfExists(boolean shouldDeleteIfExists) {
		this.shouldDeleteIfExists = shouldDeleteIfExists;
	}

}
