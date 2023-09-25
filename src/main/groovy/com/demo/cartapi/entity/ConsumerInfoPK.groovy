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
}
