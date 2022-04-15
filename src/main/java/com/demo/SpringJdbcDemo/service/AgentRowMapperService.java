package com.demo.SpringJdbcDemo.service;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.demo.SpringJdbcDemo.entity.AgentEntity;

public class AgentRowMapperService implements RowMapper<AgentEntity> {

	@Override
	public AgentEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
