package com.demo.mongodemo;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.stereotype.Service;

@Service("agentService")
public class AgentService {

	/* the logger */
	private static final Logger LOGGER = LogManager.getLogger(AgentService.class);

	private transient MongoOperations mongoTemplate;

	public AgentService(final MongoOperations mongoTemplate) {

		this.mongoTemplate = mongoTemplate;
	}

	public void saveAnAgent(final Agent agent) {

		try {
			Agent agent2 = this.mongoTemplate.save(agent, "SPTEST");
			if (agent2 == null)
				throw new NullPointerException("Agent was not saved");
		} catch (Exception e) {
			e.printStackTrace();
		}
		LOGGER.debug("Agent was saved");
	}

	public List<Agent> getAgentInfo(final BasicQuery query) {
		List<Agent> agentList = null;
		try {
			agentList = this.mongoTemplate.find(query, Agent.class);
		} catch (Exception e) {
			// TODO: handle exception
		}

		return agentList;
	}

	public List<Agent> getAllAgentInfo() {
		List<Agent> agentList = null;
		try {
			agentList = this.mongoTemplate.findAll(Agent.class, "SPTEST");
		} catch (Exception e) {
			// TODO: handle exception
		}

		return agentList;
	}
}
