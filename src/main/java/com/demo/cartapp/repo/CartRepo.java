package com.demo.cartapp.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.cartapp.entity.Cart;

public interface CartRepo extends JpaRepository<Cart, Integer> {

}
