package com.demo.SpringOrmDemo.Util;

public enum ConfigPathEnum {

	SPRING_CONTEXT_PATH("contexts/spring_application_context.xml"), SPRING_DB_CONTEXT("database/spring_db_context.sql");

	public String value;

	ConfigPathEnum(String value) {
		this.value = value;
	}
}
