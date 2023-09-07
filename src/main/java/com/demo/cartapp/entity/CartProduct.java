package com.demo.cartapp.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class CartProduct {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int cpId;
	
	//Many product can be part of single cart
	//Had to remove Cascade.Remove as the design required something else
	//Even if the the CartProduct is deleted the entry should be in the Cart
	@ManyToOne(fetch = FetchType.EAGER ) //, cascade = {CascadeType.REMOVE}
	@JoinColumn(name = "cartId" , referencedColumnName = "cartId", nullable = false , insertable = true , foreignKey = @ForeignKey(name="Fk_CartProduct_Cart"))
	@JsonBackReference
	private Cart cart ;
	
	@OneToOne(cascade = CascadeType.ALL , fetch = FetchType.EAGER , targetEntity = Product.class)
	@JoinColumn(name = "productId" , referencedColumnName = "productId" , foreignKey = @ForeignKey(name="Fk_CartProduc_Product"))
	private Product product ;
	private int quantity = 1;
	public int getCpId() {
		return cpId;
	}
	public void setCpId(int cpId) {
		this.cpId = cpId;
	}
	public Cart getCart() {
		return cart;
	}
	public void setCart(Cart cart) {
		this.cart = cart;
	}
	
	
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	
	
	
}
