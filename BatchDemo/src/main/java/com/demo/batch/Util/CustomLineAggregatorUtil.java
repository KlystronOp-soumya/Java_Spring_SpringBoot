package com.demo.batch.Util;

import org.springframework.batch.item.file.transform.FieldExtractor;
import org.springframework.batch.item.file.transform.FormatterLineAggregator;

import com.demo.batch.BatchDemo.entity.AgentBnsQualifEntity;

public class CustomLineAggregatorUtil extends FormatterLineAggregator<AgentBnsQualifEntity> {
	private String format = null;
	private transient FieldExtractor<AgentBnsQualifEntity> fieldExtractor;

	public void setFormat(final String format) {
		this.format = format;
	}

	@Override
	public void setFieldExtractor(FieldExtractor<AgentBnsQualifEntity> fieldExtractor) {
		this.fieldExtractor = fieldExtractor;
	}
}
