package com.demo.cartapi.entity

import javax.persistence.Embeddable

//composite key
@Embeddable
class ConsumerInfoPK {
	
	String conUserName
	String email
}
