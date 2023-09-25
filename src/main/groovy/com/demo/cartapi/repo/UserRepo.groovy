package com.demo.cartapi.repo

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

import com.demo.cartapi.entity.Users

interface UserRepo extends JpaRepository<Users , Integer>{
	
	
}
