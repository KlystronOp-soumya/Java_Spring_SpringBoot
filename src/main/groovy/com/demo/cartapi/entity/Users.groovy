package com.demo.cartapi.entity

import groovy.transform.TypeChecked

import javax.annotation.Generated
import javax.persistence.CascadeType
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.ForeignKey
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.JoinTable
import javax.persistence.ManyToMany
import javax.persistence.OneToMany
import javax.persistence.OneToOne
import javax.persistence.Table


@Entity
@Table(name="USERS")
class Users  implements Serializable{
		
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	//@Column(name="ID")
	int id
	@Column(name="USER_ID")
	String userId
	@Column(name="USER_PWD")
	String userPwd
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "user_role" , joinColumns = @JoinColumn(name="USER_ID" ,  referencedColumnName = "USER_ID"  , 
		foreignKey = @ForeignKey(name="Fk_UserRole_User")) , 
		inverseJoinColumns = @JoinColumn(name="ROLE_ID" , referencedColumnName = "ROLE_ID" , foreignKey= @ForeignKey(name= "FK_UserRole_Role") ))
	Set<UserRoles> userRoles
	
	@OneToOne(mappedBy="user")
	SellerInfo seller
	
	@OneToOne(mappedBy="users")
	ConsumersInfo consumer ;
	
	@OneToOne(mappedBy="cartUser")
	Cart cart
	
}
