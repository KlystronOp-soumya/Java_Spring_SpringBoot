package com.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.config.oauth2.client.CommonOAuth2Provider;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;

@SuppressWarnings("deprecation")
@Configuration(proxyBeanMethods = false, value = "webAppSecurityConfig")
@EnableWebSecurity
public class WebAppSecurityConfig extends WebSecurityConfigurerAdapter {

	private transient GithubConfigProps githubConfigProps;

	public WebAppSecurityConfig(final GithubConfigProps githubConfigProps) {
		this.githubConfigProps = githubConfigProps;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// http.csrf().disable() ;
		// http.authorizeHttpRequests().anyRequest().permitAll() ;
		// sets the security config - this basically adds a filterchain
		http.oauth2Login(oAuth2LoginCustomizer -> { // step 3 configure client registration repo
			// then add the authorizedRepo to load the following
			/*
			 * private static final String COLUMN_NAMES = "client_registration_id, " +
			 * "principal_name, " + "access_token_type, " + "access_token_value, " +
			 * "access_token_issued_at, " + "access_token_expires_at, " +
			 * "access_token_scopes, " + "refresh_token_value, " +
			 * "refresh_token_issued_at";
			 * 
			 */
			oAuth2LoginCustomizer.clientRegistrationRepository(clientRegistrationRepository());
			// oAuth2LoginCustomizer.authorizedClientRepository(authorizedClientRepository)
			// oAuth2LoginCustomizer.authorizedClientService(authorizedClientService) ;

		});

		http.authorizeHttpRequests().anyRequest().authenticated();
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED);
		http.logout().invalidateHttpSession(true).clearAuthentication(true).logoutSuccessUrl("/login") ;
	}

	// step 2 add the client registration details to the repository
	// configures the client repository which is similar to InMemoryUserDetails
	private ClientRegistrationRepository clientRegistrationRepository() {
		ClientRegistration clientRegistration = clientRegistration();

		// return new CustomClientRegistrationRepository() ;
		return new InMemoryClientRegistrationRepository(clientRegistration);
	}

	// step 1 configure the cleint registration with all the necessary end points
	// configure the client registration
	// this method defines the necessary URL configs for the Authorization Server
	private ClientRegistration clientRegistration() {
		/*
		 * ClientRegistration clientRegistration =
		 * ClientRegistration.withRegistrationId(githubConfigProps.
		 * getClientRegistrationId())
		 * .clientName("SpringBootOauth2Demo").clientId(githubConfigProps.getClientId())
		 * .clientSecret(githubConfigProps.getClientSecret())
		 * .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC).
		 * build();
		 */
		ClientRegistration clientRegistration = CommonOAuth2Provider.GITHUB.getBuilder("SpringBootOauth2Demo")
				.clientId(System.getenv("ClientId")).clientSecret(System.getenv("ClientSecret")).build();
		return clientRegistration;
	}
}
