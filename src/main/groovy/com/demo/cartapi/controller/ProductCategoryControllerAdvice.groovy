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
import com.demo.cartapi.entity.ErrorResponseEntity
import com.demo.cartapi.service.util.DateUtil

import java.text.SimpleDateFormat

import javax.jws.WebResult



@RestControllerAdvice(basePackages="com.demo.cartapi.controller")
class ProductCategoryControllerAdvice {
	private static final org.apache.logging.log4j.Logger LOGGER = LogManager.getLogger(ProductCategoryControllerAdvice.class)
	//@ResponseStatus(code = HttpStatus.NOT_FOUND , reason = "Required record/records not found")
	@ExceptionHandler(value = CartException.class)
	public ResponseEntity<Map<String,ErrorResponseEntity>> handleProductCategoryRecordNFException(CartException ex , WebRequest req)
	{
		LOGGER.info("handle prodcut category not found exception")
		Map<String , ErrorResponseEntity> responseMap = new HashMap() 
			
		ErrorResponseEntity errorResponseEntity = new ErrorResponseEntity()
		errorResponseEntity.setErrorMessage(ex.getMessage())
		errorResponseEntity.setErrorType("runtimeException")
		errorResponseEntity.setHasErrors(true)
		errorResponseEntity.setStatus("failed")
		errorResponseEntity.setHttpStatusCode(HttpStatus.NOT_FOUND.value.toString())
		errorResponseEntity.setHttpStatusCodeDesc(HttpStatus.NOT_FOUND.reasonPhrase)
		errorResponseEntity.setDate(DateUtil.getCurrentDate())
		errorResponseEntity.setTimeStamp(DateUtil.getCurrentTime())
		LOGGER.info("response entity built")
		responseMap.putAt("data", errorResponseEntity)	
		return new ResponseEntity<Map<String,ErrorResponseEntity>>(responseMap , HttpStatus.NOT_FOUND) ;
	}
}
