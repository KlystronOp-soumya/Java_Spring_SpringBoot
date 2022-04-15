package com.demo.SpringJdbcDemo.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.jdbc.datasource.init.ScriptException;
import org.springframework.stereotype.Repository;

import com.demo.SpringJdbcDemo.intf.AgentRepositoryIntf;
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

	@Override
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

	@Override
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		// TODO Auto-generated method stub
		LOGGER.info("Set JdbcTemplate");
		this.jdbctemplate = jdbcTemplate;
	}

	public void checkConnection() throws SQLException {
		// init
		connection = null;
		try {
			populateReosurces();
			connection = this.hikariDataSource.getConnection();
			// PreparedStatement pstmt =
			// connection.prepareStatement(this.hikariDataSource.getConnectionTestQuery());

			PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM AGENT");
			rs = pstmt.executeQuery();
			if (rs.next()) {
				LOGGER.info("Connection Tested:: OK");

			}

		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			connection.close();
			this.hikariDataSource.close();
			// this.dataSource.getConnection().close();
		}
	}

	@Override
	public void populateReosurces() {
		// TODO Auto-generated method stub
		LOGGER.info("Populating Reosurce");
		try {
			this.resourceDatabasePopulator.addScripts(new ClassPathResource("database/create_drop.sql"),
					new ClassPathResource("database/schema.sql"));
			this.resourceDatabasePopulator.populate(this.hikariDataSource.getConnection());
		} catch (ScriptException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
