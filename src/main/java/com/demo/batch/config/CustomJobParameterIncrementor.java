package com.demo.batch.config;

import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersIncrementer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomJobParameterIncrementor implements JobParametersIncrementer {

	@Bean
	@Override
	public JobParameters getNext(JobParameters parameters) {
		// TODO Auto-generated method stub
		// get the Map
		// Map<String, JobParameter> jobParamMap = parameters.getParameters();// taking
		// the reference
		// long orderNum = Long.valueOf(jobParamMap.get("orderNum").toString());
		// orderNum++;
		// jobParamMap.put("orderNum", new JobParameter(String.valueOf(orderNum)));
		if (parameters == null || parameters.isEmpty()) {
			return new JobParametersBuilder().addLong("orderNum", -1L).toJobParameters();
		}
		long orderNum = Long.valueOf(parameters.getString("orderNum"));
		orderNum++;

		return new JobParametersBuilder().addString("orderNum", String.valueOf(orderNum)).toJobParameters();
	}

}
