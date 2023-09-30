package com.demo.cartapi.controller

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.context.request.WebRequest

import com.demo.cartapi.controller.exceptions.CartException



@RestControllerAdvice(basePackages="com.demo.cartapi.controller")
class ProductCategoryControllerAdvice {
	@ResponseStatus(code = HttpStatus.NOT_FOUND , reason = "Required record/records not found")
	@ExceptionHandler(value = CartException.class)
	public ResponseEntity<Map<String,Object>> handleProductCategoryRecordNFException(CartException ex , WebRequest req)
	{
		Map<String, Object> responseMap = null ;
		responseMap = new HashMap<>() ;
		responseMap.put("status", "not fetched") ;
		responseMap.put("message", "Record is not present!!") ;
		responseMap.put("data", HttpStatus.NO_CONTENT) ;
		responseMap.put("found", HttpStatus.NOT_FOUND) ;
		return new ResponseEntity<Map<String,Object>>(responseMap , HttpStatus.INTERNAL_SERVER_ERROR) ;
	}
}
