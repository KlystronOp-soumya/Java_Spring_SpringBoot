package com.demo.cartapi.entity

import javax.persistence.CascadeType
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.OneToMany
import javax.persistence.OneToOne
import javax.persistence.criteria.Fetch

class CartProduct {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	Long cartProductId
	
	@OneToMany(fetch=FetchType.EAGER , mappedBy = "cartProduct" , targetEntity=ProductInfo.class)
	Set<ProductInfo> productInfoInCartProduct
	
	int cartedQuantity
	
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="consumerUserId")
	ConsumersInfo consumersInfo
}
