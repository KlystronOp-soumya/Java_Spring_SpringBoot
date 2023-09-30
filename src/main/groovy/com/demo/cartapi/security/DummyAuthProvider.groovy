package com.demo.cartapi.security

import org.springframework.context.annotation.Profile
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException
import org.springframework.stereotype.Component

//Dummy auth provider for Dev environment
@Component(value="dummyAuthProvider")
@Profile("dev")
class DummyAuthProvider implements AuthenticationProvider{

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		
		def userId= authentication.getName()
		def password = authentication.getCredentials().toString()
		
		if(userId.equals("sdas") && password.equals("kolkata@100"))
		{
			return new UsernamePasswordAuthenticationToken(userId, 	password, Arrays.asList("ADMIN"))
		}
		else
		{
			throw new BadCredentialsException("Could not validate the user")
		}
		
		
		
	}

	@Override
	public boolean supports(Class<?> authentication) {
		
		return authentication.equals(UsernamePasswordAuthenticationToken.class)
	}
}
