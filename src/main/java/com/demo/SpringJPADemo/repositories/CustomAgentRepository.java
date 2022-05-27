package com.demo.SpringJPADemo.repositories;

import org.springframework.stereotype.Repository;

import com.demo.SpringJPADemo.models.AgentEntPK;
import com.demo.SpringJPADemo.models.AgentEntity;

@Repository
public interface CustomAgentRepository extends CustomBaseRepository<AgentEntity, AgentEntPK> {

	// custom methods
	/*
	 * find and delete by collOff+AgentId composite key
	 * 
	 */

}
