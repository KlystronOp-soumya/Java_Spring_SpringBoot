package com.demo.batch.BatchDemo.validator;

import java.util.Calendar;
import java.util.TimeZone;

import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.JobParametersValidator;

/*
 * 
 * jobLauncher.run() calls the job.getJobParametersValidator which returns the object of this class
 * 
 * then that object calls the validate method.So it is internally called
 * 
 * */
public class CustomJobParameterValidator implements JobParametersValidator {

	@Override
	public void validate(JobParameters parameters) {
		// TODO Auto-generated method stub
		try {
			String day = parameters.getString("calDay");
			String month = parameters.getString("calMonth");
			String year = parameters.getString("calYear");

			// validate if the parameters are equal to the current date
			checkBatchParamsValidity(day, month, year);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			// Had to stop the execution forecfully otherwise job was executed successfully.
			// This is the nature of the Spring Batch
			System.exit(1);

		}
	}

	protected void checkBatchParamsValidity(String calDay, String calMonth, String calYear)
			throws JobParametersInvalidException {
		// get the calendar instance
		TimeZone timeZone = TimeZone.getTimeZone("ASIA/KOLKATA");
		Calendar calendar = Calendar.getInstance(timeZone);
		// check the validty
		// the calDay should be greater than or equal to the current date
		if (!(Integer.parseInt(calDay) >= calendar.get(Calendar.DAY_OF_MONTH))) {
			throw new JobParametersInvalidException("calDay can not be an old date");
		} else if (!(Integer.parseInt(calMonth) >= calendar.get(Calendar.MONTH))) {
			throw new JobParametersInvalidException("calMonth can not be an old month");
		} else if (!(Integer.parseInt(calYear) == calendar.get(Calendar.YEAR))) {
			throw new JobParametersInvalidException("calYear can not be an old Year");
		}

	}

}
