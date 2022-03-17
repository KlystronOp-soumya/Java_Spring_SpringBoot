package com.demo.batch.BatchDemo.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;

import com.demo.batch.BatchDemo.entity.Agent;
import com.demo.batch.Util.LoggerUtil;

public class AgentDtlsRowMapper implements RowMapper<Agent>{

	/* logger */
	private static final Logger LOGGER = Logger.getLogger(AgentDtlsRowMapper.class) ;
	@Override
	public Agent mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		Agent agtObj = new Agent() ;
		agtObj.setAgtId(rs.getString("AGT_ID"));
		agtObj.setName(rs.getString("NAME"));
		agtObj.setDesignation(rs.getString("DESIGNATION"));
		agtObj.setLOB(rs.getString("LOB"));
		agtObj.setBonusAmt(rs.getBigDecimal("BONUS"));
		LoggerUtil.info(LOGGER, "Agent obj mapped");
		return agtObj;
	}

}
