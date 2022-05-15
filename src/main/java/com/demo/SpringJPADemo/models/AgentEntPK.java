package com.demo.SpringJPADemo.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class AgentEntPK implements Serializable {

	private static final long serialVersionUID = 1L;
	// For composite primary key
	@Column(name = "COLL_OFF")
	private String collOff;
	@Column(name = "AGT_ID")
	private String agentId;

	public AgentEntPK() {
		// TODO Auto-generated constructor stub
	}

	public AgentEntPK(String collOff, String agentId) {
		super();
		this.collOff = collOff;
		this.agentId = agentId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((agentId == null) ? 0 : agentId.hashCode());
		result = prime * result + ((collOff == null) ? 0 : collOff.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AgentEntPK other = (AgentEntPK) obj;
		if (agentId == null) {
			if (other.agentId != null)
				return false;
		} else if (!agentId.equals(other.agentId))
			return false;
		if (collOff == null) {
			if (other.collOff != null)
				return false;
		} else if (!collOff.equals(other.collOff))
			return false;
		return true;
	}

}
