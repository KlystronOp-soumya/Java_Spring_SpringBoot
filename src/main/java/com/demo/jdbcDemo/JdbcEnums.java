package com.demo.jdbcDemo;

public enum JdbcEnums {

	GENERAL_CONFIG("general-config.properties"),
	DB_CONFIG_PATH("db.config"), 
	LOGGER_CONFIG_PATH("logger.config");
	
	public String value ;
	JdbcEnums(String value) {
		// TODO Auto-generated constructor stub
		this.value = value ;
	}
}
