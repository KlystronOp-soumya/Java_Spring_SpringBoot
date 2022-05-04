package com.demo.batch.BatchDemo.readers;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

import com.demo.batch.BatchDemo.entity.AgentBnsQualifEntity;

public class CustomeAgtBnsDetailsReader implements ItemReader<AgentBnsQualifEntity> {

	@Override
	public AgentBnsQualifEntity read()
			throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
		// TODO Auto-generated method stub
		return null;
	}

}
