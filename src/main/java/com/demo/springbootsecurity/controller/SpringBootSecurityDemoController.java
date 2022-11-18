package com.demo.springbootsecurity.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = {"/rest"})
public class SpringBootSecurityDemoController {
	
	/*
	 * Simple method to handle a get request 
	 * 
	 *@param void
	 *
	 *@return ResponseEntity String HttpStatus
	 * 
	 * */
	@GetMapping(path = {"/initHome"})
	@PreAuthorize("ADMIN")
	public ResponseEntity<String> getWelcomeMessage()
	{
		return new ResponseEntity<String>("Welcome To Spring Boot Security", HttpStatus.OK) ;
	}
}
