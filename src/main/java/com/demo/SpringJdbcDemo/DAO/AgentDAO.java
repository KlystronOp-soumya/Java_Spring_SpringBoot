package com.demo.SpringJdbcDemo.DAO;

import java.sql.Connection;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.demo.SpringJdbcDemo.intf.AgentRepositoryIntf;
import com.zaxxer.hikari.HikariDataSource;

@Repository
public class AgentDAO implements AgentRepositoryIntf {

	private Connection connection;
	private transient DataSource dataSource;

	private transient HikariDataSource hikariDataSource;

	private JdbcTemplate jdbctemplate;

	@Override
	public void setDataSource(DataSource dataSource) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setHikariDataSource(HikariDataSource dHikariDataSource) {
		// TODO Auto-generated method stub

	}

}
