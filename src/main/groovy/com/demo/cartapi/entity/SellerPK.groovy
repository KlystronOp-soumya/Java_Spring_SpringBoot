package com.demo.cartapi.entity

import javax.persistence.Column
import javax.persistence.Embeddable

/*
 * @author Soumyadeep Paul
 * 
 * Composite Primary Key Class for a seller
 * 
 * */

@Embeddable
class SellerPK implements Serializable{
	
	@Column(name="SELLER_ID")
	String sellerId
	@Column(name="SELLER_EMAIL")
	String sellerEmail ;
	@Column(name="GSTIN")
	String GSTIN ;
	@Column(name="IFSC")
	String IFSC ;
	@Column(name="PHONE")
	String phone ;
}
