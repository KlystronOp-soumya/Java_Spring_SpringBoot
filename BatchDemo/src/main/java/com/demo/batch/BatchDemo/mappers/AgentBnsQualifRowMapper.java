package com.demo.batch.BatchDemo.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;

import com.demo.batch.BatchDemo.entity.AgentBnsQualifEntity;

public class AgentBnsQualifRowMapper implements RowMapper<AgentBnsQualifEntity> {

	/* logger */
	private static final Logger LOGGER = Logger.getLogger(AgentBnsQualifRowMapper.class);

	@Override
	public AgentBnsQualifEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		LOGGER.info("Mapping Row for Agent Bonus Qualif Entity");
		AgentBnsQualifEntity agtBnsQualifEntity = new AgentBnsQualifEntity();
		agtBnsQualifEntity.setAgtId(rs.getString("AGT_ID"));
		agtBnsQualifEntity.setName(rs.getString("NAME"));
		agtBnsQualifEntity.setDesignation(rs.getString("DESIGNATION"));
		agtBnsQualifEntity.setLOB(rs.getString("LOB"));
		agtBnsQualifEntity.setBonusAmt(rs.getBigDecimal("BONUS"));
		agtBnsQualifEntity.setBonusPerct(rs.getBigDecimal("BONUS_PCT"));
		agtBnsQualifEntity.setBonusPayout(rs.getBigDecimal("BONUS_PAYOUT"));
		agtBnsQualifEntity.setCalDay(rs.getInt("CALDAY"));
		agtBnsQualifEntity.setCalMonth(rs.getInt("CALMONTH"));
		agtBnsQualifEntity.setCalYear(rs.getInt("CALYEAR"));
		agtBnsQualifEntity.setQualifStatus(rs.getInt("QUALIF_STATUS"));
		return agtBnsQualifEntity;
	}

}
