package com.demo.cartapi.entity

import static javax.persistence.TemporalType.DATE

import javax.persistence.Column
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
import javax.persistence.Table
import javax.persistence.Temporal
import javax.persistence.TemporalType
import groovy.transform.TypeChecked

@TypeChecked
@Entity
@Table(name="PRODUCT_INFO")
class ProductInfo implements Serializable {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	Long productId 
	
	@Column(name="PRODUCT_NAME")
	String productName
	@Column(name="BRAND")
	String brand
	@Column(name="PRODUCT_WEIGHT" , nullable=true)
	String productWeight
	@Column(name="PRODUCT_MANFAC")
	String productManufacturer
	@Column(name="PRODUCT_EXPDATE")
	@Temporal(DATE)
	Date  productExpDate
	@Column(name="PRODUCT_MANFAC_DATE")
	String productManufacDate
	@Column(name="PRODUCT_DESCRIPTION")
	String productDescription
	@Column(name="INVENTORY_QUANTITY")
	int quantity
	@Column(name="PRICE")
	BigDecimal price
	
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="CATEGORY_ID" , referencedColumnName="CATEGORY_ID" , foreignKey = @ForeignKey(name = "FK_PRODUCTINFO_PRODUCTCATEGORY"))
	ProductCategory productCategory
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumns(foreignKey = @ForeignKey(name="FK_PRODUCT_INFO_SELLER_INFO"),
		value=[@JoinColumn(name = "sellerId", insertable = false, updatable = false , referencedColumnName="SELLER_ID"),
		 @JoinColumn(name = "sellerEmail", insertable = false, updatable = false , referencedColumnName="SELLER_EMAIL"),
		 @JoinColumn(name = "GSTIN", insertable = false, updatable = false , referencedColumnName="GSTIN"),
		 @JoinColumn(name = "IFSC", insertable = false, updatable = false , referencedColumnName="IFSC"),
		 @JoinColumn(name = "phone", insertable = false, updatable = false , referencedColumnName="PHONE")] )
	SellerInfo sellerInfo
	
	@ManyToOne
	@JoinColumn(name="CART_PRODUCTID" , referencedColumnName="CART_PRODUCTID" , 
		foreignKey = @ForeignKey(name = "FK_PRODUCT_INFO_CART_PRODUCT"))
	CartProduct cartProduct
	
	
}
