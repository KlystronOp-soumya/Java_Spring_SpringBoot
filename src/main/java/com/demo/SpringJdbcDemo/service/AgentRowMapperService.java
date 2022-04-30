package com.demo.SpringJdbcDemo.service;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.demo.SpringJdbcDemo.entity.AgentEntity;

public class AgentRowMapperService implements RowMapper<AgentEntity> {

	@Override
	public AgentEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		AgentEntity agt = new AgentEntity();
		agt.setCollOff(rs.getString(1));
		agt.setAgtId(rs.getString(2));
		agt.setAgtNo(rs.getString(3));
		agt.setPolNum(rs.getString(4));
		agt.setLOB(rs.getString(5));
		agt.setCommission(rs.getBigDecimal(6));
		return agt;
	}

}
