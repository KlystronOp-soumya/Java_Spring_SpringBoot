package com.demo.service;

import java.util.ArrayList;
import java.util.List;

public class AgentServiceImpl {

	public List<Agent> getAgentsList() {
		List<Agent> agentList = new ArrayList<>();
		System.out.println("AgentServiceImpl.getAgentsList()");
		return agentList;
	}

	public void check() {
		System.out.println("AgentServiceImpl.check()");
	}

}
