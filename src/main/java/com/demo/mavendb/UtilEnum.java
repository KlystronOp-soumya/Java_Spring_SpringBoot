package com.demo.mavendb;

public enum UtilEnum {

	DB_NAME("test"), COLLECTION_NAME("inventory");

	public String value;

	UtilEnum(String name) {
		// TODO Auto-generated constructor stub
		this.value = name;
	}
}
