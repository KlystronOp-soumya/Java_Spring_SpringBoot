package com.demo.cartapp.service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demo.cartapp.entity.Cart;
import com.demo.cartapp.entity.Product;
import com.demo.cartapp.repo.ProductRepo;
import com.demo.cartapp.repo.UserRepo;

import ch.qos.logback.classic.Logger;

@Service(value = "cartAppService")
public class CartAppService {
	/*
	private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(CartAppService.class) ;
	
	//inject the dao and jdbcTemplate
	private UserRepo userRepo ;
	private ProductRepo productRepo ;
	@PersistenceContext
	private EntityManager entityManager ;
	private JdbcTemplate jdbcTemplate ;
	
	CartAppService(final UserRepo userRepo , final JdbcTemplate jdbcTemplate , final ProductRepo productRepo)
	{
		this.userRepo = userRepo ;
		this.jdbcTemplate = jdbcTemplate ;
		this.productRepo = productRepo ;
	}
	
	@Transactional
	public void saveProduct(final Product product) {
		LOGGER.info("Inside CartService: saveProduct");
		try {
			
			//this.productRepo.save(product) ;
			this.entityManager.merge(product);
			LOGGER.info("Product: " + product.getProductId() +" was saved successfully");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public Cart getItemsInCart(String userName) {
		Cart cart = null;
		String query = "SELECT * FROM CART WHERE USER_ID = (SELECT USER_ID FROM USER WHERE USER_NAME='" + userName + "') " ;
		try {
			
			cart = this.jdbcTemplate.queryForObject(query, Cart.class) ;
			
			if(cart!=null)
			{
				
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	} */
}
