package com.demo.pwdmanager;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.sql.DataSource;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.demo.pwdmanager.db.utils.DataSourceUtils;

public class HikaritransacTestSuit {

	private static DataSource dataSource;

	@BeforeClass
	public static void init() {
		dataSource = DataSourceUtils.gethikariDataSource();
	}

	@AfterClass
	public static void tearDown() {
		DataSourceUtils.closeDataSource();
		assertNull("datasource is not null", dataSource);
	}

	@Test
	public void checkDataSource_test() {
		assertNotNull("Datasource is null", dataSource);
	}

	public void UsersPwd_DeleteAll_test() {
		final String query = "DELETE FROM USERS_PWD";
		try {
			Connection connection = dataSource.getConnection();
			PreparedStatement pStatement = connection.prepareStatement(query);
			int res = pStatement.executeUpdate();

			assertEquals("There could be extra data in database", 3, res);
			pStatement.close();
			connection.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
