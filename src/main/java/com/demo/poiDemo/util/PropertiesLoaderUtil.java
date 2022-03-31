package com.demo.poiDemo.util;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.demo.poiDemo.enums.ConfigPathEnum;

public class PropertiesLoaderUtil {

	private static final Logger LOGGER = LogManager.getLogger(PropertiesLoaderUtil.class);

	private transient static Properties properties;

	private static HashMap<String, String> dbConfigMap;

	static {
		dbConfigMap = new HashMap<>();
		properties = new Properties();
	}

	public static HashMap<String, String> getDatabaseProperties() {
		InputStream stream;
		try {
			stream = PropertiesLoaderUtil.class.getClassLoader()
					.getResourceAsStream(ConfigPathEnum.DB_CONFIG_PATH.value);
			properties.load(stream);

			LOGGER.info("DB config loaded");
			dbConfigMap.put("DB_DRIVER", properties.getProperty("db_driver"));
			dbConfigMap.put("DB_URL", properties.getProperty("db_url"));
			dbConfigMap.put("DB_USER", properties.getProperty("db_user"));
			dbConfigMap.put("DB_PWD", properties.getProperty("db_pwd"));
			dbConfigMap.put("DB_SCHEMA", properties.getProperty("db_schema"));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return dbConfigMap;

	}
}
