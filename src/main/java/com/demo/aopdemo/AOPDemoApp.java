package com.demo.aopdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import com.demo.aopdemo.aspectanno.Loggable;

@SpringBootApplication
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class AOPDemoApp {
	
	public static void main(String[] args) {
		
		SpringApplication.run(AOPDemoApp.class, args) ;
	}

}
