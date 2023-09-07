package com.demo.cartapi.config.dev

import java.security.PrivateKey
import java.util.function.Function

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Component

import com.demo.cartapi.entity.Users

import net.bytebuddy.asm.Advice.Return
//@Component
class CustomeUserDetalsDev implements UserDetails {
	
	Users users
	
	CustomeUserDetalsDev(Users users)
	{
		this.users = users
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		def userAuthorityList =[]
	  
		userAuthorityList = users.getUserRoles().collect { role -> return new SimpleGrantedAuthority("ROLE_" + role) } 
		
		return userAuthorityList
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return false;
	}
}
