package com.demo.cartapi.entity

import groovy.transform.TypeChecked

import javax.annotation.Generated
import javax.persistence.CascadeType
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.JoinTable
import javax.persistence.OneToMany
import javax.persistence.Table


@Entity
@Table(name="USERS")
class Users  implements Serializable{
		
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ID")
	int user_id
	@Column(name="USER_ID")
	String userId
	@Column(name="USER_PWD")
	String userPwd
	
	@OneToMany(cascade = CascadeType.DETACH , fetch = FetchType.EAGER)
	@JoinTable(name = "user_role" , joinColumns = @JoinColumn(name="user_id") , inverseJoinColumns = @JoinColumn(name="role_id"))
	Set<UserRoles> userRoles;
	
	
	
}
