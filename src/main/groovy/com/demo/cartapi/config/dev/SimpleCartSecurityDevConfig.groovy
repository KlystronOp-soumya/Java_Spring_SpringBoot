package com.demo.cartapi.config.dev

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.authentication.configurers.provisioning.InMemoryUserDetailsManagerConfigurer
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.password.NoOpPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.bind.annotation.RequestMapping

import groovyjarjarantlr4.v4.runtime.atn.SemanticContext.AND

@SuppressWarnings("deprecation")
@Configuration(value="simpleSecurityConfig")
@EnableWebSecurity
@Profile(value=["dev"])
class SimpleCartSecurityDevConfig extends WebSecurityConfigurerAdapter{
	
	AuthenticationProvider authenticationProvider
	
	//for higher environments the authentication provider will be changed to active directory
	SimpleCartSecurityDevConfig(AuthenticationProvider authenticationProvider)
	{
		this.authenticationProvider = authenticationProvider
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub
		http.csrf().disable()
		http.headers().frameOptions().disable()
		http.authenticationProvider(authenticationProvider)
		http.httpBasic()
		http.authorizeRequests().mvcMatchers("/admin/manage/**").hasRole("ADMIN")
		.anyRequest().authenticated()
		http.cors().disable()
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
	}
	// do not need to override the configure method twice
	// this is for higher version of Spring Boot Security
	/*@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// TODO Auto-generated method stub
		auth.authenticationProvider(authenticationProvider).build()
	}*/
	
	@Bean
	public PasswordEncoder passwordEncoder()
	{
		return NoOpPasswordEncoder.getInstance() 
	}
}
