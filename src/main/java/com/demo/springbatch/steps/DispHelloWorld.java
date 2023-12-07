package com.demo.springbatch.steps;

import java.sql.SQLException;
import java.util.Calendar;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;

public class DispHelloWorld implements Tasklet {

	@Autowired
	@Qualifier("jdbcTemplate")
	private JdbcTemplate jdbcTemplate ;
	
	@SuppressWarnings("deprecation")
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
		Calendar calendar = Calendar.getInstance() ;
		System.out.println("Executing the Job");
		System.out.println("Execution Time: Seconds: " + calendar.getTime().getSeconds());
		try {
			if( null!= jdbcTemplate.getDataSource().getConnection())
				System.out.println("Ok");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return RepeatStatus.FINISHED;
	}

}
