package com.demo.cartapi.entity

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.ForeignKey
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.JoinColumns
import javax.persistence.ManyToOne
import javax.persistence.OneToMany
import javax.persistence.Table

import com.fasterxml.jackson.annotation.JsonBackReference

import groovy.transform.TypeChecked

@TypeChecked
@Entity
@Table(name="CONSUMER_ADDRESS")
class ConsumerAdd implements Serializable{
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	Long id
	@Column(name="CONSUMER_ADDRESSID")
	String consumerAddressId
	@Column(name="COUNTRY")
	String country;
	@Column(name="STATE")
	String state ;
	@Column(name="PINCODE")
	String pincode;
	@Column(name="ADDRESS_HOME")
	String address_home ;
	@Column(name="ADDRESS_OFFICE")
	String address_office ;
	@Column(name="IS_DEFAULT_ADDRESS")
	String isDefaultAddress="Y" ;
	
	@ManyToOne
	@JsonBackReference
	//@JoinColumn(name="consumerUserId" , columnDefinition="The cosumer id for referencing" , referencedColumnName="consumerUserId" , 
		//foreignKey=@ForeignKey(name = "FK_CONSUMERADD_CONSUMER_INFO"))
	@JoinColumns( foreignKey = @ForeignKey(name="FK_CONSUMERADD_CONSUMER_INFO"), 
		value = [
			@JoinColumn(name = "CONSUMER_USERID" , referencedColumnName="CONSUMER_USERID") , 
			@JoinColumn(name = "CONSUMER_EMAIL" , referencedColumnName="CONSUMER_EMAIL")
			])
	ConsumersInfo consumersInfo
	
	
	
}
