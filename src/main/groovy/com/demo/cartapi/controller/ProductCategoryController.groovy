package com.demo.cartapi.controller

import java.security.PrivateKey

import org.apache.logging.log4j.LogManager
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController

import com.demo.cartapi.controller.exceptions.CartException
import com.demo.cartapi.entity.ProductCategory
import com.demo.cartapi.service.ProductCategoryService

import net.bytebuddy.asm.Advice.This

/*
 *  @author Soumyadeep Paul
 *  
 *  The product and category management can only have admin privilages
 * 
 * */

@RestController(value="productCategoryController")
@RequestMapping(path= "/admin/manage/product") //TODO need to create a base admin controller
class ProductCategoryController {
	 private static final org.apache.logging.log4j.Logger LOGGER = LogManager.getLogger(ProductCategoryController.class)
	private ProductCategoryService productCategoryService
	
	ProductCategoryController(ProductCategoryService productCategoryService)
	{
		this.productCategoryService = productCategoryService
	}
	
	@GetMapping(path="/getProductCategories")
	public@ResponseBody ResponseEntity getAllProductCategory()
	{
		LOGGER.info("Request received for :: getProductCategories")
		ResponseEntity responseEn = null ;
		List<ProductCategory> allProductCategory ;
		
			LOGGER.info("Service call is made")
			allProductCategory = this.productCategoryService.getAllProductCategoryList() ;
			if(null!=allProductCategory && !allProductCategory.isEmpty() )
			{
				LOGGER.info("Non Empty category list received : response entity built")
				responseEn = new ResponseEntity(allProductCategory, HttpStatus.OK);
			}
			else if( null == allProductCategory || allProductCategory.isEmpty() )
			{
				LOGGER.info("Non Empty category list NOT received : response entity built")
				//responseEn = new ResponseEntity(allProductCategory, HttpStatus.NO_CONTENT);
				throw new CartException()
			}
			
		
		return responseEn
	}
	
}
