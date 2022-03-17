package com.demo.batch.BatchDemo.entity;

import java.math.BigDecimal;

public class Agent {
	private int id ;
	private String agtId;
	private String name ;
	private String designation ;
	private String LOB ;
	private BigDecimal bonusAmt ;


	public Agent() {
		super();
	}
	public Agent(int id, String name, String designation, String lOB, BigDecimal bonusAmt
			) {
		super();
		this.id = id;
		this.name = name;
		this.designation = designation;
		LOB = lOB;
		this.bonusAmt = bonusAmt;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public String getLOB() {
		return LOB;
	}
	public void setLOB(String lOB) {
		LOB = lOB;
	}
	public BigDecimal getBonusAmt() {
		return bonusAmt;
	}
	public void setBonusAmt(BigDecimal bonusAmt) {
		this.bonusAmt = bonusAmt;
	}
	public String getAgtId() {
		return agtId;
	}
	public void setAgtId(String agtId) {
		this.agtId = agtId;
	}

	@Override
	public String toString() {
		return "Agent [id=" + id + ", name=" + name + ", designation=" + designation + ", LOB=" + LOB + ", bonusAmt="
				+ bonusAmt + "]";
	}
	


}
