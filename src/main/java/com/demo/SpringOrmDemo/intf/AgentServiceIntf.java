package com.demo.SpringOrmDemo.intf;

import java.util.List;

import com.demo.SpringOrmDemo.entity.AgentEntity;
import com.demo.SpringOrmDemo.entity.AgentQualifEntity;

public interface AgentServiceIntf {

	List<AgentEntity> getAllActiveAgents();

	void saveAgent(AgentEntity agt);

	void saveQaulifAgents(List<AgentQualifEntity> agtQualifList);
}
