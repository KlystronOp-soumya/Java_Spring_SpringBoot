package com.demo.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @author Soumyadeep Paul
 * 
 * A class as a wrapper for GitHub as Auth server Configs
 * 
 * */
@Component
@ConfigurationProperties(ignoreInvalidFields = false , ignoreUnknownFields =  false , prefix = "github.auth.dev")
@PropertySource(ignoreResourceNotFound = false , value = "classpath:gitHubConfig.properties")
public class GithubConfigProps {
	
	private String clientRegistrationId ;
	private String clientId ;
	private String clientSecret ;
	public String getClientRegistrationId() {
		return clientRegistrationId;
	}
	public void setClientRegistrationId(String clientRegistrationId) {
		this.clientRegistrationId = clientRegistrationId;
	}
	public String getClientId() {
		return clientId;
	}
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	public String getClientSecret() {
		return clientSecret;
	}
	public void setClientSecret(String clientSecret) {
		this.clientSecret = clientSecret;
	}
	
	
	
	
	
}
