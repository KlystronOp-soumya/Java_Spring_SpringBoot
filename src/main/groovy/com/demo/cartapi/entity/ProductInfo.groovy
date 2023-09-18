package com.demo.cartapi.entity

import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.ForeignKey
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.OneToOne

import groovy.transform.TypeChecked

@TypeChecked
@Entity
class ProductInfo implements Serializable {
	
	String productName
	String brand
	String productWeight
	String productManufacturer
	Date  productExpDate
	String productManufacDate
	int quantity
	BigDecimal price
	
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="categoryId" , referencedColumnName="categoryId" , foreignKey = @ForeignKey(name = "FK_PRODUCTINFO_PRODUCTCATEGORY"))
	ProductCategory productCategory
	
	@ManyToOne
	@JoinColumn(name="sellerId" , referencedColumnName="sellerId" , 
		foreignKey= @ForeignKey(name = "FK_PRODUCTINFO_SELLERINFO") )
	SellerInfo sellerInfo
	
	@ManyToOne
	@JoinColumn(name="cartProductId" , referencedColumnName="cartProductId" , 
		foreignKey = @ForeignKey(name = "FK_PRODUCTINFO_CARTPRODUCT"))
	CartProduct cartProduct
	
	
}
