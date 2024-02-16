package com.demo.elkdemo.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.elkdemo.dao.AgentRepo;
import com.demo.elkdemo.entity.Agent;

@Service
public class AgentService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AgentService.class) ;
	
	private transient AgentRepo agentRepo ;
	
	public AgentService(final AgentRepo agentRepo) {
		this.agentRepo = agentRepo ;
	}
	
	public List<Agent> getAllAgents() {
		LOGGER.info("Inside getAllAgents :: trying to get agent list from Repo");
		return this.agentRepo.getAllAgents().get() ;
		
	}
	
	public Agent getAgentDetailsById(final int id) {
		LOGGER.info("Inside getAgentDetailsById :: trying to get agent data for: " + id);
		return this.agentRepo.getAgentById(id) ;
		
	}

}
