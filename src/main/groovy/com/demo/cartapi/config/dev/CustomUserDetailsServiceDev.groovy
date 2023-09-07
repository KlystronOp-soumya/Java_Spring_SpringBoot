/**
 * 
 */
package com.demo.cartapi.config.dev

import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

/**
 * @author SOUMYADEEP PAUL
 *
 */
//@Service(value="customUserDetailsService")
class CustomUserDetailsServiceDev implements UserDetailsService {
	
	JdbcTemplate jdbcTemplate
	
	CustomUserDetailsServiceDev(JdbcTemplate jdbcTemplate)
	{
		this.jdbcTemplate = jdbcTemplate
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}
}
