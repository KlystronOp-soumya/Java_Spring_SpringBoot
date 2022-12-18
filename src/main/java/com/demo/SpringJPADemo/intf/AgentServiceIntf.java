package com.demo.SpringJPADemo.intf;

import java.util.List;

import com.demo.SpringJPADemo.models.AgentEntity;

public interface AgentServiceIntf {

	void saveAgent(AgentEntity agtEntity);

	List<AgentEntity> getAllActiveAgents();

	AgentEntity createAgentEntity();

}
