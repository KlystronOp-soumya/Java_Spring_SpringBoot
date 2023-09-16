package com.demo.cartapi.entity

import javax.persistence.CascadeType
import javax.persistence.EmbeddedId
import javax.persistence.FetchType
import javax.persistence.ForeignKey
import javax.persistence.JoinColumn
import javax.persistence.OneToMany
import javax.persistence.OneToOne

import groovy.transform.NamedDelegate

class SellerInfo implements Serializable{
	
	@EmbeddedId
	SellerPK sellerPK ;
	
	String sellerName ;
	String sellerAddress ;
	String sellerType ;
	
	//with the Users
	@OneToOne(cascade=CascadeType.ALL , fetch=FetchType.EAGER , targetEntity=Users.class)
	@JoinColumn(name="userId" , referencedColumnName="user_id" , foreignKey= @ForeignKey(name="FK_SELLER_USER"))
	Users user ;
	
	@OneToMany(fetch=FetchType.EAGER , mappedBy="sellers" , targetEntity=ProductInfo.class)
	//TODO need to add mapping of seller into ProductInfo class
	List<ProductInfo> ProductInfoList;
	
}
