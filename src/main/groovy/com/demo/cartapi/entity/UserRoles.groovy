package com.demo.cartapi.entity

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table


@Entity
@Table(name="USER_ROLES")
class UserRoles implements Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	Long role_id
	@Column(name="USER_ROLE")
	String userRole
	
	
}
