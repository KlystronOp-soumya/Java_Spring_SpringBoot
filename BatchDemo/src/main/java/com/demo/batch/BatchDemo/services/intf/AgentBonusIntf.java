package com.demo.batch.BatchDemo.services.intf;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.demo.batch.BatchDemo.entity.Agent;
import com.demo.batch.BatchDemo.entity.AgentBnsQualifEntity;

public interface AgentBonusIntf {
	/* Some of the methods will be moved to the DAO */
	void createAgents();

	List<Agent> getAgents();

	List<AgentBnsQualifEntity> getAgtQualifList() throws SQLException;

	ResultSet getAgtBnsQualifResultSet();

	void calculateAgentBns(Agent agtObj);

	void displayAgentDetails();

	void displayAgtBnsDetails();

}
