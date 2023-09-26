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
	@Override
	public int hashCode() {
		return Objects.hash(GSTIN, IFSC, phone, sellerEmail, sellerId);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SellerPK other = (SellerPK) obj;
		return (Objects.equals(GSTIN, other.GSTIN) && Objects.equals(IFSC, other.IFSC)
				&& Objects.equals(phone, other.phone) && Objects.equals(sellerEmail, other.sellerEmail)
				&& Objects.equals(sellerId, other.sellerId) );
	}
	
	
}
