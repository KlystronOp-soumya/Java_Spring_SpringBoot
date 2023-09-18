package com.demo.cartapi.entity

import static javax.persistence.GenerationType.IDENTITY

import javax.persistence.CascadeType
import javax.persistence.EmbeddedId
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.JoinTable
import javax.persistence.ManyToOne
import javax.persistence.OneToMany
import javax.persistence.OneToOne
import javax.persistence.Table
import javax.persistence.Transient
import javax.persistence.criteria.Fetch

import com.fasterxml.jackson.annotation.JsonIgnore

@Entity
@Table(name="CUSTOMER_INFO")
class ConsumersInfo implements Serializable {

	@EmbeddedId
	ConsumerInfoPK consumerInfoPK
	
	 String consumerUserId ;
	 String consumerFName ;
	 String consumerLName ;
	 String consumerPhone
	 
	 @JsonIgnore(value=true)
	 @Transient
	 String consumerPwd ; //need not to show the password
	 
	 @OneToMany(cascade=CascadeType.ALL , fetch=FetchType.EAGER )
	 List<ConsumerAdd> consumerAddress; //should have a many to one relationship , a customer can have multiple addresses
	 
	 String phoneNumber ;
	 
	 @OneToOne(cascade= CascadeType.ALL , fetch=FetchType.EAGER , targetEntity = Users.class)
	 Users users
	 
	 @OneToOne(mappedBy="consumersInfo")
	 CartProduct cartProduct //cart product saved by the consumer
	
	
}
