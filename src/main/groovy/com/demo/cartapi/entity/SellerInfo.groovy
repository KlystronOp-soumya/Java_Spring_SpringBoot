package com.demo.cartapi.entity

import javax.persistence.CascadeType
import javax.persistence.Column
import javax.persistence.EmbeddedId
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.ForeignKey
import javax.persistence.JoinColumn
import javax.persistence.OneToMany
import javax.persistence.OneToOne
import javax.persistence.Table

import groovy.transform.NamedDelegate

@Entity
@Table(name="SELLER_INFO")
class SellerInfo implements Serializable{
	
	@EmbeddedId
	SellerPK sellerPK ;
	
	@Column(name="SELLER_NAME")
	String sellerName ;
	@Column(name="SELLER_ADDRESS")
	String sellerAddress ;
	@Column(name="SELLER_TYPE")
	String sellerType ;
	
	//with the Users
	@OneToOne(cascade=CascadeType.ALL , fetch=FetchType.EAGER , targetEntity=Users.class)
	@JoinColumn(name="USER_ID" , referencedColumnName="USER_ID" , foreignKey= @ForeignKey(name="FK_SELLER_USER"))
	Users user ;
	
	@OneToMany(mappedBy="sellerInfo")
	List<ProductInfo> ProductInfoList;
	
}
