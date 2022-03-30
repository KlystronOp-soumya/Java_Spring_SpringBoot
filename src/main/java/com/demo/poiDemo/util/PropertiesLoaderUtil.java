package com.demo.poiDemo.util;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Properties;

public class PropertiesLoaderUtil {

	private transient Properties properties;

	private HashMap<String, String> dbConfigMap;

	public HashMap<String, String> getDatabaseProperties() {
		InputStream stream;
		try {

			this.properties = new Properties();
			stream = PropertiesLoaderUtil.class.getClassLoader().getResourceAsStream("resources/db-config.properties");
			this.properties.load(stream);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return this.dbConfigMap;

	}
}
