package com.demo.batch.BatchDemo.DAO.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.demo.batch.BatchDemo.DAO.intf.AgentBonusDAO;
import com.demo.batch.BatchDemo.entity.AgentBnsQualifEntity;

public class AgentBonusDAOImpl implements AgentBonusDAO {

	private transient DataSource dataSource;

	private Connection connection;

	private ResultSet rs;

	@Override
	public List<AgentBnsQualifEntity> getAgentQualifData() {
		// TODO Auto-generated method stub
		List<AgentBnsQualifEntity> qualifAgtList = new ArrayList<>();
		this.connection = null;
		String query = "SELECT AGT_ID,NAME,DESIGNATION,LOB,BONUS,BONUS_PCT,BONUS_PAYOUT,CALDAY,CALMONTH,CALYEAR FROM agent_bonus_qualif";
		try {
			this.connection = this.dataSource.getConnection();
			PreparedStatement pstmt = this.connection.prepareStatement(query);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// Create the POJO
				AgentBnsQualifEntity agentBnsQualifEntity = new AgentBnsQualifEntity();
				agentBnsQualifEntity.setAgtId(rs.getString("AGT_ID"));
				agentBnsQualifEntity.setName(rs.getString("NAME"));
				agentBnsQualifEntity.setDesignation(rs.getString("DESIGNATION"));
				agentBnsQualifEntity.setLOB(rs.getString("LOB"));
				agentBnsQualifEntity.setBonusAmt(rs.getBigDecimal("BONUS"));
				agentBnsQualifEntity.setBonusPerct(rs.getBigDecimal("BONUS_PCT"));
				agentBnsQualifEntity.setBonusPayout(rs.getBigDecimal("BONUS_PAYOUT"));
				agentBnsQualifEntity.setCalDay(rs.getInt("CALDAY"));
				agentBnsQualifEntity.setCalMonth(rs.getInt("CALMONTH"));
				agentBnsQualifEntity.setCalYear(rs.getInt("CALYEAR"));

				qualifAgtList.add(agentBnsQualifEntity);

			}
			if (qualifAgtList == null || qualifAgtList.isEmpty())
				throw new NullPointerException("The qualified Agent List Can not be empty");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return qualifAgtList;
	}

	@Override
	public void setDataSource(DataSource dataSource) {
		// TODO Auto-generated method stub
		this.dataSource = dataSource;

	}

	@Override
	public void saveQualifAgentList(List<AgentBnsQualifEntity> agentBnsQualifList) {
		// TODO Auto-generated method stub
		// to be used in the Writer
	}

}
