package com.demo.taskdemo.runner;

import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component("billSetupTaskRunner")
public class BillSetupTaskRunner implements CommandLineRunner {

	private static final Logger LOGGER = LogManager.getLogger(BillSetupTaskRunner.class);

	private DataSource dataSource;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		LOGGER.info("Running command line runner");
		JdbcTemplate jdbcTemplate = new JdbcTemplate(this.dataSource);
		try {
			jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS BILL_STATEMENTS ( id int, "
					+ " first_name varchar(50), last_name varchar(50), minutes int, "
					+ " data_usage int , bill_amount decimal(10,2)) ");
			jdbcTemplate.execute(
					"INSERT INTO BILL_STATEMENTS VALUES(12345 , 'Paul' , 'Soumyadeep' , 226600 , 559966, 5000.66)");

			LOGGER.info("Data inserted successfully");
		} catch (Exception e) {
			// TODO: handle exception
			LOGGER.error(e.getCause());
			LOGGER.error(e.getMessage());
		}

	}

}
