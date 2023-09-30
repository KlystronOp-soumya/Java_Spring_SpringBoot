package com.demo.cartapi.repo

import com.demo.cartapi.entity.ProductCategory
import org.springframework.data.jpa.repository.JpaRepository

interface ProductCategoryRepo extends JpaRepository<ProductCategory , Long> {
}
