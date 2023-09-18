package com.demo.cartapi.entity

import javax.persistence.FetchType
import javax.persistence.ForeignKey
import javax.persistence.JoinColumn
import javax.persistence.OneToOne

class Cart implements Serializable{
	
	Long cartId
	BigDecimal totalCartAmount
	Boolean isCouponeApplied
	BigDecimal discountProvided
	
	@OneToOne
	CartProduct cartProduct
	
	@OneToOne(fetch=FetchType.EAGER , targetEntity=Users.class)
	@JoinColumn(name="id" , referencedColumnName="id" , foreignKey = @ForeignKey(name = "FK_CART_USERS"))
	Users cartUser
	
}
