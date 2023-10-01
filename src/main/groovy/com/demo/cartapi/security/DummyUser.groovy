package com.demo.cartapi.security



import org.springframework.context.annotation.Profile
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Component

import net.bytebuddy.asm.Advice.This

//@Component
//@Profile("dev")
class DummyUser implements UserDetails{
	
	String username
	String password
	
	DummyUser(def username , def password)
	{
		this.username = username
		this.password = password
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		/*When hasRole("ADMIN") was used with out new SimpleGrantedAuthority("ROLE_ADMIN")  it FAILED
		 * 
		 * Change to ROLE_it will work
		 * 
		 * In case of hasAuhtority do not need to add ROLE_
		 * */
		return Arrays.asList(  new SimpleGrantedAuthority("ROLE_ADMIN") ,new SimpleGrantedAuthority("SELLER") ,
					new SimpleGrantedAuthority("CONSUMER") , 
					new SimpleGrantedAuthority("FINANCE"))
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return username;
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
}
