package com.demo.SpringOrmDemo.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "AGENT_QUALIF")
public class AgentQualifEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int Id;

	@Column(name = "COLL_OFF", columnDefinition = "Collection Office", length = 3, nullable = false)
	private String coll_off;

	@Column(name = "AGT_ID", nullable = false)
	private String agentId;
	@Column(name = "AGT_NO", nullable = false)
	private String agentNo;
	@Column(name = "POL_NO", nullable = false)
	private String polNo;
	@Column(name = "LOB", nullable = false)
	private String LOB;
	@Column(name = "QUALIF_STATUS", nullable = false)
	private String qualifStatus;
	@Column(name = "AGT_BONUS", nullable = true, length = 7)
	private BigDecimal agentBonus;
	@Column(name = "calDay", nullable = false)
	private int calDay;
	@Column(name = "calMonth", nullable = false)
	private int calMonth;
	@Column(name = "calYear", nullable = false)
	private int calyear;
	@Column(name = "transasction_date", nullable = false)
	private Date transactionDate;

	public String getColl_off() {
		return coll_off;
	}

	public void setColl_off(String coll_off) {
		this.coll_off = coll_off;
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

	public String getQualifStatus() {
		return qualifStatus;
	}

	public void setQualifStatus(String qualifStatus) {
		this.qualifStatus = qualifStatus;
	}

	public BigDecimal getAgentBonus() {
		return agentBonus;
	}

	public void setAgentBonus(BigDecimal agentBonus) {
		this.agentBonus = agentBonus;
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

	public int getCalyear() {
		return calyear;
	}

	public void setCalyear(int calyear) {
		this.calyear = calyear;
	}

	public Date getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}

	@Override
	public String toString() {
		return "AgentQualifEntity [coll_off=" + coll_off + ", agentId=" + agentId + ", agentNo=" + agentNo + ", polNo="
				+ polNo + ", LOB=" + LOB + ", qualifStatus=" + qualifStatus + ", agentBonus=" + agentBonus + ", calDay="
				+ calDay + ", calMonth=" + calMonth + ", calyear=" + calyear + ", transactionDate=" + transactionDate
				+ "]";
	}

}
