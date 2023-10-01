package com.demo.cartapi.security

import org.springframework.context.annotation.Profile
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Component

import net.bytebuddy.asm.Advice.This

//Dummy auth provider for Dev environment
@Component(value="dummyAuthProvider")
@Profile("dev")
class DummyAuthProvider implements AuthenticationProvider{
	
	private UserDetailsService userDetailsService
	
	DummyAuthProvider(UserDetailsService userDetailsService)
	{
		this.userDetailsService = userDetailsService
	}
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		
		def userId= authentication.getName()
		def password = authentication.getCredentials().toString()
		UserDetails user = this.userDetailsService.loadUserByUsername(userId) ;
		if(userId.equals(user.getUsername()) && password.equals(user.getPassword()))
		{
			return new UsernamePasswordAuthenticationToken(userId, 	password,  user.getAuthorities())
		}
		else
		{
			throw new BadCredentialsException("Could not validate the user")
		}

	}

	@Override
	public boolean supports(Class<?> authentication) {
		
		//return authentication.equals(UsernamePasswordAuthenticationToken.class)
		return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication)
	}
}
