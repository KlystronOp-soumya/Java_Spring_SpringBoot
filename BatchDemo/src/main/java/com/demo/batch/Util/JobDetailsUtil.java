package com.demo.batch.Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;

public class JobDetailsUtil {
	@Autowired
	private static transient DataSource dataSource;
	
	private static List<String> jobNamesList;
	
	public JobDetailsUtil() {
		// TODO Auto-generated constructor stub
	}
	
	public  JobDetailsUtil(DataSource dataSource) {
		JobDetailsUtil.dataSource = dataSource ;
	}
	
	public static List<String> getJobNames()
	{
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rSet= null;
		
		try {
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return jobNamesList ;
	}
	

}
