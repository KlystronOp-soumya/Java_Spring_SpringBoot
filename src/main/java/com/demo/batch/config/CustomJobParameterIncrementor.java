package com.demo.batch.config;

import java.util.Map;

import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersIncrementer;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomJobParameterIncrementor implements JobParametersIncrementer {
	@Override
	public JobParameters getNext(JobParameters parameters) {
		// TODO Auto-generated method stub
		// get the Map
		Map<String, JobParameter> jobParamMap = parameters.getParameters();// taking the reference
		long orderNum = Long.valueOf(jobParamMap.get("orderNum").toString());
		orderNum++;
		jobParamMap.put("orderNum", new JobParameter(String.valueOf(orderNum)));
		return parameters;
	}

}
