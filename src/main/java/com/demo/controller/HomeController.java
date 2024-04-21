package com.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {
	
	// The Logger
	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
		 
	@RequestMapping(path = "/login" , name = "index" , method = RequestMethod.GET)
	public String index()
	{
		return "login" ; 
	}
	
	@GetMapping(path = "/showToken")
	public void main(final OAuth2AuthenticationToken oAuth2AuthenticationToken)
	{
		LOGGER.info(String.valueOf(oAuth2AuthenticationToken.getPrincipal())) ;
	}
	
}
