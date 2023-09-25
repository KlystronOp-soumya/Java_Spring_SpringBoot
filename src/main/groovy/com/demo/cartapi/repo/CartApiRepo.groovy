package com.demo.cartapi.repo

import com.demo.cartapi.entity.Users

interface CartApiRepo {
	
	List<Users> getUsersWithRolesList()
}
