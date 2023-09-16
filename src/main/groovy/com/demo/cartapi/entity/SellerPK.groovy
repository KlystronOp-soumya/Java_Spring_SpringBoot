package com.demo.cartapi.entity

import javax.persistence.Embeddable

/*
 * @author Soumyadeep Paul
 * 
 * Composite Primary Key Class for a seller
 * 
 * */

@Embeddable
class SellerPK implements Serializable{
	
	String sellerId
	String sellerEmail ;
	String GSTIN ;
	String IFSC ;
	String phone ;
}
