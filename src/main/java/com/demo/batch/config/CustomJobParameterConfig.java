package com.demo.batch.config;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;

@Configuration
public class CustomJobParameterConfig {

	private JobParameters jobParameters;

	private long orderNum = 1;
	@NonNull
	private String orderDate;
	@NonNull
	private String orderTime;

	@Bean
	public JobParameters getCustomJobParameters() {
		// Map<String, JobParameter> jobParamMap = new HashMap<>();
		Calendar calendar = Calendar.getInstance();
		Date date = new Date();
		SimpleDateFormat timeFormatter = new SimpleDateFormat("hh:mm");
		SimpleDateFormat dateFormatter = new SimpleDateFormat("YYYY-MM-dd");
		orderDate = dateFormatter.format(date);
		orderTime = timeFormatter.format(calendar.getTime());
		// jobParamMap.put("orderNum", new JobParameter(orderNum));
		// jobParamMap.put("dateOfOrder", new JobParameter(orderDate));
		// jobParamMap.put("timeOfOrder", new JobParameter(orderTime));
		// this.jobParameters = new JobParameters(jobParamMap);

		this.jobParameters = new JobParametersBuilder().addString("orderNum", String.valueOf(orderNum))
				.addString("timeOfOrder", orderTime).addString("dateOfOrder", orderDate).toJobParameters();

		return this.jobParameters;
	}
}
