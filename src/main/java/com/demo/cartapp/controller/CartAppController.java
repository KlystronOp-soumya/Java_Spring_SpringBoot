package com.demo.cartapp.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity.HeadersBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.header.Header;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.demo.cartapp.entity.Cart;
import com.demo.cartapp.entity.CartProduct;
import com.demo.cartapp.entity.Category;
import com.demo.cartapp.entity.Product;
import com.demo.cartapp.entity.User;
import com.demo.cartapp.service.CartAppService;

@RestController(value = "cartAppController")
@RequestMapping(path = "/cart")
public class CartAppController {
	/*
	private static final Logger LOGGER = LoggerFactory.getLogger(CartAppController.class) ;
	private CartAppService cartAppService ;
	
	public CartAppController(final CartAppService cartAppService) {
		this.cartAppService = cartAppService ;
	}
	
	@GetMapping(path = "/index" , consumes = MediaType.ALL_VALUE , produces = MediaType.ALL_VALUE)
	@ResponseBody
	public String getWelComeMsg()
	{
		LOGGER.info("getWelcomeMsg: request received") ;
		return "India is on the moon" ;
	}
	
	//only admin
	@PostMapping(path = "/addNewProduct" , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE , produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Product> addNewProduct(@RequestBody final Product product , BindingResult result)
	{
		ResponseEntity<Product> responseEntity = null;
		
		try {
			
			if(product.getSeller() != null && product.getCategory() !=null)
			{
				this.cartAppService.saveProduct(product);
				
				responseEntity = new ResponseEntity<Product>(product, HttpStatus.OK) ;
			}
			
		} catch (Exception e) {
			
			e.printStackTrace() ;
			responseEntity = new ResponseEntity<Product>(product, HttpStatus.INTERNAL_SERVER_ERROR) ;
		}
		return responseEntity;
		
	}
	//only for user with consumer
	@PostMapping(path = "/addToCart" , consumes = MediaType.APPLICATION_JSON_VALUE , produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<CartProduct>> addToCart(@RequestBody final List<CartProduct> cartProducts, BindingResult result , HttpServletRequest request)
	{
		ResponseEntity<List<CartProduct>> responseEntity = null;
		Cart cart = null ;
		try {
			
			if(!cartProducts.isEmpty())
			{
				this.cartAppService.saveToCart(cartProducts) ;
				responseEntity = new ResponseEntity<List<CartProduct>>(cartProducts, HttpStatus.OK) ;
			}
			
		} catch (Exception e) {
			e.printStackTrace() ;
			responseEntity = new ResponseEntity<List<CartProduct>>(cartProducts, HttpStatus.INTERNAL_SERVER_ERROR) ;
		}
		
		return responseEntity ;
	}
	
	//seller account cant not see the cart restrict the access
	@GetMapping(path="/showCart" , consumes = MediaType.ALL_VALUE , produces = MediaType.APPLICATION_PROBLEM_JSON_VALUE)
	public ResponseEntity<Cart> getItemsInCart()
	{
		ResponseEntity<Cart> responseEntity = null ;
		String userName;
		//get the current user from Security
		SecurityContext currentContext = SecurityContextHolder.getContext() ;
		Object principal= currentContext.getAuthentication().getPrincipal() ;
		
		if(principal instanceof UserDetails)
		{
			userName = ((UserDetails)principal).getUsername() ;
		}
		Cart currentUserCart = this.cartAppService.getItemsInCart(userName) ;
		
		if(currentUserCart != null)
		{
			responseEntity = new ResponseEntity<Cart>(currentUserCart , HttpStatus.OK ) ;
		}
	}
	*/

}
