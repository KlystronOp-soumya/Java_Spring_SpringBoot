package com.Demo.SpringBatch_CsvToDB.utility;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.batch.item.database.ItemPreparedStatementSetter;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.Demo.SpringBatch_CsvToDB.domain.Associates;

public class CSVToDbWriter {

	/* The Logger */
	private static final Logger LOGGER = LogManager.getLogger(CSVToDbWriter.class);
	private JdbcBatchItemWriter<Associates> jdbcBatchItemWriter;

	@Autowired
	@Qualifier("mysql_ds")
	private DataSource dataSource;

	public CSVToDbWriter() {
		super();
		this.jdbcBatchItemWriter = new JdbcBatchItemWriter<Associates>();
	}

	public JdbcBatchItemWriter<Associates> itemWriter() throws CustomException {
		try {
			this.jdbcBatchItemWriter.setDataSource(this.dataSource);
			this.jdbcBatchItemWriter.setSql(this.getSql());
			// this.jdbcBatchItemWriter
			// .setItemSqlParameterSourceProvider(new
			// BeanPropertyItemSqlParameterSourceProvider<Associates>());
			this.jdbcBatchItemWriter.setItemPreparedStatementSetter(new ItemPreparedStatementSetter<Associates>() {

				public void setValues(Associates item, PreparedStatement ps) throws SQLException {
					// TODO Auto-generated method stub
					ps.setString(1, item.getSerialNum());
					ps.setString(2, item.getComapany());
					ps.setString(3, item.getAssociateName());
					ps.setString(4, item.getDescription());
					ps.setString(5, item.getLeaves());
				}
			});
		} catch (Exception e) {
			// TODO: handle exception
			throw new CustomException(e.getMessage(), e);
		}
		return this.jdbcBatchItemWriter;
	}

	private String getSql() {

		String sql = "INSERT INTO ASSOCIATES VALUES(?,?,?,?,?)";

		return sql;
	}

}
