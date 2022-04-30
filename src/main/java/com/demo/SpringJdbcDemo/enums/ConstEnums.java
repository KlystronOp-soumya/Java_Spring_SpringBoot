/**
 * 
 */
package com.demo.SpringJdbcDemo.enums;

/**
 * @author SOUMYADEEP PAUL
 *
 */
public enum ConstEnums {

	SPRING_CONTEXT_PATH("context/spring-jdbc-context.xml"), DATABASE_CONTEXT_PATH("database/db-config-context.xml"),
	DATABASE_PROPS("database/db-config.properties"), CREATE_SCRIPT_CLASSPATH("database/create_drop.sql"),
	INSERT_DATA_CLASSPATH("database/schema.sql");

	public String value;

	ConstEnums(String value) {
		this.value = value;
	}
}
