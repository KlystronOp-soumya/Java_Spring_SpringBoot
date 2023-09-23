package com.demo.cartapi.entity

import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.ForeignKey
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.JoinColumns
import javax.persistence.ManyToOne
import javax.persistence.OneToOne

import groovy.transform.TypeChecked

@TypeChecked
@Entity
class ProductInfo implements Serializable {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	Long productId 
	
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
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns([@JoinColumn(name = "sellerId", insertable = false, updatable = false),
		 @JoinColumn(name = "sellerEmail", insertable = false, updatable = false),
		 @JoinColumn(name = "GSTIN", insertable = false, updatable = false),
		 @JoinColumn(name = "IFSC", insertable = false, updatable = false),
		 @JoinColumn(name = "phone", insertable = false, updatable = false)])
	SellerInfo sellerInfo
	
	@ManyToOne
	@JoinColumn(name="cartProductId" , referencedColumnName="cartProductId" , 
		foreignKey = @ForeignKey(name = "FK_PRODUCTINFO_CARTPRODUCT"))
	CartProduct cartProduct
	
	
}
