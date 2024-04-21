package com.demo.oauth2Impl;

import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;

/*
 * This class is similar to the 
 * 
 * */
public class CustomOauth2UserSerive implements OAuth2UserService<OAuth2UserRequest, CustomOauthUser> {
	// restemplate to get user from a specific userinfo endpoint
	@Override
	public CustomOauthUser loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
		// TODO load the user from the endpoint
		return null;
	}

}
