package com.demo.cartapi.entity

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.ManyToMany
import javax.persistence.Table


@Entity
@Table(name="ROLES")
class UserRoles implements Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	Long roleId
	@Column(name="USER_ROLE")
	String userRole
	
	
	@ManyToMany(mappedBy="userRoles")
	Set<Users> users ;
	
	
	
}
