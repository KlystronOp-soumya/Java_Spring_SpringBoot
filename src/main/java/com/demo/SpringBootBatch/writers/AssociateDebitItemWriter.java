package com.demo.SpringBootBatch.writers;

import java.io.IOException;
import java.io.Writer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.batch.item.file.FlatFileHeaderCallback;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.demo.SpringBootBatch.entities.AssociateDebit;
import com.demo.SpringBootBatch.utility.Constants;

@Component
public class AssociateDebitItemWriter {

	/* The Logger */
	private static final Logger LOGGER = LogManager.getLogger(AssociateDebitItemWriter.class);

	private Resource resource;
	private BeanWrapperFieldExtractor<AssociateDebit> assoDebitFieldExtractor;
	private FlatFileItemWriter<AssociateDebit> associateDebitItemWirter;
	private DelimitedLineAggregator<AssociateDebit> delimitedLineAggregator;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public AssociateDebitItemWriter() {
		this.associateDebitItemWirter = new FlatFileItemWriter<>();
		this.assoDebitFieldExtractor = new BeanWrapperFieldExtractor<>();
		this.delimitedLineAggregator = new DelimitedLineAggregator<AssociateDebit>();
	}

	/*
	 * @Autowired public void
	 * setAssoDebitFieldSetMapper(BeanWrapperFieldExtractor<AssociateDebit>
	 * assoDebitFieldExtractor) { this.assoDebitFieldExtractor =
	 * assoDebitFieldExtractor; }
	 * 
	 * @Autowired public void
	 * setAssociateDebitItemWirter(FlatFileItemWriter<AssociateDebit>
	 * associateDebitItemWirter) { this.associateDebitItemWirter =
	 * associateDebitItemWirter; }
	 */

	/*
	 * @Autowired public void
	 * setDelimitedLineAggregator(DelimitedLineAggregator<AssociateDebit>
	 * delimitedLineAggregator) { this.delimitedLineAggregator =
	 * delimitedLineAggregator; }
	 */

	public FlatFileItemWriter<AssociateDebit> itemWriter() {
		LOGGER.info("Configuring FlatFileWriter");
		try {

			this.assoDebitFieldExtractor.setNames(new String[] { "serialNum", "associateName", "leaves", "debitAmt" });
			this.assoDebitFieldExtractor.afterPropertiesSet();
			final String exportFileHeader = "SerialNum,AssociateName,Leave,DebitAmount";

			this.delimitedLineAggregator.setDelimiter(",");
			this.delimitedLineAggregator.setFieldExtractor(this.assoDebitFieldExtractor);

			// set the FaltFileItemReader
			this.associateDebitItemWirter.setResource(this.writeResource());
			this.associateDebitItemWirter.setHeaderCallback(new FlatFileHeaderCallback() {

				@Override
				public void writeHeader(Writer writer) throws IOException {
					// TODO Auto-generated method stub
					writer.write(exportFileHeader);
				}
			});
			this.associateDebitItemWirter.setLineAggregator(delimitedLineAggregator);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		LOGGER.info("FlatFileWriter configuration completed");
		return this.associateDebitItemWirter;
	}

	// getter for Resource
	private Resource writeResource() {
		LOGGER.info("Gettng the resource from: " + Constants.OUTPUT_CSV_PATH.getValue());
		try {
			this.resource = new ClassPathResource(Constants.OUTPUT_CSV_PATH.getValue());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return this.resource;
	}
}
