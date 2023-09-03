package com.demo.cartapp.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.apache.el.parser.AstFalse;

@Entity
public class CartProduct {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int cpId;
	
	//Many product can be part of single cart
	@ManyToOne(fetch = FetchType.EAGER , cascade = {CascadeType.REMOVE})
	@JoinColumn(name = "cartId" , referencedColumnName = "cartId", nullable = true , insertable = true)
	private Cart cart ;
	
	@OneToOne(cascade = CascadeType.ALL , fetch = FetchType.EAGER , targetEntity = Product.class)
	@JoinColumn(name = "productId" , referencedColumnName = "productId")
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
