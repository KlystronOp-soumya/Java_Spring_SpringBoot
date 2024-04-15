package com.demo.springbootsecurity.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.springbootsecurity.entities.Users;

public interface CustomUserRepository extends JpaRepository<Users, Long> {

	Users findByUserName(String username);


}
