package com.demo.SpringJPADemo.models;

import java.io.Serializable;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Cacheable
@Table(name = "Agents")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class AgentEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private AgentEntPK agentEntPK; // primary key

	@Column(name = "FNAME")
	private String fname;
	@Column(name = "LNAME")
	private String lname;
	@Column(name = "AGT_NO")
	private String agentNo;

	/*
	 * @Embedded private AddressEntity address;
	 */

	@Column(name = "EMAIL_ID")
	private String emailId;
	@Column(name = "CONTACT_NUM")
	private String contactNum;

	public AgentEntPK getAgentEntPK() {
		return agentEntPK;
	}

	public void setAgentEntPK(AgentEntPK agentEntPK) {
		this.agentEntPK = agentEntPK;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getAgentNo() {
		return agentNo;
	}

	public void setAgentNo(String agentNo) {
		this.agentNo = agentNo;
	}

	/*
	 * public AddressEntity getAddress() { return address; }
	 * 
	 * public void setAddress(AddressEntity address) { this.address = address; }
	 */

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getContactNum() {
		return contactNum;
	}

	public void setContactNum(String contactNum) {
		this.contactNum = contactNum;
	}

	@Override
	public String toString() {
		return "AgentEntity [agentEntPK=" + agentEntPK + ", fname=" + fname + ", lname=" + lname + ", agentNo="
				+ agentNo + ", emailId=" + emailId + ", contactNum=" + contactNum + "]";
	}

}
