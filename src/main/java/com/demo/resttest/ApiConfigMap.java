package com.demo.resttest;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@PropertySource(value = "classpath:api-config.properties")
@ConfigurationProperties(prefix = "api.endpoint",ignoreUnknownFields = true)
@Data
public class ApiConfigMap {
	
	private String postsApi;
	private String commentsApi;

}
