package com.demo.batch.BatchDemo.listeners;

import org.springframework.batch.core.ItemProcessListener;

import com.demo.batch.BatchDemo.entity.Agent;
import com.demo.batch.BatchDemo.entity.AgentBnsQualifEntity;

public class CustomItemProcessorListener implements ItemProcessListener<Agent, AgentBnsQualifEntity> {

	@Override
	public void beforeProcess(Agent item) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterProcess(Agent item, AgentBnsQualifEntity result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onProcessError(Agent item, Exception e) {
		// TODO Auto-generated method stub
		
	}

}
