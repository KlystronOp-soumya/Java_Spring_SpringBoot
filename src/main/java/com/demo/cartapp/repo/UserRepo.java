package com.demo.cartapp.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.cartapp.entity.User;

public interface UserRepo extends JpaRepository<User, Integer> {

	User findByUserName(String username);

}
