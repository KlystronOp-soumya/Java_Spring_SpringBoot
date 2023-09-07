package com.demo.cartapi.config.dev

import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.authentication.configurers.provisioning.InMemoryUserDetailsManagerConfigurer
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter

@SuppressWarnings("deprecation")
@Configuration(value="simpleSecurityConfig")
@Profile(value=["dev"])
class SimpleCartSecurityDevConfig extends WebSecurityConfigurerAdapter{
	
	AuthenticationProvider authenticationProvider
	
	SimpleCartSecurityDevConfig(AuthenticationProvider authenticationProvider)
	{
		this.authenticationProvider = authenticationProvider
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub
		http.csrf().disable()
		http.headers().frameOptions().disable()
		http.authorizeRequests().anyRequest().permitAll()
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// TODO Auto-generated method stub
		auth.authenticationProvider(authenticationProvider).build()
	}
}
