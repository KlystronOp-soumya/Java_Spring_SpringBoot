package com.demo.cartapp.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class User implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "user_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int Id ;
	
	@JsonIgnore
	private String userName ;
	@JsonIgnore
	private String userPassword ;
	
	//Multiple user can have Multiple roles
	@ManyToMany(fetch = FetchType.EAGER , cascade = CascadeType.ALL)
	@JoinTable(name = "UserRole" , joinColumns  = @JoinColumn(name="user_id" , referencedColumnName = "user_id" , foreignKey = @ForeignKey(name="Fk_UserRole_User")) , inverseJoinColumns =  @JoinColumn(name="role_id" , referencedColumnName = "role_id" , foreignKey = @ForeignKey(name= "Fk_UserRole_Role")))
	@JsonIgnore
	@JsonManagedReference
	private Set<Role> roles ;

	@OneToMany(cascade = CascadeType.REMOVE , mappedBy = "seller")
	@JsonManagedReference
	private Set<Product> products ;
	
	@OneToOne(mappedBy = "user")
	private Cart cart;
	
	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	
	

}
