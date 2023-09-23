package com.demo.cartapi.entity

import javax.persistence.CascadeType
import javax.persistence.EmbeddedId
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.ForeignKey
import javax.persistence.JoinColumn
import javax.persistence.OneToMany
import javax.persistence.OneToOne

import groovy.transform.NamedDelegate

@Entity
class SellerInfo implements Serializable{
	
	@EmbeddedId
	SellerPK sellerPK ;
	
	String sellerName ;
	String sellerAddress ;
	String sellerType ;
	
	//with the Users
	@OneToOne(cascade=CascadeType.ALL , fetch=FetchType.EAGER , targetEntity=Users.class)
	@JoinColumn(name="userId" , referencedColumnName="userId" , foreignKey= @ForeignKey(name="FK_SELLER_USER"))
	Users user ;
	
	@OneToMany(mappedBy="sellerInfo")
	List<ProductInfo> ProductInfoList;
	
}
