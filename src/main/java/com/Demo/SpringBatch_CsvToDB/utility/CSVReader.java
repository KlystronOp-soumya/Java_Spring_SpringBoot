package com.Demo.SpringBatch_CsvToDB.utility;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import com.Demo.SpringBatch_CsvToDB.domain.Associates;
import com.Demo.SpringBatch_CsvToDB.intfs.CustomCsvReaderIntf;

public class CSVReader implements CustomCsvReaderIntf {

	/* The Logger */
	private static final Logger LOGGER = LogManager.getLogger(CSVReader.class);
	private Resource resource;
	private FlatFileItemReader<Associates> itemReader;
	private DefaultLineMapper<Associates> lineMapper;
	private BeanWrapperFieldSetMapper<Associates> fieldSetMapper;

	public CSVReader() {
		this.itemReader = new FlatFileItemReader<Associates>();
		this.lineMapper = new DefaultLineMapper<Associates>();
		this.fieldSetMapper = new BeanWrapperFieldSetMapper<Associates>();
	}

	// getter for Resource
	private Resource readResource() {
		LOGGER.info("Gettng the resource from: " + Constants.INPUT_CSV_PATH.getValue());
		try {
			this.resource = new ClassPathResource(Constants.INPUT_CSV_PATH.getValue());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return this.resource;
	}

	public FlatFileItemReader<Associates> csvItemReader() {
		LOGGER.info("Configuring the FlatFileItemReader...");
		try {
			this.itemReader.setLineMapper(this.lineMapper());
			this.itemReader.setLinesToSkip(1);
			this.itemReader.setResource(this.readResource());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		LOGGER.info("FlatFile Configuration processed");
		return this.itemReader;
	}

	private DefaultLineMapper<Associates> lineMapper() {
		LOGGER.info("Line mapper configuration");
		try {
			DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
			lineTokenizer.setNames(new String[] { "SerialNum", "Company", "AssociateName", "Description", "Leave" });
			// lineTokenizer.setIncludedFields(new int[] { 0, 1, 2, 3, 4 });
			this.fieldSetMapper.setTargetType(Associates.class);
			this.lineMapper.setLineTokenizer(lineTokenizer);
			this.lineMapper.setFieldSetMapper(fieldSetMapper);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		LOGGER.info("Line mapper configuration processed");
		return this.lineMapper;

	}

}
