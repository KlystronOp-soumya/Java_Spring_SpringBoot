package com.demo.springbootsecurity.configs.security;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.aspectj.weaver.NewConstructorTypeMunger;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.demo.springbootsecurity.entities.Roles;
import com.demo.springbootsecurity.entities.Users;

public class CustomUserDetails implements UserDetails{

	private static final long serialVersionUID = 1L;
	
	//User Pojo
	private Users user ;
	
	
	public void setUser(Users user) {
		this.user = user;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Set<Roles> userRoles=user.getRoles() ;
		return userRoles.stream().map(eachUserRole -> new SimpleGrantedAuthority("ROLE_"+eachUserRole)).collect(Collectors.toList());
	}

	@Override
	public String getPassword() {
		 
		return null;
	}

	@Override
	public String getUsername() {
		 
		return null;
	}

	@Override
	public boolean isAccountNonExpired() {
		 
		return false;
	}

	@Override
	public boolean isAccountNonLocked() {
		 
		return false;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		 
		return false;
	}

	@Override
	public boolean isEnabled() {
		 
		return false;
	}

}
