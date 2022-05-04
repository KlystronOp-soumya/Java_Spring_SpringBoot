package com.demo.SpringOrmDemo.intf;

import java.util.List;

import com.demo.SpringOrmDemo.DAO.repository.AgentRepository;
import com.demo.SpringOrmDemo.entity.AgentEntity;
import com.demo.SpringOrmDemo.entity.AgentQualifEntity;

public interface AgentService {

	void setAgentRepository(AgentRepository agentRepository);

	List<AgentEntity> getAllActiveAgents();

	void saveAgent(AgentEntity agt);

	void saveQaulifAgents(List<AgentQualifEntity> agtQualifList);

	AgentEntity creatAgentEntity();

	List<AgentEntity> getAllActiveAgentsUsingRepo();
}
