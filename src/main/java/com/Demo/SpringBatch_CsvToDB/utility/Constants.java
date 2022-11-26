package com.Demo.SpringBatch_CsvToDB.utility;

public enum Constants {

	INPUT_CSV_PATH("inputs/employee.csv"), OUTPUT_CSV_PATH("inputs/processed_employee.csv");

	private String value;

	Constants(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

}
