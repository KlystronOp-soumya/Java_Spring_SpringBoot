package com.demo.SpringOrmDemo.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "AGENT")
public class AgentEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int Id;

	@Column(name = "COLL_OFF")
	private String collOff;

	@Column(name = "AGT_ID")
	private String agentId;

	@Column(name = "AGT_NO", nullable = false)
	private String agentNo;

	@Column(name = "POL_NO", nullable = false)
	private String polNo;

	@Column(name = "LOB", nullable = false)
	private String LOB;

	@Column(name = "commission", nullable = true)
	private BigDecimal commission;

	public String getCollOff() {
		return collOff;
	}

	public void setCollOff(String collOff) {
		this.collOff = collOff;
	}

	public String getAgentId() {
		return agentId;
	}

	public void setAgentId(String agentId) {
		this.agentId = agentId;
	}

	public String getAgentNo() {
		return agentNo;
	}

	public void setAgentNo(String agentNo) {
		this.agentNo = agentNo;
	}

	public String getPolNo() {
		return polNo;
	}

	public void setPolNo(String polNo) {
		this.polNo = polNo;
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
		return "AgentEntity [coll_off=" + collOff + ", agentId=" + agentId + ", agentNo=" + agentNo + ", polNo=" + polNo
				+ ", LOB=" + LOB + ", commission=" + commission + "]";
	}

}
