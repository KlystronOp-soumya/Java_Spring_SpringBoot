package com.demo.cartapi.entity

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.OneToOne
import javax.persistence.Table

@Entity
@Table(name="PRODUCT_CATEGORY")
class ProductCategory implements Serializable {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	Long id
	
	@Column(name="CATEGORY_ID")
	String categoryId
	@Column(name="CATEGORY")
	String category
	
	@OneToOne(mappedBy="productCategory")
	ProductInfo productInfo
	
}
