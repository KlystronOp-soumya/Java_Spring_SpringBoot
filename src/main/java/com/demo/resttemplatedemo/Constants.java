package com.demo.resttemplatedemo;

public enum Constants {

	NY_TIMES_ENDPOINT("https://api.nytimes.com/svc/topstories/v2/science.json?"),
	NY_TIMES_API_KEY("D5aKLQYODd4hHcgHEDLuNGdPr6lG1gg3"), NY_TIMES_SECRET("mndsNMAySZTR1JkB");

	private String value;

	Constants(String value) {
		// TODO Auto-generated constructor stub
		this.value = value;
	}

	public String getValue() {
		return value;
	}

}
