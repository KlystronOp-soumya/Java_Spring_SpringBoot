package com.demo.todoapp.security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true , securedEnabled = true)
public class ToDoAppSecurityConfig{

	
	  	@Bean
	    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	        http.cors().disable() ;
	       
	  		http.csrf().disable()
	                .authorizeRequests().anyRequest().permitAll()
	                //.anyRequest().authenticated()
	                .and().sessionManagement()
	                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

	        //http.addFilterBefore(jwtRequestFilter(), UsernamePasswordAuthenticationFilter.class);

	        return http.build();
	    }
}
