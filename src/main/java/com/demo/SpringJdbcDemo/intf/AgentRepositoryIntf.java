package com.demo.SpringJdbcDemo.intf;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import com.demo.SpringJdbcDemo.entity.AgentEntity;
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
	public List<AgentEntity> getAllAgentsList();

	// add an agent
	public void addAgent();

	// search an agent
	public AgentEntity searchAgentById(String collOffice, String agentId);

	// update bonus of all agents

}
