package com.demo.cartapp.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.cartapp.entity.CartProduct;

public interface CartProductRepo extends JpaRepository<CartProduct, Integer> {

}
