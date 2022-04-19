package com.demo.batch.BatchDemo.writers;

import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.transform.LineAggregator;
import org.springframework.core.io.Resource;

import com.demo.batch.BatchDemo.entity.AgentBnsQualifEntity;

public class CustomAgentBonusFileWriter extends FlatFileItemWriter<AgentBnsQualifEntity> {

	private String lineSeparator;
	private LineAggregator<AgentBnsQualifEntity> lineAggregator; // the custom class
	private Resource resource; // from bean set in the properties file
	private boolean shouldDeleteIfExists;

	public void setLineSeparator(String lineSeparator) {
		this.lineSeparator = lineSeparator;
	}

	public void setLineAggregator(LineAggregator<AgentBnsQualifEntity> lineAggregator) {
		this.lineAggregator = lineAggregator;
	}

	@Override
	public void setResource(Resource resource) {
		this.resource = resource;
	}

	public void setShouldDeleteIfExists(boolean shouldDeleteIfExists) {
		this.shouldDeleteIfExists = shouldDeleteIfExists;
	}
}
