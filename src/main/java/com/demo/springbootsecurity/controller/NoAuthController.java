package com.demo.springbootsecurity.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//for this url the security will not be applied
@RestController
@RequestMapping(path = {"/noAuth/rest/"})
public class NoAuthController {
	
	@GetMapping(path = "noAuthHome")
	public String getNoAuthMessage()
	{
		return "This is no auth message" ;
	}

}
