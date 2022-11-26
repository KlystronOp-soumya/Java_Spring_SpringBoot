package com.Demo.SpringBatch_CsvToDB.domain;

import java.io.Serializable;

public class Associates implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String serialNum;
	private String comapany;
	private String associateName;
	private String description;
	private String leaves;

	public Associates() {
		super();
	}

	public Associates(String serialNum, String comapany, String associateName, String description, String leaves) {
		super();
		this.serialNum = serialNum;
		this.comapany = comapany;
		this.associateName = associateName;
		this.description = description;
		this.leaves = leaves;
	}

	@Override
	public String toString() {
		return "Associates [serialNum=" + serialNum + ", comapany=" + comapany + ", associateName=" + associateName
				+ ", description=" + description + ", leaves=" + leaves + "]";
	}

	public String getSerialNum() {
		return serialNum;
	}

	public void setSerialNum(String serialNum) {
		this.serialNum = serialNum;
	}

	public String getComapany() {
		return comapany;
	}

	public void setComapany(String comapany) {
		this.comapany = comapany;
	}

	public String getAssociateName() {
		return associateName;
	}

	public void setAssociateName(String associateName) {
		this.associateName = associateName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLeaves() {
		return leaves;
	}

	public void setLeaves(String leaves) {
		this.leaves = leaves;
	}

}
