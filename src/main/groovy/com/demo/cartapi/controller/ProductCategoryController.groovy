package com.demo.cartapi.controller

import java.security.PrivateKey

import org.apache.logging.log4j.LogManager
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContext
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController

import com.demo.cartapi.controller.exceptions.CartException
import com.demo.cartapi.controller.exceptions.ProductCategoryNotAddExcp
import com.demo.cartapi.controller.exceptions.ProductCategoryNotFoundException
import com.demo.cartapi.entity.ProductCategory
import com.demo.cartapi.service.ProductCategoryService
import com.demo.cartapi.service.util.LoggerUtil

import groovy.transform.TypeChecked
import net.bytebuddy.asm.Advice.Return
import net.bytebuddy.asm.Advice.This


/*
 *  @author Soumyadeep Paul
 *  
 *  The product and category management can only have admin privilages
 * 
 * */

@TypeChecked
@RestController(value="productCategoryController")
@RequestMapping(path= "/admin/manage/product") //TODO need to create a base admin controller
class ProductCategoryController {
	 private static final org.apache.logging.log4j.Logger LOGGER = LogManager.getLogger(ProductCategoryController.class)
	private ProductCategoryService productCategoryService
	
	ProductCategoryController(ProductCategoryService productCategoryService)
	{
		this.productCategoryService = productCategoryService
	}
	
	@GetMapping(path="/getProductCategories" , produces= MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize(value="hasRole('ADMIN')")
	public @ResponseBody ResponseEntity<ProductCategory> getAllProductCategory()
	{
		LOGGER.info("Request received for::getProductCategories")
		
		ResponseEntity<ProductCategory> responseEn = null ;
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
				throw new ProductCategoryNotFoundException("Empty Product Category")
			}
			
		
		return responseEn
	}
	
	/*
	 * Method to add category into the database
	 * 
	 * Only available to the admin
	 * */
	
	@SuppressWarnings("deprecation")
	@PostMapping(path="/addProductCategories" , 
		consumes=[MediaType.APPLICATION_JSON_UTF8_VALUE , MediaType.APPLICATION_XML_VALUE] , 
		produces=[MediaType.APPLICATION_JSON_UTF8_VALUE , MediaType.APPLICATION_XML_VALUE])
	public @ResponseBody ResponseEntity<List<ProductCategory>> addProductCategories(@RequestBody(required=true) 
																					List<ProductCategory> productCategoryList )
	{
		LoggerUtil.info("Request received for::addProductCategories method called:# " + productCategoryList.size())
		ResponseEntity<List<ProductCategory>> responseEn = null
		List<ProductCategory> categoryList = null
		LoggerUtil.info("Service method is called")
		try {
			categoryList = this.productCategoryService.addAllProductCategoriesList(productCategoryList)
		} catch (Exception e) {
			throw new  ProductCategoryNotAddExcp("Product categories could not be added")
		}

		return responseEn
	}
	
	@SuppressWarnings("deprecation")
	@DeleteMapping(path="/deleteProductCategory" , 
		consumes=[MediaType.APPLICATION_JSON_UTF8_VALUE , MediaType.APPLICATION_XML_VALUE] , 
		produces=[MediaType.APPLICATION_JSON_UTF8_VALUE , MediaType.APPLICATION_XML_VALUE])
	public @ResponseBody ResponseEntity<List<ProductCategory>> deleteProductCategory(@RequestBody(required=true) 
																					List<ProductCategory> productCategoryList )
	{
		
		return null;
	}
}
