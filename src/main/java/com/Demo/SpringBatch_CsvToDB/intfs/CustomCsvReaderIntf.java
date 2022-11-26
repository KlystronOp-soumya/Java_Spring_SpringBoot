package com.Demo.SpringBatch_CsvToDB.intfs;

import java.io.IOException;

import org.springframework.batch.item.ItemReader;

import com.Demo.SpringBatch_CsvToDB.domain.Associates;

public interface CustomCsvReaderIntf {

	ItemReader<Associates> csvItemReader() throws IOException;

}
