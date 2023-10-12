package com.demo.cartapi.service

import javax.cache.annotation.CachePut
import javax.cache.annotation.CacheValue
import javax.persistence.Cacheable

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional

import com.demo.cartapi.entity.ProductCategory
import com.demo.cartapi.repo.ProductCategoryRepo
import com.demo.cartapi.service.util.LoggerUtil

import net.bytebuddy.asm.Advice.This

@Service(value="productCategoryService")
class ProductCategoryService {
	
	/* TODO 
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
	//@org.springframework.cache.annotation.Cacheable(value="productCategoryCache" ,condition="#result!=null") 
	public List<ProductCategory> getAllProductCategoryList()
	{
		LoggerUtil.info("ProductCateSrvc:: etAllProductCategoryList ")
		List<ProductCategory> productCategoryList = null ;
		try {
			
			productCategoryList = this.productCategoryRepo.findAll() ;
			
			if(productCategoryList.isEmpty() || null == productCategoryList)
				throw new NullPointerException("Empty product category")
			
		} catch (Exception e) {
			e.printStackTrace()
		}
		LoggerUtil.info("ProductCateSrvc:: fetched all product categories")
		return productCategoryList
	}
	
	@Transactional(readOnly=false , propagation = Propagation.REQUIRED)
	public List<ProductCategory>  addAllProductCategoriesList(final List<ProductCategory> productCategoryList )
	{
		LoggerUtil.info("ProductCateSrvc:: addAllProductCategoriesList #" + productCategoryList.size())
		List<ProductCategory> categoryList = null
		try {
			categoryList = productCategoryRepo.saveAndFlush(productCategoryList)
			if(null == categoryList || categoryList.empty())
				throw new RuntimeException("Could not added the categories")
			
		} catch (Exception e) {
			e.printStackTrace()
		}
		LoggerUtil.info("ProductCateSrvc:: products were added successfully")
		return categoryList
	}
}
