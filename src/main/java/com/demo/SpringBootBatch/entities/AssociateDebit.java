package com.demo.SpringBootBatch.entities;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Associate_Debit")
public class AssociateDebit implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private String debit_id;

	@Column(name = "ASSOCIATE_NAME")
	private String associateName;
	@Column(name = "LEAVES")
	private String leaves;
	@Column(name = "DEBIT_AMT", precision = 2)
	private BigDecimal debitAmt;
	@Column(name = "SERIAL_NUM")
	private String serialNum;

	public String getAssociateName() {
		return associateName;
	}

	public void setAssociateName(String associateName) {
		this.associateName = associateName;
	}

	public String getLeaves() {
		return leaves;
	}

	public void setLeaves(String leaves) {
		this.leaves = leaves;
	}

	public BigDecimal getDebitAmt() {
		return debitAmt;
	}

	public void setDebitAmt(BigDecimal debitAmt) {
		this.debitAmt = debitAmt;
	}

	public String getSerialNum() {
		return serialNum;
	}

	public void setSerialNum(String serialNum) {
		this.serialNum = serialNum;
	}

	@Override
	public String toString() {
		return "AssociateDebit [debit_id=" + debit_id + ", associateName=" + associateName + ", leaves=" + leaves
				+ ", debitAmt=" + debitAmt + ", serialNum=" + serialNum + "]";
	}

}
