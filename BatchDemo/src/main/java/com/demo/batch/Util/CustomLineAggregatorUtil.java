package com.demo.batch.Util;

import org.springframework.batch.item.file.transform.FieldExtractor;
import org.springframework.batch.item.file.transform.FormatterLineAggregator;

import com.demo.batch.BatchDemo.entity.AgentBnsQualifEntity;

public class CustomLineAggregatorUtil extends FormatterLineAggregator<AgentBnsQualifEntity> {

	private FieldExtractor<AgentBnsQualifEntity> fieldExtractor; // the BeanWrapper

	public CustomLineAggregatorUtil() {
		// TODO Auto-generated constructor stub
	}
}
