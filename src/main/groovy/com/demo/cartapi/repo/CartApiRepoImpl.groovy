package com.demo.cartapi.repo

import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Repository

import com.demo.cartapi.entity.Users

import net.bytebuddy.asm.Advice.This

@Repository
class CartApiRepoImpl implements CartApiRepo{
	
	private JdbcTemplate jdbcTemplate ;
	
	CartApiRepoImpl(JdbcTemplate jdbcTemplate)
	{
		
		this.jdbcTemplate = jdbcTemplate 
	}
	
	@Override
	public List<Users> getUsersWithRolesList() {
		def sql ="SELECT U.USER_ID , R.ROLE_ID FROM USERS U , ROLES R , USER_ROLE UR "
		+ " WHERE UR.USER_ID = U.USER_ID AND UR.ROLE_ID = R.ROLE_ID "
		 this.jdbcTemplate.queryForList(sql) 
		
	}
}
