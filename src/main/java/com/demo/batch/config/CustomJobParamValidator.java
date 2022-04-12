package com.demo.batch.config;

import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.JobParametersValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CustomJobParamValidator implements JobParametersValidator {

	@Autowired

	@Override
	public void validate(JobParameters parameters) throws JobParametersInvalidException {
		// TODO Auto-generated method stub

	}

}
