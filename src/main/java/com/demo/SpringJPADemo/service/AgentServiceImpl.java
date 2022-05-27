package com.demo.SpringJPADemo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.SpringJPADemo.intf.AgentServiceIntf;
import com.demo.SpringJPADemo.models.AgentEntPK;
import com.demo.SpringJPADemo.models.AgentEntity;
import com.demo.SpringJPADemo.repositories.CustomAgentRepository;

@Service
public class AgentServiceImpl implements AgentServiceIntf {

	@Autowired
	private CustomAgentRepository agentRepo;

	@Override
	public void saveAgent(AgentEntity agtEntity) {
		// TODO Auto-generated method stub
		System.out.println("SAVING AGENT......");
		AgentEntity agentEntity = this.agentRepo.saveAndFlush(agtEntity);
		if (agentEntity != null) {
			System.out.println("AGENT SAVED..." + agentEntity);
		}

	}

	@Override
	public List<AgentEntity> getAllActiveAgents() {
		// TODO Auto-generated method stub

		List<AgentEntity> agentList = (List<AgentEntity>) agentRepo.findAll();
		return agentList;
	}

	public AgentEntity createAgentEntity() {
		AgentEntity agtEntity = new AgentEntity();
		agtEntity.setAgentEntPK(new AgentEntPK("227", "AG12345"));
		agtEntity.setFname("A");
		agtEntity.setLname("BC");
		agtEntity.setAgentNo("12345");

		agtEntity.setContactNum("6298555332");
		agtEntity.setEmailId("abcd@gmail.com");
		return agtEntity;

	}
}
