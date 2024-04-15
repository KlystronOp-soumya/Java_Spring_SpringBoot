package com.demo.springbootsecurity.configs.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.demo.springbootsecurity.filters.JwtRequestFilter;

@SuppressWarnings("deprecation")
@Configuration
//@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
public class CustomSecurityConfig extends WebSecurityConfigurerAdapter{
	
	// TODO change it to constructor based injection
	@Autowired
	private UserDetailsService userDetailsService ;
	
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider(); //sets the password delegations
		daoAuthenticationProvider.setUserDetailsService(userDetailsService);
		daoAuthenticationProvider.setPasswordEncoder(PasswordEncoder());
		auth.authenticationProvider(daoAuthenticationProvider) ;
	}
	
	@Bean
	public PasswordEncoder PasswordEncoder() {
		
		//return NoOpPasswordEncoder.getInstance() ;
		return new BCryptPasswordEncoder() ;
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		//http.csrf().disable() ;
		/*http.authorizeRequests().antMatchers("/login").fullyAuthenticated().
		anyRequest().fullyAuthenticated()
		.filterSecurityInterceptorOncePerRequest(true)
		.and().exceptionHandling()
		.and().sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS); */
		http.headers().frameOptions().disable();
		 http.csrf().disable().authorizeRequests().antMatchers("/authenticate" , "/h2-console")
         .permitAll()
         //.anyRequest().authenticated().and().formLogin()
         .and().exceptionHandling().and().sessionManagement()
         .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
		//add the JWT filter
		http.addFilterBefore( new JwtRequestFilter()  , UsernamePasswordAuthenticationFilter.class) ; //filter order can be set as there are multiple filter chains
	}
	
	
}
