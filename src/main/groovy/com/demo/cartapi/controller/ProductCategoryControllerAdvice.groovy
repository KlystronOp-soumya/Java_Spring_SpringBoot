package com.demo.cartapi.controller

import org.apache.logging.log4j.LogManager
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.context.request.WebRequest

import com.demo.cartapi.controller.exceptions.CartException
import com.demo.cartapi.controller.exceptions.PrdCatgryNotUpdatedExcp
import com.demo.cartapi.controller.exceptions.ProductCategoryNotAddExcp
import com.demo.cartapi.controller.exceptions.ProductCategoryNotFoundException
import com.demo.cartapi.entity.ErrorResponseEntity
import com.demo.cartapi.service.util.DateUtil
import com.demo.cartapi.service.util.LoggerUtil

import java.text.SimpleDateFormat





@RestControllerAdvice(basePackages="com.demo.cartapi.controller")
class ProductCategoryControllerAdvice {
	//private static final org.apache.logging.log4j.Logger LOGGER = LogManager.getLogger(ProductCategoryControllerAdvice.class)
	/*
	 * Do not need to keep Response Status if a ResponseEntity is used
	 * */
	//@ResponseStatus(code = HttpStatus.NOT_FOUND , reason = "Required record/records not found")
	@ExceptionHandler(value = ProductCategoryNotFoundException.class )
	public ResponseEntity<Map<String,ErrorResponseEntity>> handleProductCategoryRecordNFException(CartException ex , WebRequest req)
	{
		LoggerUtil.info("handle prodcut category not found exception")
		Map<String , ErrorResponseEntity> responseMap = new HashMap() 
			
		ErrorResponseEntity errorResponseEntity = new ErrorResponseEntity()
		errorResponseEntity.setErrorMessage(ex.getMessage())
		errorResponseEntity.setErrorType("nullException")
		errorResponseEntity.setHasErrors(true)
		errorResponseEntity.setStatus("failed")
		errorResponseEntity.setHttpStatusCode(HttpStatus.NOT_FOUND.value.toString())
		errorResponseEntity.setHttpStatusCodeDesc(HttpStatus.NOT_FOUND.reasonPhrase)
		errorResponseEntity.setDate(DateUtil.getCurrentDate())
		errorResponseEntity.setTimeStamp(DateUtil.getCurrentTime())
		LoggerUtil.info("response entity built")
		responseMap.put("data", errorResponseEntity)	
		return new ResponseEntity<Map<String,ErrorResponseEntity>>(responseMap , HttpStatus.NOT_FOUND) ;
	}
	
	@ExceptionHandler(value = ProductCategoryNotAddExcp.class )
	public ResponseEntity<Map<String,ErrorResponseEntity>> handleProductCategoryNtAddExceprion(CartException ex , WebRequest req)
	{
		LoggerUtil.info("handle prodcut category not added exception")
		Map<String , ErrorResponseEntity> responseMap = new HashMap()
			
		ErrorResponseEntity errorResponseEntity = new ErrorResponseEntity()
		errorResponseEntity.setErrorMessage(ex.getMessage())
		errorResponseEntity.setErrorType("runtimeException")
		errorResponseEntity.setHasErrors(true)
		errorResponseEntity.setStatus("failed")
		errorResponseEntity.setHttpStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value.toString())
		errorResponseEntity.setHttpStatusCodeDesc(HttpStatus.INTERNAL_SERVER_ERROR.reasonPhrase)
		errorResponseEntity.setDate(DateUtil.getCurrentDate())
		errorResponseEntity.setTimeStamp(DateUtil.getCurrentTime())
		LoggerUtil.info("response entity built")
		responseMap.put("data", errorResponseEntity)
		return new ResponseEntity<Map<String,ErrorResponseEntity>>(responseMap , HttpStatus.NOT_FOUND) ;
	}
	
	@ExceptionHandler(value = PrdCatgryNotUpdatedExcp.class )
	public ResponseEntity<Map<String,ErrorResponseEntity>> handleProductCategoryNtUpdtExceprion(CartException ex , WebRequest req)
	{
		LoggerUtil.info("handle prodcut category not added exception")
		Map<String , ErrorResponseEntity> responseMap = new HashMap()
			
		ErrorResponseEntity errorResponseEntity = new ErrorResponseEntity()
		errorResponseEntity.setErrorMessage(ex.getMessage())
		errorResponseEntity.setErrorType("runtimeException")
		errorResponseEntity.setHasErrors(true)
		errorResponseEntity.setStatus("failed")
		errorResponseEntity.setHttpStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value.toString())
		errorResponseEntity.setHttpStatusCodeDesc(HttpStatus.INTERNAL_SERVER_ERROR.reasonPhrase)
		errorResponseEntity.setDate(DateUtil.getCurrentDate())
		errorResponseEntity.setTimeStamp(DateUtil.getCurrentTime())
		LoggerUtil.info("response entity built")
		responseMap.put("data", errorResponseEntity)
		return new ResponseEntity<Map<String,ErrorResponseEntity>>(responseMap , HttpStatus.NOT_FOUND) ;
	}
	
}
