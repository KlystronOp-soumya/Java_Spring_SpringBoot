package com.demo.springbootsecurity.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.springbootsecurity.model.User;

public interface UserREpository extends JpaRepository<User, Integer> {

	User findByUsername();

	
}
