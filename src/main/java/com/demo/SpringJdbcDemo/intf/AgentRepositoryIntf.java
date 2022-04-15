package com.demo.SpringJdbcDemo.intf;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import com.zaxxer.hikari.HikariDataSource;

public interface AgentRepositoryIntf {
//TODO Add setter injection for the DataSource;Implement DataSourceUtils as well

	public void populateReosurces();

	public void setResourcePopulator(ResourceDatabasePopulator resourceDatabasePopulator);

	// public void setDataSource(DataSource dataSource);

	/* Should not be removed */
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate);

	/* Should not be removed */
	// public void setNamedJdbcTemplate(NamedParameterJdbcTemplate
	// namedParameterJdbcTemplate);

	public void setHikariDataSource(HikariDataSource dHikariDataSource);

	// get all agents

	// add an agent

	// search an agent

	// update bonus of all agents

}
