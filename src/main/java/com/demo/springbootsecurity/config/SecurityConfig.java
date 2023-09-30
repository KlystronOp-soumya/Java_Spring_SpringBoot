package com.demo.springbootsecurity.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.LdapShaPasswordEncoder;

@SuppressWarnings("deprecation")
@Configuration(proxyBeanMethods = false)
@EnableWebSecurity(debug = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub
		http.csrf().disable() ;
		http.authorizeRequests().anyRequest().fullyAuthenticated().and().formLogin().and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED) ;
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// TODO Auto-generated method stub
		auth.ldapAuthentication()
		.userSearchFilter("uid={0}")
		.userDnPatterns("uid={0},ou=people").userSearchBase("ou=people")
        .groupSearchBase("ou=groups").groupSearchFilter("uniqueMember={0}")
        .contextSource()
          .url("ldap://0.0.0.0:10389/dc=springframework,dc=org").port(10389).root("dc=springframework,dc=org").managerDn("uid=admin,ou=system")
          .managerPassword("secret")
         .and()
        .passwordCompare()
         .passwordEncoder(new LdapShaPasswordEncoder())
         .passwordAttribute("userPassword");
	}
	
	/*@Bean
    public LdapContextSource contextSource () {
        LdapContextSource contextSource= new LdapContextSource();
        contextSource.setUrl("ldap://192.168.2.2:389");
        contextSource.setBase("dc=ad,dc=company,dc=com,dc=pl");
        contextSource.setUserDn("CN=lister,OU=SupportUsers,OU=Users,OU=company,DC=ad,DC=company,DC=com,DC=pl");
        contextSource.setPassword("examplePassword");
        contextSource.setAnonymousReadOnly(false);
        contextSource.setPooled(true);
        contextSource.afterPropertiesSet();
        return contextSource;
    }*/
	/*
	 * @Bean LdapAuthoritiesPopulator authorities(BaseLdapPathContextSource
	 * contextSource) { String groupSearchBase = ""; DefaultLdapAuthoritiesPopulator
	 * authorities = new DefaultLdapAuthoritiesPopulator(contextSource,
	 * groupSearchBase); authorities.setGroupSearchFilter("member={0}"); return
	 * authorities; }
	 */

	/*
	 * @Bean AuthenticationManager authenticationManager(BaseLdapPathContextSource
	 * contextSource, LdapAuthoritiesPopulator authorities) {
	 * LdapBindAuthenticationManagerFactory factory = new
	 * LdapBindAuthenticationManagerFactory(contextSource);
	 * factory.setUserDnPatterns("uid={0},ou=people");
	 * factory.setLdapAuthoritiesPopulator(authorities); return
	 * factory.createAuthenticationManager(); }
	 */
}	
