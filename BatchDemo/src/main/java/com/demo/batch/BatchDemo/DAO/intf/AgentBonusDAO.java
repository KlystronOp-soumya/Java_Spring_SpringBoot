package com.demo.batch.BatchDemo.DAO.intf;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import com.demo.batch.BatchDemo.entity.AgentBnsQualifEntity;

public interface AgentBonusDAO {

	void setDataSource(DataSource dataSource);

	List<AgentBnsQualifEntity> getAgentQualifData() throws SQLException;

	ResultSet getAgentBnsQualifResultSet();

	void saveQualifAgentList(List<AgentBnsQualifEntity> agentBnsQualifList);
}
