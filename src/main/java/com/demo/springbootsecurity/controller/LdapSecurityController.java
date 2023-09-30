package com.demo.springbootsecurity.controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class LdapSecurityController {

	@RequestMapping(path = "/index" , method = RequestMethod.GET)
	 public String getWelcomeMessage() {
		 
		 return "Hello World" ;
	}
}
