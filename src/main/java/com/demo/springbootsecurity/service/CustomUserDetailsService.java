package com.demo.springbootsecurity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import com.demo.springbootsecurity.model.User;
import com.demo.springbootsecurity.repository.UserREpository;

public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserREpository userREpository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		User user = userREpository.findByUsername();
		CustomUserDetails userDetails = null;
		if (user != null) {
			userDetails = new CustomUserDetails();
			userDetails.setUser(user);
		} else {
			throw new UsernameNotFoundException("User not exist with name: " + username);
		}
		return userDetails;
	}

}
