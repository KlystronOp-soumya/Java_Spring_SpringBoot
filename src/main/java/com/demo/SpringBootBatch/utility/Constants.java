package com.demo.SpringBootBatch.utility;

public enum Constants {

	OUTPUT_CSV_PATH("output/processed_employee.csv"), ALLOWED_LEAVES("5");

	private String value;

	Constants(String value) {
		// TODO Auto-generated constructor stub
		this.value = value;
	}

	public String getValue() {
		return value;
	}

}
