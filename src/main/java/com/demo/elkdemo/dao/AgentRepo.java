package com.demo.elkdemo.dao;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.demo.elkdemo.entity.Agent;

@Repository
public class AgentRepo {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AgentRepo.class) ;
	
	private List<Agent> agentList ;
	
	public AgentRepo() {
		agentList =  List.of(new Agent(123L, 70098L, "Doe", "Jhon"), new Agent(124L, 70099L, "Steven", "Smith") 
				, new Agent(125L, 70100L, "Paula", "Hawkins") 
				,new Agent(126L, 700101L, "Sydney", "Sheldom") ) ;
	}
	
	public Optional<List<Agent>> getAllAgents()
	{
		LOGGER.info("Inside DAO :: getting agent list from repo");
		Optional<List<Agent>> optAgentList= Optional.of(agentList) ;
		LOGGER.info("Inside DAO :: data was fetched");
		return optAgentList ;
	}
	
	public Agent getAgentById(final Integer id) {
		LOGGER.info("Inside DAO :: getting agent by id ");
		Predicate<Agent> findAgentById = (Agent a) -> a.getAgtId() == Integer.toUnsignedLong(id);
		Optional<Agent> foundAgent = Optional.ofNullable(agentList.stream().findAny().filter(findAgentById).orElseThrow(()-> new NullPointerException())) ;
		LOGGER.info("Inside DAO :: data fetched: " + foundAgent.isPresent());
		return foundAgent.get() ;
		

	}
}
