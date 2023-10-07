package com.demo.cartapi.controller.exceptions

class ProductCategoryNotFoundException extends CartException{
	
	ProductCategoryNotFoundException( def message)
	{
		super(message)
	}
	
}
