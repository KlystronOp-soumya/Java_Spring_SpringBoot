package com.demo.SpringBootBatch.readers;

import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.demo.SpringBootBatch.entities.Associates;
import com.demo.SpringBootBatch.utility.AssociateRowMapper;

//@Component
public class AssociateReader {

	/* The Logger */
	private static final Logger LOGGER = LogManager.getLogger(AssociateReader.class);
	// private JdbcTemplate jdbcTemplate;
	private JdbcCursorItemReader<Associates> associateJdbcReader;

	private transient DataSource dataSource;

	public AssociateReader() {
		// TODO Auto-generated constructor stub
		this.associateJdbcReader = new JdbcCursorItemReader<Associates>();
	}

	@Autowired
	@Qualifier("mysql_ds")
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	/*
	 * @Autowired public void
	 * setAssociateJdbcReader(JdbcCursorItemReader<Associates> associateJdbcReader)
	 * { this.associateJdbcReader = associateJdbcReader; }
	 */

	public JdbcCursorItemReader<Associates> itemReader() {
		LOGGER.info("Reading Associate data");
		try {
			this.associateJdbcReader.setName("Associate Data JDBC reader");
			this.associateJdbcReader.setDataSource(this.dataSource);
			this.associateJdbcReader.setRowMapper(new AssociateRowMapper());
			this.associateJdbcReader.setSql(this.getSql());
			this.associateJdbcReader.setConnectionAutoCommit(true);
			this.associateJdbcReader.afterPropertiesSet();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return this.associateJdbcReader;
	}

	private String getSql() {
		String sql = "SELECT * FROM ASSOCIATES";
		return sql;
	}

}
