package com.demo.taskdemo;

import static org.assertj.core.api.Assertions.assertThat;

import javax.sql.DataSource;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootTest
class ElectricBillTaskApplicationTests {

	@Autowired
	private DataSource dataSource;

	@Test
	@Tag("Database_Creation_Test")
	@Order(value = 2)
	public void testRepository() {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(this.dataSource);
		int result = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM BILL_STATEMENTS", Integer.class);
		assertThat(result).isEqualTo(0);
	}

	@Test
	@Tag("Table Existence Check")
	@Order(value = 1)
	public void testTableExistence() {
		Assertions.assertThrows(BadSqlGrammarException.class, () -> {
			JdbcTemplate jdbcTemplate = new JdbcTemplate(this.dataSource);
			jdbcTemplate.queryForObject("SELECT COUNT(*) FROM BILL_STATEMENT", Integer.class);
		});
	}

}
