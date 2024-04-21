package com.demo.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration(proxyBeanMethods = false , value = "webAppRootConfig")
@ComponentScan(basePackages = "com.demo")
@Import(value  = {WebAppConfig.class , WebAppSecurityConfig.class})
public class WebAppRootConfig {
	
}
