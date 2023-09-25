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
import javax.persistence.JoinColumns
import javax.persistence.OneToMany
import javax.persistence.OneToOne
import javax.persistence.Table
import javax.persistence.criteria.Fetch

@Entity
@Table(name="CART_PRODUCT")
class CartProduct implements Serializable {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="CART_PRODUCTID")
	Long cartProductId //pattern:datetime
	
	@OneToMany(fetch=FetchType.EAGER , mappedBy = "cartProduct" , targetEntity=ProductInfo.class)
	Set<ProductInfo> productInfoInCartProduct
	@Column(name="CARTED_QUANTITY")
	int cartedQuantity
	
	@OneToOne(fetch=FetchType.EAGER)
	//@JoinColumn(name="consumerUserId" , referencedColumnName="consumerUserId" , foreignKey = @ForeignKey(name = "FK_CARTPRODUCT_CONSUMERINFO"))
	@JoinColumns( foreignKey = @ForeignKey(name="FK_CARTPRODUCT_CONSUMERINFO"),
		value = [
			@JoinColumn(name = "CONSUMER_USERID" , referencedColumnName="CONSUMER_USERID") ,
			@JoinColumn(name = "CONSUMER_EMAIL" , referencedColumnName="CONSUMER_EMAIL")
			])
	ConsumersInfo consumersInfo
	
	@OneToOne(mappedBy="cartProduct")
	Cart cart
	
}
