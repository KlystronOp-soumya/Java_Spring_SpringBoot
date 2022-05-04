package com.demo.batch.BatchDemo.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.demo.batch.BatchDemo.entity.AgentBnsQualifEntity;

public class AgentBnsQualifRowMapper implements RowMapper<AgentBnsQualifEntity> {

	@Override
	public AgentBnsQualifEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
