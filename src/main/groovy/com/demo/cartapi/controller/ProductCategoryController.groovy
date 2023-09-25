package com.demo.cartapi.controller

import java.security.PrivateKey

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

import com.demo.cartapi.service.ProductCategoryService

/*
 *  @author Soumyadeep Paul
 *  
 *  The product and category management can only have admin privilages
 * 
 * */

@RestController(value="productCategoryController")
@RequestMapping(path= "/admin/manage")
class ProductCategoryController {
	
	private ProductCategoryService productCategoryService
	
	ProductCategoryController(ProductCategoryService productCategoryService)
	{
		this.productCategoryService = productCategoryService
	}
	
}
