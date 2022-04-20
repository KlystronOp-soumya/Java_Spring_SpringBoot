package com.demo.batch.BatchDemo.DAO.intf;

import java.util.List;

import javax.sql.DataSource;

import com.demo.batch.BatchDemo.entity.AgentBnsQualifEntity;

public interface AgentBonusDAO {

	void setDataSource(DataSource dataSource);

	List<AgentBnsQualifEntity> getAgentQualifData();

	void saveQualifAgentList(List<AgentBnsQualifEntity> agentBnsQualifList);
}
