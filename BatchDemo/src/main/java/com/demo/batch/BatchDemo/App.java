package com.demo.batch.BatchDemo;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.TimeZone;

import org.apache.log4j.Logger;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.demo.batch.Util.CheckDBConn;
import com.demo.batch.Util.DateTimeUtil;
import com.demo.batch.Util.LoggerUtil;

/**
 * Hello world!
 *
 */
@SuppressWarnings("unused")
public class App 
{
	private static final Logger LOGGER = Logger.getLogger(App.class) ;
	
	private static String getBatchRunDate()
	{
		Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("Asia/Kolkata"));
		String formatString = "%s-%s-%s" ;
		int d = calendar.get( Calendar.DAY_OF_MONTH) ;
		int m = calendar.get( Calendar.MONTH) ;
		int y = calendar.get( Calendar.YEAR);		
		  final String formattedDate = String.format(formatString,
		  Integer.toString(y),Integer.toString(m),Integer.toString(d));
		  
		  return formattedDate ;
	}
	
    public static void main( String[] args )
    {   	
    	LoggerUtil.info(LOGGER, "Inside main class");
    	String[] springConfig = { "spring/batch/jobs/batch-job-config.xml" };

		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(springConfig);
		
		//CheckDBConn objCheckDBConn = (CheckDBConn) context.getBean("checkDatabaseConn",CheckDBConn.class) ;
		//objCheckDBConn.isConnected();
		JobLauncher jobLauncher = (JobLauncher) context.getBean("jobLauncher");
		
		Job job = (Job) context.getBean("agentBonusCalculation");
		JobExecution execution = null ;
		/*
		 * JobParameters jobParameters = new JobParametersBuilder().addLong("timestamp",
		 * System.currentTimeMillis()) .toJobParameters();
		 */
		int batchDay = DateTimeUtil.getCalDay();
		int batchMonth = DateTimeUtil.getCalMonth();
		int batchYear = DateTimeUtil.getCalYear() ;
		
		JobParameters jobParameters = new JobParametersBuilder().addLong("timestamp", System.currentTimeMillis())
										.addString("run.date", getBatchRunDate())
										.addString("calDay", Integer.toString(batchYear))
										.addString("calMonth", Integer.toString(batchMonth))
										.addString("calMonth", Integer.toString(batchYear)).toJobParameters() ;
		
		
		
		try {
			/*
			 * LoggerUtil.info(LOGGER, "Checking database connection");
			 * CheckDBConn.isConnected() ;
			 */
			LoggerUtil.info(LOGGER, "Job started");
			 execution = jobLauncher.run(job, jobParameters);
			System.out.println("Exit Status : " + execution.getStatus());
			System.out.println("Done");
			System.out.println("The current job is : "+execution.getJobId());
			System.out.println("The current step execution is : "+execution.getStepExecutions());
			System.out.println("Exit Status: "+execution.getExitStatus());
			context.close();
		}
		catch (Exception e)
		{
			// TODO: handle exception
			e.printStackTrace();
		}
		
	
	}
    	
       
}
