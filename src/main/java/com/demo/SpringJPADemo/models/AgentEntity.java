package com.demo.SpringJPADemo.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "Agents")
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

	@Embedded
	private AddressEntity address;

	@Column(name = "EMAIL_ID")
	private String emailId;
	@Column(name = "CONTACT_NUM")
	private String contactNum;
}
