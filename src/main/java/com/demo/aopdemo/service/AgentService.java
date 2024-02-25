package com.demo.aopdemo.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;

import com.demo.aopdemo.AgentException;
import com.demo.aopdemo.aspectanno.Loggable;
import com.demo.aopdemo.dao.AgentRepository;
import com.demo.aopdemo.entity.Agent;

@Service("agentService")
public class AgentService {

	private transient AgentRepository agentRepository ;
	
	public AgentService(final AgentRepository agentRepository) {
	
		this.agentRepository = agentRepository ;
	}
	
	@CachePut(cacheNames = "agentCache" , cacheManager = "agentcacheManager" , key = "#agent.agtId" , condition = "#agent!=null")
	@Loggable
	@org.springframework.transaction.annotation.Transactional(rollbackFor = AgentException.class)
	public Agent saveAgent(final Agent agent) throws AgentException
	{
		try {
			agentRepository.insertAgent(agent.getAgtId(), agent.getAgtNum(), agent.getAgtFname(), agent.getAgtLname(), agent.getPaidComm());
		}catch (Exception e) {
			throw new AgentException("Could not save agent.Please Contact Administrator") ;
		}
		return agent ;
	}

	@Loggable
	@Transactional
	public List<Agent> getAllAgents() throws AgentException {
		List<Agent> agentsList = null ;
		Optional<List<Agent>> agentListOptional = null ;
		try {
			agentsList=agentRepository.findAll() ;
			agentListOptional = Optional.of(agentsList) ;
			if (!agentListOptional.isPresent()) {
				agentListOptional.orElseThrow(NullPointerException::new) ;
			}
		} catch (Exception e) {
			throw new AgentException(e.getMessage()) ;
		}
		
		return agentListOptional.get() ;
	}
	
	@Loggable
	@CachePut(cacheNames = "agentCache" , cacheManager = "agentcacheManager" , key = "#agtId")
	@org.springframework.transaction.annotation.Transactional(readOnly = true , isolation = Isolation.REPEATABLE_READ)
	public Agent getAgentById(final String agtId, final String agtNum) throws AgentException {
		Optional<Agent> foundAgentOptional = Optional.empty() ;
		
		try {
			 if( null == agtNum || agtNum.equals(" "))
			 {
				 foundAgentOptional = agentRepository.findByAgtId(Long.valueOf(agtId)) ;				 	 
			 }
			 else {
				 
			}
			 
			 return foundAgentOptional.orElseThrow(NullPointerException::new) ;
			
		} catch (Exception e) {
			throw new AgentException("Agent not found with id: " + agtId) ;
		}
		
	}

}
