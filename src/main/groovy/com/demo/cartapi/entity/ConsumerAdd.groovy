package com.demo.cartapi.entity

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.ManyToOne
import javax.persistence.OneToMany

import groovy.transform.TypeChecked

@TypeChecked
class ConsumerAdd implements Serializable{
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	Long id
	
	String custAddId
	
	String country;
	String state ;
	String pincode;
	String address_home ;
	String address_office ;
	String isDefaultAddress ;
	
}
