package com.demo.oauth2Impl;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;

public class CustomAuthClientService implements OAuth2AuthorizedClientService {
	
	// this service class then make use of JDBCTemplate or something similar to fetch details from Database
	@Override	
	public <T extends OAuth2AuthorizedClient> T loadAuthorizedClient(String clientRegistrationId,
			String principalName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveAuthorizedClient(OAuth2AuthorizedClient authorizedClient, Authentication principal) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeAuthorizedClient(String clientRegistrationId, String principalName) {
		// TODO Auto-generated method stub
		
	}

}
