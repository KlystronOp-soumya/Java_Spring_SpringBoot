package com.demo.cartapi.entity

import javax.persistence.CascadeType
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.ForeignKey
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.OneToOne
import javax.persistence.criteria.Fetch

import groovy.transform.NamedDelegate

@Entity
class Cart implements Serializable{
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	Long cartId
	BigDecimal totalCartAmount
	Boolean isCouponeApplied
	BigDecimal discountProvided
	
	@OneToOne(cascade=CascadeType.ALL , fetch = FetchType.EAGER , targetEntity=CartProduct.class)
	@JoinColumn(name = "cartProductId" , referencedColumnName="cartProductId" , foreignKey = @ForeignKey(name="FK_CART_CARTPRODUCT"))
	CartProduct cartProduct
	
	@OneToOne(fetch=FetchType.EAGER , targetEntity=Users.class)
	@JoinColumn(name="userId" , referencedColumnName="userId" , foreignKey = @ForeignKey(name = "FK_CART_USERS"))
	Users cartUser
	
}
