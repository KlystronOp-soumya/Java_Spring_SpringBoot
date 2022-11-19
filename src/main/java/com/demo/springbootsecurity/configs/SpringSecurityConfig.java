package com.demo.springbootsecurity.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

@SuppressWarnings("deprecation")
@Configuration(value = "spring_security_config")
@PropertySource(value = "classpath:application.properties")
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private Environment env ;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		//TODO fix the Basic HTTP auth issue not using the environment variables
		System.out.println("User: " + env.getProperty("user.name") +" \nPassword: "+env.getProperty("user.password")+" \nRoles: "+ env.getProperty("user.roles"));
		/*//This line was commented as was not working
		 * auth.inMemoryAuthentication().withUser(env.getProperty("user.name").toString(
		 * )).password(env.getProperty("user.password").toString()).roles(env.
		 * getProperty("user.roles").toString()) ;
		 */
		
		//adding the credentials manually
		auth.inMemoryAuthentication().withUser("Soumya").password("13608").roles("ADMIN","DEVELOPER") ;
		
		//another user added
		auth.inMemoryAuthentication().withUser("Soumya").password("13626").roles("QA") ;
	}
	
	//security for all api
	/*
	 * @Override protected void configure(HttpSecurity http) throws Exception {
	 * http.csrf().disable() ;
	 * http.authorizeRequests().anyRequest().fullyAuthenticated().and().httpBasic()
	 * ; }
	 */
	
	//security for url
	//applies security only for the url which starts from /rest
	/*
	 * @Override protected void configure(HttpSecurity http) throws Exception {
	 * http.authorizeRequests().antMatchers("/rest/**").fullyAuthenticated().and().
	 * httpBasic() ; }
	 */
	
	//security based on role
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable() ;
		http.authorizeRequests().antMatchers("/rest/**").hasAnyRole("ADMIN","DEVELOPER").anyRequest().fullyAuthenticated().and().httpBasic() ;
	}
	@Bean
	public static NoOpPasswordEncoder passwordEncoder()
	{
		return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance() ;
	}

}
