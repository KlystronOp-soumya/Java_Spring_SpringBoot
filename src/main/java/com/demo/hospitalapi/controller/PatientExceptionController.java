package com.demo.hospitalapi.controller;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.demo.hospitalapi.exceptions.PatientRecordNotFoundException;
import com.demo.hospitalapi.exceptions.UpdateNotPossibleException;

@RestControllerAdvice(basePackages = "com.demo.hospitalapi.controller")
public class PatientExceptionController {
	
	@ResponseStatus(code = HttpStatus.NOT_FOUND , reason = "Required record not found")
	@ExceptionHandler(PatientRecordNotFoundException.class)
	public ResponseEntity<Map<String,Object>> handlePatientRecordNFException()
	{
		Map<String, Object> responseMap = null ;
		responseMap = new HashMap<>() ;
		responseMap.put("status", "not fetched") ;
		responseMap.put("message", "Record is not present!!") ;
		responseMap.put("data", HttpStatus.NO_CONTENT) ;
		responseMap.put("found", HttpStatus.NOT_FOUND) ;
		return new ResponseEntity<Map<String,Object>>(responseMap , HttpStatus.BAD_REQUEST) ;
	}
	
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	@ExceptionHandler(UpdateNotPossibleException.class)
	public ResponseEntity<Map<String,String>> handlePatientRecordUPDTException()
	{
		Map<String, String> responseMap = null ;
		responseMap = new HashMap<>() ;
		responseMap.put("status", "not updated") ;
		responseMap.put("message", "Record is not present") ;
		return new ResponseEntity<Map<String,String>>(responseMap , HttpStatus.BAD_REQUEST) ;
	}
	
	@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR , reason = "Database issue")
	@ExceptionHandler(SQLException.class)
	public ResponseEntity<Map<String, String>> handleDataBaseExceptions()
	{
		return null ;
	}
	
	@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR , reason = "Runtime issue")
	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<Map<String, String>> handleGeneralExceptions()
	{
		return null ;
	}
}
