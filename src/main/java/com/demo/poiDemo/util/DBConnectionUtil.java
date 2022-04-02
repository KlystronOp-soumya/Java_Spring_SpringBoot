package com.demo.poiDemo.util;

import static com.demo.poiDemo.util.LoggerConfigUtil.info;

import java.util.HashMap;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.mysql.cj.jdbc.MysqlDataSource;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

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
			basicDataSource.setDefaultAutoCommit(true);

			info(LOGGER, "Configured properly");
		} catch (Exception e) {
			// TODO: handle exception
		}
		return basicDataSource;
	}

	// using HikariCp
	public static HikariDataSource getHikariDataSource() {
		HikariDataSource hikariDataSource = null;
		HikariConfig hikariConfig = null;

		try {
			/*
			 * hikariDataSource = new HikariDataSource();
			 * hikariDataSource.setDriverClassName(dbConfigsMap.get("DB_DRIVER"));
			 * hikariDataSource.setJdbcUrl(dbConfigsMap.get("DB_URL"));
			 * hikariDataSource.setUsername(dbConfigsMap.get("DB_USER"));
			 * hikariDataSource.setPassword(dbConfigsMap.get("DB_PWD"));
			 * hikariDataSource.setSchema(dbConfigsMap.get("DB_SCHEMA"));
			 * hikariDataSource.setAutoCommit(true);
			 * hikariDataSource.setMaximumPoolSize(20);
			 */
			hikariConfig = new HikariConfig(); // constructor with the file path is also available
			hikariConfig.setDriverClassName(dbConfigsMap.get("DB_DRIVER"));
			hikariConfig.setSchema(dbConfigsMap.get("DB_SCHEMA"));
			hikariConfig.setJdbcUrl(dbConfigsMap.get("DB_URL") + hikariConfig.getSchema());
			hikariConfig.setUsername(dbConfigsMap.get("DB_USER"));
			hikariConfig.setPassword(dbConfigsMap.get("DB_PWD"));

			hikariConfig.setMaximumPoolSize(20);
			hikariConfig.addDataSourceProperty("cachePrepStmts", "true");
			hikariConfig.addDataSourceProperty("prepStmtCacheSize", "250");
			hikariConfig.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
			hikariDataSource = new HikariDataSource(hikariConfig);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return hikariDataSource;
	}

	public HashMap<String, String> getDbConfigsMap() {
		return dbConfigsMap;
	}

	public void setDbConfigsMap(HashMap<String, String> dbConfigsMap) {
		DBConnectionUtil.dbConfigsMap = dbConfigsMap;
	}
}
