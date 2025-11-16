package com.demo.resttest.service;

import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.demo.resttest.ApiConfigMap;

@Service(value = "backendCommunicator")
public class ApiCommunicator {
	
	private ApiConfigMap apiConfigMap;
	
	public ApiCommunicator(ApiConfigMap apifApiConfigMap) {
		this.apiConfigMap = apifApiConfigMap;
	}
	
	public RequestEntity<Void> getPostsCommunicator() {
		URI url;
		RequestEntity<Void> postsRequestEntity = null;
		try {
			url = new URI(apiConfigMap.getPostsApi());
			postsRequestEntity = RequestEntity.get(url).build();
		} catch (URISyntaxException ex) {
			ex.printStackTrace();
		}
	
		return postsRequestEntity;
	}
	
	public RestTemplate getCommentsCommunicator() {
		return null;
	}
}
