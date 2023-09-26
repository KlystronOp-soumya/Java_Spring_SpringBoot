package com.demo.cartapi.service

import com.demo.cartapi.entity.Users
import com.demo.cartapi.repo.UserRepo

import org.springframework.stereotype.Service

@Service(value="userRoleService")
class UserRolesService {
	/*
	 * TODO All the apis will be exposed to admin
	 * 
	 * Get the use id and roles assigned
	 * 
	 * Modify the roles assigned
	 * 
	 * Update-Upsert user id for all types of user
	 * 
	 * replace password
	 * 
	 * */
	
	
	UserRepo userRepo 
	
	public List<Users> getUsersWithRoleList()
	{
		
	}
}
