package com.demo.cartapi.entity

import static javax.persistence.GenerationType.IDENTITY

import javax.persistence.CascadeType
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.JoinTable
import javax.persistence.ManyToOne
import javax.persistence.OneToMany
import javax.persistence.Table
import javax.persistence.Transient

import com.fasterxml.jackson.annotation.JsonIgnore

@Entity
@Table(name="CUSTOMER_INFO")
class ConsumersInfo implements Serializable {

	@Id
	@GeneratedValue(strategy=IDENTITY)
	int id
	
	private String custId ; // a random generated Id using some logic
	 String customerFName ;
	 String customerLName ;
	 String customerUserId ;
	 
	 @JsonIgnore(value=true)
	 @Transient
	 String customerPwd ; //need not to show the password
	 
	 @OneToMany(cascade=CascadeType.ALL , fetch=FetchType.EAGER )
	 @JoinColumn(columnDefinition="customerAddFK" , insertable=true , nullable=false , referencedColumnName = "custAddId")
	 ConsumerAdd consumerAddress; //should have a many to one relationship , a customer can have multiple addresses
	 
	 String phoneNumber ;
	 
	
	
}
