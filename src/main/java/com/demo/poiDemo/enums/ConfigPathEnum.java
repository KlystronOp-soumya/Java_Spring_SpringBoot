package com.demo.poiDemo.enums;

/*
 * This enum stores the different configuration paths
 * 
 * */
public enum ConfigPathEnum {
	DB_CONFIG_PATH("db-config.properties"), LOGGER_CONFIG_PATH("logger-config.xml"), EXCEL_FILE("agent_data.xls");

	public String value;

	ConfigPathEnum(String value) {
		// TODO Auto-generated constructor stub
		this.value = value;
	}

}
