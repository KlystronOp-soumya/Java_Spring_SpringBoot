package com.demo.jdbcDemo.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.sql.DataSource;
import javax.sql.RowSet;
import javax.sql.rowset.JdbcRowSet;
import javax.sql.rowset.RowSetProvider;

import org.apache.log4j.Logger;

import com.mysql.cj.jdbc.MysqlDataSource;

public class DBConnectionUtil {

	private static final Logger LOGGER = Logger.getLogger(DBConnectionUtil.class);
	
	private static Connection establishConnection()
	{
		LOGGER.info("Connection using JDBC");
		Connection conn = null;
		try {
			Class.forName(System.getProperty("msql.driver")) ;			
			conn = DriverManager.getConnection(System.getProperty("msql.url"),System.getProperty("msql.userid"),System.getProperty("msql.password"));			
		}
		catch(Exception e )
		{
			e.printStackTrace();
		}
		return conn ;		
	}
	
	private static DataSource getMySqlDataSource()
	{
		LOGGER.info("Connection using the datasource");
		MysqlDataSource dataSource = null ;
		try {
			dataSource = new MysqlDataSource() ; //no requirement of registering the class
			dataSource.setURL(System.getProperty("msql.url"));
			dataSource.setUser(System.getProperty("msql.userid"));
			dataSource.setPassword(System.getProperty("msql.password"));
		} catch (Exception e) {
			// TODO: handle exception
		}
		return dataSource;
	}
	
	private static RowSet rowSetFactory()
	{
		LOGGER.info("Connection using Rowset");
		JdbcRowSet rowSet = null;
		try {
			Class.forName(System.getProperty("msql.driver")) ;	
			rowSet = RowSetProvider.newFactory().createJdbcRowSet();
			rowSet.setUrl(System.getProperty("msql.url"));
			rowSet.setUsername(System.getProperty("msql.userid"));
			rowSet.setPassword(System.getProperty("msql.password"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch(ClassNotFoundException cnf)
		{
			cnf.printStackTrace();
		}
		return rowSet ;
	}
	
	public static Connection getConnection()
	{
		return establishConnection();
	}
	public static DataSource getDataSource()
	{
		return getMySqlDataSource();
	}
	
	public static RowSet getRowSet()
	{
		return rowSetFactory();
	}
}
