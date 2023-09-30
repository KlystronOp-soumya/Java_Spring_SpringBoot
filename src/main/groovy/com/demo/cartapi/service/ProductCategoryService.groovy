package com.demo.cartapi.service

import javax.cache.annotation.CachePut
import javax.cache.annotation.CacheValue
import javax.persistence.Cacheable

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional

import com.demo.cartapi.entity.ProductCategory
import com.demo.cartapi.repo.ProductCategoryRepo

import net.bytebuddy.asm.Advice.This

@Service(value="productCategoryService")
class ProductCategoryService {
	
	/* TODO All these apis are only exposed to the admins
	 * 
	 * Get Product category list
	 * Put Product category
	 * Modify Product category
	 * Add Single Product Category
	 * Add multiple product category - use excel and poi
	 * Delete a product category
	 * 
	 * 
	 * */
	private ProductCategoryRepo productCategoryRepo
	
	ProductCategoryService(final ProductCategoryRepo productCategoryRepo)
	{
		this.productCategoryRepo = productCategoryRepo 
	}
	
	@Transactional(readOnly=true , propagation = Propagation.REQUIRED)
	//@org.springframework.cache.annotation.Cacheable(key="categoryId#" , condition="") 
	public List<ProductCategory> getAllProductCategoryList()
	{
		List<ProductCategory> productCategoryList = null ;
		try {
			
			productCategoryList = this.productCategoryRepo.findAll() ;
			
			if(productCategoryList.isEmpty() || null == productCategoryList)
				throw new NullPointerException("Empty product category")
			
		} catch (Exception e) {
			e.printStackTrace()
		}
	}
}
