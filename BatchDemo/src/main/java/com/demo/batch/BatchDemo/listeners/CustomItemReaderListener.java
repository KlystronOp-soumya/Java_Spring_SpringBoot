package com.demo.batch.BatchDemo.listeners;

import org.apache.log4j.Logger;
import org.springframework.batch.core.ItemReadListener;

import com.demo.batch.BatchDemo.entity.Agent;

public class CustomItemReaderListener implements ItemReadListener<Agent> {

	private static final Logger LOGGER = Logger.getLogger(CustomItemReaderListener.class);

	@Override
	public void beforeRead() {
		// TODO Auto-generated method stub
		LOGGER.info("Reading Agent Entity...Started");

	}

	@Override
	public void afterRead(Agent item) {
		// TODO Auto-generated method stub
		LOGGER.info("Reading Agent Entity...Ended");
	}

	@Override
	public void onReadError(Exception ex) {
		// TODO Auto-generated method stub
		throw new RuntimeException(ex.getMessage());
	}

}
