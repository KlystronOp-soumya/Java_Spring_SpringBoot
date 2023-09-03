package com.demo.cartapp.service;

import java.util.Collection;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.UserDetailsManager;

import com.demo.cartapp.entity.Role;
import com.demo.cartapp.entity.User;

public class CartUserDetails implements UserDetails {
	
	private User cartUser ;
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		Function<Role, SimpleGrantedAuthority> mapAuthorities = (eachAuth)->{ return new SimpleGrantedAuthority("ROLE_" + eachAuth) ;} ;
		
		return this.cartUser.getRoles().stream().map(mapAuthorities).collect(Collectors.toList());
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return this.cartUser.getUserPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.cartUser.getUserName();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

	public User getCartUser() {
		return cartUser;
	}

	public void setCartUser(User cartUser) {
		this.cartUser = cartUser;
	}

	
}
