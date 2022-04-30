package com.demo.SpringJdbcDemo.entity;

import java.io.Serializable;
import java.math.BigDecimal;

public class AgentEntity implements Serializable {

	/**
	 * This is the AgentEntity POJO
	 */
	private static final long serialVersionUID = 1L;

	private String collOff;
	private String agtId;
	private String agtNo;
	private String polNum;
	private String LOB;
	private BigDecimal commission;

	public String getCollOff() {
		return collOff;
	}

	public void setCollOff(String collOff) {
		this.collOff = collOff;
	}

	public String getAgtId() {
		return agtId;
	}

	public void setAgtId(String agtId) {
		this.agtId = agtId;
	}

	public String getAgtNo() {
		return agtNo;
	}

	public void setAgtNo(String agtNo) {
		this.agtNo = agtNo;
	}

	public String getPolNum() {
		return polNum;
	}

	public void setPolNum(String polNum) {
		this.polNum = polNum;
	}

	public String getLOB() {
		return LOB;
	}

	public void setLOB(String lOB) {
		LOB = lOB;
	}

	public BigDecimal getCommission() {
		return commission;
	}

	public void setCommission(BigDecimal commission) {
		this.commission = commission;
	}

	@Override
	public String toString() {
		return "AgentEntity [collOff=" + collOff + ", agtId=" + agtId + ", agtNo=" + agtNo + ", polNum=" + polNum
				+ ", LOB=" + LOB + ", commission=" + commission + "]";
	}

}
