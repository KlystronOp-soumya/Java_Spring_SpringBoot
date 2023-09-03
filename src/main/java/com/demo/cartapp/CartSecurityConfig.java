package com.demo.cartapp;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration(proxyBeanMethods = false)
public class CartSecurityConfig extends WebSecurityConfigurerAdapter{
	
	private UserDetailsService userDetailsService ;
	
	public CartSecurityConfig(final UserDetailsService userDetailsService) {
		this.userDetailsService = userDetailsService ;
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		auth.userDetailsService(this.userDetailsService).passwordEncoder(passwordEncoder()) ;
		
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub
		http.csrf().disable() ;
		http.headers().frameOptions().disable();
		http.authorizeHttpRequests().anyRequest().permitAll() ;
		//http.authorizeHttpRequests().anyRequest().authenticated().and().formLogin();
		
	}
	
	@Bean
	public PasswordEncoder passwordEncoder()
	{
		return NoOpPasswordEncoder.getInstance() ;
	}

}
