package com.demo.SpringJdbcDemo.service;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.demo.SpringJdbcDemo.entity.AgentEntity;

public class AgentResultSetExtractService implements ResultSetExtractor<AgentEntity> {

	@Override
	public AgentEntity extractData(ResultSet rs) throws SQLException, DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

}
