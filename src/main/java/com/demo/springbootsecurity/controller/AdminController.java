package com.demo.springbootsecurity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.springbootsecurity.model.User;
import com.demo.springbootsecurity.repository.UserREpository;

@RestController
@RequestMapping(path = "/secure/rest")
public class AdminController {
	
	@Autowired
	private UserREpository userREpository ;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder ;
	
	@PreAuthorize("hasAnyRole(\"ADMIN\")")
	@PostMapping("/admin/add")
	public String addUserByAdmin(@RequestBody User user)
	{
		String pwdString= user.getPassword() ;
		String encryptPwdString= this.passwordEncoder.encode(pwdString) ;
		user.setPassword(encryptPwdString) ;
		this.userREpository.save(user) ;
		return "User added successfully" ;
	}
}
