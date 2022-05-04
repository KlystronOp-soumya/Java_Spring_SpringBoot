package com.demo.batch.Util;

import org.springframework.batch.item.file.transform.FormatterLineAggregator;

import com.demo.batch.BatchDemo.entity.AgentBnsQualifEntity;

public class CustomLineAggregatorUtil extends FormatterLineAggregator<AgentBnsQualifEntity> {
	/*
	 * 
	 * override setFormat override override setFieldExtractor
	 * 
	 * Add this as well in the XML as bean and inject into the FlatFileItemReader
	 * 
	 */
}
