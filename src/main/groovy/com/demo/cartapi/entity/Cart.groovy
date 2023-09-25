package com.demo.cartapi.entity

import javax.persistence.CascadeType
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.ForeignKey
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.OneToOne
import javax.persistence.Table
import javax.persistence.criteria.Fetch

import groovy.transform.NamedDelegate

@Entity
@Table(name="CART")
class Cart implements Serializable{
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name="CART_ID")
	Long cartId
	@Column(name="TOTAL_CART_AMOUNT")
	BigDecimal totalCartAmount
	@Column(name="IS_COUPON_APPLIED")
	Boolean isCouponeApplied
	@Column(name="DISCOUNT")
	BigDecimal discountProvided
	
	@OneToOne(cascade=CascadeType.ALL , fetch = FetchType.EAGER , targetEntity=CartProduct.class)
	@JoinColumn(name = "CART_PRODUCTID" , referencedColumnName="CART_PRODUCTID" , foreignKey = @ForeignKey(name="FK_CART_CARTPRODUCT"))
	CartProduct cartProduct
	
	@OneToOne(fetch=FetchType.EAGER , targetEntity=Users.class)
	@JoinColumn(name="USER_ID" , referencedColumnName="USER_ID" , foreignKey = @ForeignKey(name = "FK_CART_USERS"))
	Users cartUser
	
}
