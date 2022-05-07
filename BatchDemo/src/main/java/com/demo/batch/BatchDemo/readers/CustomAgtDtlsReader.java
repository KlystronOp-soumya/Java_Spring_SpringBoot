package com.demo.batch.BatchDemo.readers;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.demo.batch.BatchDemo.entity.Agent;
import com.demo.batch.BatchDemo.mappers.AgentDtlsRowMapper;
import com.demo.batch.Util.LoggerUtil;

@Component
public class CustomAgtDtlsReader implements ItemReader<Agent> {

	/* logger */
	private static final Logger LOGGER = Logger.getLogger(CustomAgtDtlsReader.class);

	@Autowired
	private transient DataSource dataSource;

	@Autowired
	private transient AgentDtlsRowMapper agtDtlsRowMapper;

	/*
	 * public void setAgtDtlsRowMapper(AgentDtlsRowMapper agtDtlsRowMapper) {
	 * this.agtDtlsRowMapper = agtDtlsRowMapper; }
	 */

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	public void setRs(ResultSet rs) {
		this.rs = rs;
	}

	public void setStmt(Statement stmt) {
		this.stmt = stmt;
	}

	public void setRowNum(int rowNum) {
		this.rowNum = rowNum;
	}

	private Connection connection;

	private ResultSet rs = null;

	private Statement stmt;

	protected String query;

	private int rowNum = 0;

//	public CustomAgtDtlsReader(DataSource dataSource) {
//		// TODO Auto-generated constructor stub
//		this.dataSource = dataSource ;
//	}

	@Override
	public Agent read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
		// TODO Auto-generated method stub
		Agent agtMappedObj = null;
		try {

			if (null == rs) {
				LoggerUtil.debug(LOGGER, "Result set is null,initializing the reader");
				this.initReader();
			}

			if (rs.next()) {
				LoggerUtil.debug(LOGGER, "rowMapper called");

				rowNum++;
				agtMappedObj = agtDtlsRowMapper.mapRow(rs, rowNum);
				System.out.printf("Current row %d mapped", rowNum);
			}

		} catch (NullPointerException npe) {
			// TODO: handle exception
			npe.printStackTrace();
			this.closeReader();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			this.closeReader();
		}
		return agtMappedObj;
	}

	private void initReader() // Service -> DAO -> DB
	{
		this.connection = null;
		this.rs = null;
		this.stmt = null;
		String queryString = this.getQuery();
		try {
			connection = this.dataSource.getConnection();
			stmt = connection.createStatement();
			rs = stmt.executeQuery(queryString);
			// this.closeReader();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	private String getQuery() {

		query = "SELECT AGT_ID,NAME,DESIGNATION,LOB,BONUS FROM AGENT";
		return query;
	}

	public void closeReader() {
		try {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
			if (connection != null)
				connection.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}
}
