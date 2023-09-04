package com.demo.cartapp.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.ConstraintMode;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Product implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int productId;
	
	private String productName ;
	private Double price ;
	
	//Single user can have multiple products
	@ManyToOne(fetch = FetchType.EAGER , cascade = CascadeType.ALL)
	@JoinColumn(name = "sellerId" , referencedColumnName = "user_id" , nullable = false , foreignKey = @ForeignKey(name="Fk_Product_User"))
	private User seller ;
	
	//Multiple product can belong to same category
	@ManyToOne(fetch = FetchType.EAGER ,cascade = CascadeType.ALL)
	@JoinColumn(name="categoryId" , referencedColumnName = "categoryId" , foreignKey = @ForeignKey(name="Fk_Product_Category"))
	private Category category ;

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public User getSeller() {
		return seller;
	}

	public void setSeller(User seller) {
		this.seller = seller;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
	
	

}
