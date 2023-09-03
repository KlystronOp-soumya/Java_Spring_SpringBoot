package com.demo.cartapp.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.demo.cartapp.entity.User;
import com.demo.cartapp.repo.UserRepo;

@Service(value = "customUserService")
public class CustomUserService implements UserDetailsService {
	
	private UserRepo userRepo ;
	public CustomUserService(UserRepo userRepo) {
		// TODO Auto-generated constructor stub
		this.userRepo = userRepo ;
	}
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		User user = this.userRepo.findByUserName(username) ;
		CartUserDetails cartUserDetails = null ;
		if(user !=null)
		{
			cartUserDetails = new CartUserDetails() ;
			cartUserDetails.setCartUser(user);
		}
		else {
			throw new UsernameNotFoundException("User With Name: " + username + " not found") ;
		}
		
		return cartUserDetails ;
	}

}
