package com.demo.springbootsecurity.configs;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@Configuration
@ComponentScan(basePackages = {"com.demo"})
@EnableWebSecurity
@Import(value = {SpringSecurityConfig.class})
public class AppConfiguration {

}
