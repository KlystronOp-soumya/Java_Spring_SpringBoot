package com.demo.batch.BatchDemo.enums;

public enum Constants {

	SPRING_BATCH_CONFIG_PATH("spring/batch/jobs/batch-job-config.xml");

	public String value;

	Constants(String value)

	{
		this.value = value;
	}
}
