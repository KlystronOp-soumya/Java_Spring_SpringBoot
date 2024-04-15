package com.demo.springbootsecurity.configs;

import org.apache.catalina.security.SecurityConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.demo.springbootsecurity.configs.security.CustomSecurityConfig;


@Configuration
@ComponentScan(basePackages = "com.demo.springbootsecurity")
@Import(value = {DatabaseConfig.class , CustomSecurityConfig.class})
public class ApplicationConfig {

}
