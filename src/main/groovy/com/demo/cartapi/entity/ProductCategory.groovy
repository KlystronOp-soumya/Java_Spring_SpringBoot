package com.demo.cartapi.entity

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.OneToOne

@Entity
class ProductCategory implements Serializable {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	Long id
	
	String categoryId
	String category
	
	@OneToOne(mappedBy="productCategory")
	ProductInfo productInfo
	
}
