package com.demo.SpringOrmDemo.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Agent")
public class AgentEntity {

	@Column(name = "COLL_OFF", columnDefinition = "Collection Office", length = 3, nullable = false)
	private String collOff;

	@Id
	@Column(name = "AGT_ID", nullable = false)
	private String agentId;
	@Column(name = "AGT_NO", nullable = false)
	private String agentNo;
	@Column(name = "POL_NO", nullable = false)
	private String polNo;
	@Column(name = "LOB", nullable = false)
	private String LOB;
	@Column(name = "commission", nullable = true, precision = 2, length = 11)
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
