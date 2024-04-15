package com.demo.springbootsecurity.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import com.demo.springbootsecurity.repo.CustomUserRepository;
import com.demo.springbootsecurity.configs.security.CustomUserDetails;
import com.demo.springbootsecurity.entities.Users;



@Service(value = "customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private CustomUserRepository userRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		Users user = this.userRepo.findByUserName(username);
		CustomUserDetails customUserDetails;
		if (user != null) {
			customUserDetails = new CustomUserDetails();
			customUserDetails.setUser(user);
		} else {
			throw new UsernameNotFoundException("User not exist with name: " + username);
		}
		return new org.springframework.security.core.userdetails.User(username, customUserDetails.getPassword(),
				customUserDetails.getAuthorities());
	}

}
