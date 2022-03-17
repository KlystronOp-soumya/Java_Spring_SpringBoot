package com.demo.batch.Util;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

public class CheckDBConn {

	private static final Logger LOGGER = Logger.getLogger(CheckDBConn.class) ;
	
	@Autowired
	private static transient DataSource dataSource ;
	
	/*
	 * public CheckDBConn(DataSource dataSource) { // TODO Auto-generated
	 * constructor stub this.dataSource = dataSource ; }
	 */
	public static  void isConnected()
	{
		
		try {
			Connection connection = dataSource.getConnection() ;
			
			if(null != connection)
			{
				LoggerUtil.info(LOGGER, "Connection was successfull");
				String queryString = "select * from ";
				connection.close();
				LoggerUtil.info(LOGGER, "Connection was closed");
			}
			else {
				LOGGER.error("Connection was not established");
			}
				
			
		} catch (Exception e) {
			// TODO: handle exception
			LOGGER.error("Database connection error", e);
			e.printStackTrace();
		}
		
		
	
	}
}
