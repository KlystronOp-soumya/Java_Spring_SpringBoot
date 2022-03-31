package com.demo.poiDemo.util;

import static com.demo.poiDemo.util.LoggerConfigUtil.info;

import java.util.HashMap;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.mysql.cj.jdbc.MysqlDataSource;

public class DBConnectionUtil {

	private static final Logger LOGGER = LogManager.getLogger(DBConnectionUtil.class);

	private transient static HashMap<String, String> dbConfigsMap;

	// Using DataSource

	public static DataSource getMySqlDataSource() {
		LOGGER.info("Connection using the datasource");
		MysqlDataSource dataSource = null;
		LOGGER.info("Configuring MySqlDataSource");
		try {
			dataSource = new MysqlDataSource(); // no requirement of registering the class
			dataSource.setURL(dbConfigsMap.get("DB_URL") + dbConfigsMap.get("DB_SCHEMA"));
			dataSource.setUser(dbConfigsMap.get("DB_USER"));
			dataSource.setPassword(dbConfigsMap.get("DB_PWD"));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		LOGGER.info("MySqlDataSource was successfully configured");
		return dataSource;
	}

	// Using Commons-DBCP connection pooling
	public static BasicDataSource getBasicDataSource() {
		BasicDataSource basicDataSource = null;
		info(LOGGER, "Initializing BasicDataSource");

		try {
			basicDataSource = new BasicDataSource();
			basicDataSource.setDriverClassName(dbConfigsMap.get("DB_DRIVER"));

			basicDataSource.setUrl(dbConfigsMap.get("DB_URL") + dbConfigsMap.get("DB_SCHEMA"));
			basicDataSource.setUsername(dbConfigsMap.get("DB_USER"));
			basicDataSource.setPassword(dbConfigsMap.get("DB_PWD"));
			basicDataSource.setCacheState(true);
			basicDataSource.setPoolPreparedStatements(true);

			info(LOGGER, "Configured properly");
		} catch (Exception e) {
			// TODO: handle exception
		}
		return basicDataSource;
	}
	// using HikariCp

	public HashMap<String, String> getDbConfigsMap() {
		return dbConfigsMap;
	}

	public void setDbConfigsMap(HashMap<String, String> dbConfigsMap) {
		DBConnectionUtil.dbConfigsMap = dbConfigsMap;
	}
}
