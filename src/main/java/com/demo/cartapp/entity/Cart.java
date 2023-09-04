package com.demo.cartapp.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.ConstraintMode;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Cart {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int cartId ;
	private Double totalAmount ;
	
	@OneToOne(cascade =CascadeType.REMOVE)
	@JoinColumn(name = "userId" , referencedColumnName = "user_id" , foreignKey = @ForeignKey(name="Fk_Cart_User"))
	private User user ;
	
	@OneToMany(mappedBy = "cart" , cascade = CascadeType.REMOVE , fetch = FetchType.EAGER)
	private List <CartProduct> cartProduct;
	
	public Integer getCartId() {
		return cartId;
	}
	public void setCartId(Integer cartId) {
		this.cartId = cartId;
	}
	public Double getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public List<CartProduct> getCartProduct() {
		return cartProduct;
	}
	public void setCartProduct(List<CartProduct> cartProduct) {
		this.cartProduct = cartProduct;
	}
	
	
}
