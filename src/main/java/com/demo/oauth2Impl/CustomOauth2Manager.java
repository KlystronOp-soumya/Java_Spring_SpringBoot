package com.demo.oauth2Impl;

import org.springframework.security.oauth2.client.OAuth2AuthorizeRequest;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizedClientRepository;

/**
 * <p> This class is similar to the AuthenticationManager for http basic or form </p>
 * 
 * 
 * */
public class CustomOauth2Manager implements OAuth2AuthorizedClientManager {
	
	//clientManager -> OAuth2AuthorizedClientRepository -> OAuth2AuthorizedClientService[JdbcOAuth2AuthorizedClientService]
	@Override
	public OAuth2AuthorizedClient authorize(OAuth2AuthorizeRequest authorizeRequest) {
		// TODO authorization logic goes here
		return null;
	}

}
