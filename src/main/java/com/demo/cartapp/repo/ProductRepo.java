package com.demo.cartapp.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.cartapp.entity.Product;

public interface ProductRepo extends JpaRepository<Product	, 	Integer> {

}
