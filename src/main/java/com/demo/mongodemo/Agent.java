package com.demo.mongodemo;

import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Agent {

	private String agtId;
	private String agtNo;
	private String finOwner;
	private String producerId;
	private Contract contract;

	// an inner class
	class Contract {

		private Date contractDate;
		private Date signOnDate;

		public Date getContractDate() {
			return contractDate;
		}

		public void setContractDate(Date contractDate) {
			this.contractDate = contractDate;
		}

		public Date getSignOnDate() {
			return signOnDate;
		}

		public void setSignOnDate(Date signOnDate) {
			this.signOnDate = signOnDate;
		}

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

	public String getFinOwner() {
		return finOwner;
	}

	public void setFinOwner(String finOwner) {
		this.finOwner = finOwner;
	}

	public String getProducerId() {
		return producerId;
	}

	public void setProducerId(String producerId) {
		this.producerId = producerId;
	}

	public Contract getContract() {
		return contract;
	}

	public void setContract(Contract contract) {
		this.contract = contract;
	}

}
