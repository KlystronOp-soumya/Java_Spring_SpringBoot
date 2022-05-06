package com.demo.batch.BatchDemo.entity;

import java.io.Serializable;
import java.math.BigDecimal;

public class AgentBnsQualifEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String agtId;
	private String name;
	private String designation;
	private String LOB;
	private BigDecimal bonusAmt;
	private BigDecimal bonusPerct;
	private BigDecimal bonusPayout;
	private int calDay, calMonth, calYear;
	private int qualifStatus;

	public AgentBnsQualifEntity() {
		super();
	}

	public AgentBnsQualifEntity(int id, String name, String designation, String lOB, BigDecimal bonusAmt,
			BigDecimal bonusPayout, int calDay, int calMonth, int calYear) {
		super();
		this.id = id;
		this.name = name;
		this.designation = designation;
		this.LOB = lOB;
		this.bonusAmt = bonusAmt;
		this.bonusPayout = bonusPayout;
		this.calDay = calDay;
		this.calMonth = calMonth;
		this.calYear = calYear;
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

	public BigDecimal getBonusPayout() {
		return bonusPayout;
	}

	public void setBonusPayout(BigDecimal bonusPayout) {
		this.bonusPayout = bonusPayout;
	}

	public int getCalDay() {
		return calDay;
	}

	public void setCalDay(int calDay) {
		this.calDay = calDay;
	}

	public int getCalMonth() {
		return calMonth;
	}

	public void setCalMonth(int calMonth) {
		this.calMonth = calMonth;
	}

	public int getCalYear() {
		return calYear;
	}

	public void setCalYear(int calYear) {
		this.calYear = calYear;
	}

	public int getQualifStatus() {
		return qualifStatus;
	}

	public void setQualifStatus(int qualifStatus) {
		this.qualifStatus = qualifStatus;
	}

	@Override
	public String toString() {
		return "AgentBnsQualifEntity [id=" + id + ", name=" + name + ", designation=" + designation + ", LOB=" + LOB
				+ ", bonusAmt=" + bonusAmt + ", bonusPayout=" + bonusPayout + ", calDay=" + calDay + ", calMonth="
				+ calMonth + ", calYear=" + calYear + "]";
	}

	public BigDecimal getBonusPerct() {
		return bonusPerct;
	}

	public void setBonusPerct(BigDecimal bonusPerct) {
		this.bonusPerct = bonusPerct;
	}

	public String getAgtId() {
		return this.agtId;
	}

	public void setAgtId(String agtId) {
		this.agtId = agtId;
	}

}
