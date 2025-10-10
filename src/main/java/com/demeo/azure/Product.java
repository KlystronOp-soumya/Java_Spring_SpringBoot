package com.demeo.azure;

public record Product(String prodId , String prodName , String prodCategory , double prodValue , double discountPercent) {

    public double getFinalPrice(){
        double cost = this.prodValue - (this.prodValue * this.discountPercent) ;
        return cost ;
    }

}
