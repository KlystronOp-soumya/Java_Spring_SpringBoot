package com.demo.pwdmanager.db.utils;

import java.util.Properties;

import javax.sql.DataSource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

/*
 * autoCommit
connectionTimeout
idleTimeout
maxLifetime
connectionTestQuery
connectionInitSql
validationTimeout
maximumPoolSize
poolName
allowPoolSuspension
readOnly
transactionIsolation
leakDetectionThreshold
 * */
public class DataSourceUtils {

	private static transient HikariDataSource hikariDataSource = null;

	public static DataSource gethikariDataSource() {
		HikariConfig hikariConfig;

		Properties dbProps = null;
		try {
			if (null == hikariDataSource || hikariDataSource.isClosed()) {// then only create the datasource
				dbProps = PropertyUtils.loadDatabaseProps();
				if (dbProps.isEmpty() || null == dbProps) {
					throw new NullPointerException();
				} else {
					hikariConfig = new HikariConfig();
					hikariConfig.setDriverClassName(dbProps.getProperty("db.driver"));
					hikariConfig.setUsername(dbProps.getProperty("db.user"));
					hikariConfig.setJdbcUrl(dbProps.getProperty("db.url"));
					hikariConfig.setPassword(dbProps.getProperty("db.password"));
					hikariConfig.setPoolName("pwdMngrPool");
					hikariConfig.addDataSourceProperty("maximumPoolSize", 20);
					hikariConfig.setAutoCommit(true);
					hikariDataSource = new HikariDataSource(hikariConfig);
				}
			} else {
				return hikariDataSource;
			}
		} catch (Exception e) {

			e.printStackTrace();
		}

		return hikariDataSource;
	}

	public static void closeDataSource() {
		try {
			if (!hikariDataSource.isClosed())
				hikariDataSource.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
