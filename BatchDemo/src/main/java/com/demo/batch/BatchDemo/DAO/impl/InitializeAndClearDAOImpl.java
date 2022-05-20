package com.demo.batch.BatchDemo.DAO.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.sql.DataSource;

import org.apache.log4j.Logger;

import com.demo.batch.BatchDemo.DAO.intf.InitializeAndClearDAOIntf;

public class InitializeAndClearDAOImpl implements InitializeAndClearDAOIntf {

	/* The Logger */
	private static final Logger LOGGER = Logger.getLogger(InitializeAndClearDAOImpl.class);
	private transient DataSource dataSource;

	private Connection connection;

	private ResultSet rs;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public void insertDataIntoTable() {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteTable() {
		// TODO Auto-generated method stub
		LOGGER.info("Inside DAO :: deleteTable");
		final String query = "DELETE FROM AGENT_BONUS_QUALIF";
		try {
			this.connection = this.dataSource.getConnection();

			PreparedStatement pStatement = this.connection.prepareStatement(query);
			int deletedRows = pStatement.executeUpdate();

			if (deletedRows == 0)
				throw new NullPointerException("Table is empty");

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

}
