package com.demo.cartapi.entity

import javax.persistence.Embeddable

//composite key
@Embeddable
class ConsumerInfoPK implements Serializable{
	
	String consumerUserId
	String consumerEmail
}
