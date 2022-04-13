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
	DATABASE_PROPS("database/db-config.properties");

	public String value;

	ConstEnums(String value) {
		this.value = value;
	}
}
