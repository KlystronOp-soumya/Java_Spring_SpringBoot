package com.demo.SpringJPADemo.util;

public enum ConstantsEnum {

	SPRING_CONTEXT_PATH("context/spring-jdbc-context.xml"), DATABASE_CONTEXT_PATH("database/db-config-context.xml"),
	DATABASE_PROPS("database/db-config.properties"), CREATE_SCRIPT_CLASSPATH("database/create_drop.sql"),
	INSERT_DATA_CLASSPATH("database/schema.sql");

	public String value;

	ConstantsEnum(String value) {
		// TODO Auto-generated constructor stub
		this.value = value;
	}
}
