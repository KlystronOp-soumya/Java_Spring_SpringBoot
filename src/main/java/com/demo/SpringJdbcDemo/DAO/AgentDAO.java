package com.demo.SpringJdbcDemo.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.jdbc.datasource.init.ScriptException;
import org.springframework.stereotype.Repository;

import com.demo.SpringJdbcDemo.entity.AgentEntity;
import com.demo.SpringJdbcDemo.enums.ConstEnums;
import com.demo.SpringJdbcDemo.intf.AgentRepositoryIntf;
import com.demo.SpringJdbcDemo.service.AgentRowMapperService;
import com.zaxxer.hikari.HikariDataSource;

@Repository
@SuppressWarnings("unused")
public class AgentDAO implements AgentRepositoryIntf {

	/* The Logger */
	private static final Logger LOGGER = org.apache.logging.log4j.LogManager.getLogger(AgentDAO.class);
	private Connection connection;
	private ResultSet rs;
	// private transient DataSource dataSource;

	private transient ResourceDatabasePopulator resourceDatabasePopulator;
	private transient HikariDataSource hikariDataSource;

	private JdbcTemplate jdbctemplate;

	/*
	 * @Override public void setDataSource(DataSource dataSource) { // TODO
	 * Auto-generated method stub this.dataSource = dataSource;
	 * 
	 * }
	 */

	@Override // setter injection
	public void setHikariDataSource(HikariDataSource hikariDataSource) {
		// TODO Auto-generated method stub
		LOGGER.info("Set HikariDataSource");
		this.hikariDataSource = hikariDataSource;
	}

	@Autowired
	@Override
	public void setResourcePopulator(ResourceDatabasePopulator resourceDatabasePopulator) {
		// TODO Auto-generated method stub
		LOGGER.info("Set Resource Populator");
		this.resourceDatabasePopulator = resourceDatabasePopulator;
	}

	@Override // setter injection
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		// TODO Auto-generated method stub
		LOGGER.info("Set JdbcTemplate");
		this.jdbctemplate = jdbcTemplate;
	}

	// The method was commented.
	/*
	 * public void checkConnection() throws SQLException { // init connection =
	 * null; try { populateReosurces(); connection =
	 * this.hikariDataSource.getConnection(); // PreparedStatement pstmt = //
	 * connection.prepareStatement(this.hikariDataSource.getConnectionTestQuery());
	 * 
	 * PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM AGENT");
	 * rs = pstmt.executeQuery(); if (rs.next()) {
	 * LOGGER.info("Connection Tested:: OK");
	 * 
	 * }
	 * 
	 * } catch (Exception e) { // TODO: handle exception } finally {
	 * connection.close(); this.hikariDataSource.close(); //
	 * this.dataSource.getConnection().close(); } }
	 */

	@Override
	public void populateReosurces() {
		// TODO Auto-generated method stub
		LOGGER.info("Populating Reosurce");
		try {
			this.resourceDatabasePopulator.addScripts(new ClassPathResource(ConstEnums.CREATE_SCRIPT_CLASSPATH.value),
					new ClassPathResource(ConstEnums.INSERT_DATA_CLASSPATH.value));
			this.resourceDatabasePopulator.populate(this.hikariDataSource.getConnection());
		} catch (ScriptException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		LOGGER.info("Resource Population Done");
	}

	/*
	 * Method to fetch all the agents from Database
	 * 
	 * @param
	 * 
	 * @return List<AgentEntity>
	 * 
	 */
	@Override
	public List<AgentEntity> getAllAgentsList() {
		// TODO Auto-generated method stub
		LOGGER.info("Inside DAO::getAllAgentsList method");
		List<AgentEntity> agentList = null;
		final String queryString = "SELECT COLL_OFF,AGENT_ID,AGENT_NO,POL_NO,LOB,COMMISSION FROM AGENT_TEST";
		try {
			agentList = new ArrayList<>();
			agentList = this.jdbctemplate.query(queryString, new AgentRowMapperService());

			if (agentList == null || agentList.isEmpty())
				throw new NullPointerException("Agent List can not be empty");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {

			try {
				this.jdbctemplate.getDataSource().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return agentList;
	}

	@Override
	public void addAgent() {
		// TODO Auto-generated method stub

	}

	@Override
	public AgentEntity searchAgentById(String collOffice, String agentId) {
		// TODO Auto-generated method stub
		return null;
	}

}
