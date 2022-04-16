package com.demo.SpringOrmDemo.service;

import java.math.BigDecimal;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;

import com.demo.SpringOrmDemo.entity.AgentEntity;
import com.demo.SpringOrmDemo.entity.AgentQualifEntity;
import com.demo.SpringOrmDemo.intf.AgentDao;
import com.demo.SpringOrmDemo.intf.AgentService;

public class AgentServiceImpl implements AgentService {

	@Autowired
	private transient AgentDao agentDao;

	// constructor injection to be implemented
	@Override
	@Transactional
	public List<AgentEntity> getAllActiveAgents() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public void saveAgent(AgentEntity agt) {
		// TODO Auto-generated method stub
		this.agentDao.saveAgent(agt);
	}

	@Override
	@Transactional
	public void saveQaulifAgents(List<AgentQualifEntity> agtQualifList) {
		// TODO Auto-generated method stub

	}

	@Override
	public AgentEntity creatAgentEntity() {
		AgentEntity obj = new AgentEntity();
		obj.setCollOff("227");
		obj.setAgentId("AG12345");
		obj.setAgentNo("1224");
		obj.setPolNo("U1234");
		obj.setLOB("10");
		obj.setCommission(new BigDecimal(2000));

		return obj;

	}

}
