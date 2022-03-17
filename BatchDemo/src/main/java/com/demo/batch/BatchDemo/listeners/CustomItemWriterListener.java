package com.demo.batch.BatchDemo.listeners;

import java.util.List;

import org.springframework.batch.core.ItemWriteListener;

import com.demo.batch.BatchDemo.entity.AgentBnsQualifEntity;

public class CustomItemWriterListener implements ItemWriteListener<AgentBnsQualifEntity> {

	@Override
	public void beforeWrite(List<? extends AgentBnsQualifEntity> items) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterWrite(List<? extends AgentBnsQualifEntity> items) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onWriteError(Exception exception, List<? extends AgentBnsQualifEntity> items) {
		// TODO Auto-generated method stub
		
	}

}
