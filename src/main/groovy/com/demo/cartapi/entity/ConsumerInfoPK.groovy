package com.demo.cartapi.entity

import javax.persistence.Column
import javax.persistence.Embeddable

//composite key
@Embeddable
class ConsumerInfoPK implements Serializable{
	
	@Column(name="CONSUMER_USERID")
	String consumerUserId
	@Column(name="CONSUMER_EMAIL")
	String consumerEmail
	@Override
	public int hashCode() {
		return Objects.hash(consumerEmail, consumerUserId);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ConsumerInfoPK other = (ConsumerInfoPK) obj;
		return (Objects.equals(consumerEmail, other.consumerEmail)
				&& Objects.equals(consumerUserId, other.consumerUserId));
	}
	
	
}
