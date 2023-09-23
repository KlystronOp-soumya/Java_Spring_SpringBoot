package com.demo.cartapi.entity

import javax.persistence.Entity
import javax.persistence.ForeignKey
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.JoinColumns
import javax.persistence.ManyToOne
import javax.persistence.OneToMany

import com.fasterxml.jackson.annotation.JsonBackReference

import groovy.transform.TypeChecked

@TypeChecked
@Entity
class ConsumerAdd implements Serializable{
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	Long id
	
	String consumerAddressId
	
	String country;
	String state ;
	String pincode;
	String address_home ;
	String address_office ;
	String isDefaultAddress="Y" ;
	
	@ManyToOne
	@JsonBackReference
	//@JoinColumn(name="consumerUserId" , columnDefinition="The cosumer id for referencing" , referencedColumnName="consumerUserId" , 
		//foreignKey=@ForeignKey(name = "FK_CONSUMERADD_CONSUMER_INFO"))
	@JoinColumns( foreignKey = @ForeignKey(name="FK_CONSUMERADD_CONSUMER_INFO"), 
		value = [
			@JoinColumn(name = "consumerUserId" ) , 
			@JoinColumn(name = "consumerEmail")
			])
	ConsumersInfo consumersInfo
	
	
	
}
