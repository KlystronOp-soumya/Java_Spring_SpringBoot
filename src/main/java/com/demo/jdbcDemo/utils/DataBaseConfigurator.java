package com.demo.jdbcDemo.utils;

import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import com.demo.jdbcDemo.JdbcEnums;

public class DataBaseConfigurator {

	private static final Logger LOGGER = Logger.getLogger(DataBaseConfigurator.class) ;
	
	public static void loadDBConfigs()
	{
		Properties props = new Properties() ;
		try {
			DataBaseConfigurator obj = new DataBaseConfigurator();
			InputStream is = obj.getConfigAsStream() ;
			LOGGER.info("Database configurations loaded");
			props.load(is);
			String dbConfigFile = props.getProperty(JdbcEnums.DB_CONFIG_PATH.value);
			InputStream isDb = obj.getDBConfigAsStream(dbConfigFile);
			props.load(isDb);
			System.setProperty("msql.driver",props.getProperty("db.driver"));
			System.setProperty("msql.url", props.getProperty("db.url"));
			System.setProperty("msql.userid", props.getProperty("db.user")) ;
			System.setProperty("msql.password", props.getProperty("db.password")) ;
			LOGGER.info("Database configurations set");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private InputStream getConfigAsStream()
	{
		ClassLoader classLoader =this.getClass().getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(JdbcEnums.GENERAL_CONFIG.value);

        // the stream holding the file content
        if (inputStream == null) {
            throw new IllegalArgumentException("file not found! ");
        } else {
            return inputStream;
        }
	}
	
	private InputStream getDBConfigAsStream(String fileName)
	{
		ClassLoader classLoader =this.getClass().getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(fileName);

        // the stream holding the file content
        if (inputStream == null) {
            throw new IllegalArgumentException("file not found! ");
        } else {
            return inputStream;
        }
	}
}
