package com.demo.batch.config;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;

@Configuration
public class CustomJobParameterConfig {

	private JobParameters jobParameters;

	@NonNull
	private long orderNum = 0;
	@NonNull
	private String orderDate;
	@NonNull
	private String orderTime;

	public JobParameters getCustomJobParameters() {

		Calendar calendar = Calendar.getInstance();
		Date date = new Date();
		SimpleDateFormat timeFormatter = new SimpleDateFormat("HH:MM");
		SimpleDateFormat dateFormatter = new SimpleDateFormat("YYYY-MM-dd");
		orderDate = dateFormatter.format(date);
		orderTime = timeFormatter.format(calendar.getTime());

		this.jobParameters = new JobParametersBuilder().addString("orderNum", String.valueOf(orderNum))
				.addString("timeOfOrder", orderTime).addString("dateOfOrder", orderDate).toJobParameters();
		return this.jobParameters;
	}
}
