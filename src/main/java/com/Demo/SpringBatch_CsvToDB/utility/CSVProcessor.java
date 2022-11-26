package com.Demo.SpringBatch_CsvToDB.utility;

import org.springframework.batch.item.ItemProcessor;

import com.Demo.SpringBatch_CsvToDB.domain.Associates;

public class CSVProcessor implements ItemProcessor<Associates, Associates> {

	@Override
	public Associates process(Associates item) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("Processing: " + item.toString());
		return item;
	}

}
