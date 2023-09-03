package com.demo.cartapp.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.cartapp.entity.Category;

public interface CategoryRepo extends JpaRepository<Category, Integer> {

}
