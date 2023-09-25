package com.demo.cartapi.service

import com.demo.cartapi.entity.Users
import com.demo.cartapi.repo.UserRepo

import org.springframework.stereotype.Service

@Service(value="userRoleService")
class UserRolesService {
	
	UserRepo userRepo 
	
	public List<Users> getUsersWithRoleList()
	{
		
	}
}
