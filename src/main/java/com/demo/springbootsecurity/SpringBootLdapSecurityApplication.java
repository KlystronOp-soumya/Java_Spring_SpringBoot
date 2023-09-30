package com.demo.springbootsecurity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class }) 
public class SpringBootLdapSecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootLdapSecurityApplication.class, args);
	}

}
