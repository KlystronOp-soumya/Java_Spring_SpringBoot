package com.demo.cartapi.entity

import javax.persistence.CascadeType
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.ForeignKey
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.JoinColumns
import javax.persistence.OneToMany
import javax.persistence.OneToOne
import javax.persistence.criteria.Fetch

@Entity
class CartProduct implements Serializable {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	Long cartProductId //pattern:datetime
	
	@OneToMany(fetch=FetchType.EAGER , mappedBy = "cartProduct" , targetEntity=ProductInfo.class)
	Set<ProductInfo> productInfoInCartProduct
	
	int cartedQuantity
	
	@OneToOne(fetch=FetchType.EAGER)
	//@JoinColumn(name="consumerUserId" , referencedColumnName="consumerUserId" , foreignKey = @ForeignKey(name = "FK_CARTPRODUCT_CONSUMERINFO"))
	@JoinColumns( foreignKey = @ForeignKey(name="FK_CARTPRODUCT_CONSUMERINFO"),
		value = [
			@JoinColumn(name = "consumerUserId" ) ,
			@JoinColumn(name = "consumerEmail")
			])
	ConsumersInfo consumersInfo
	
	@OneToOne(mappedBy="cartProduct")
	Cart cart
	
}
