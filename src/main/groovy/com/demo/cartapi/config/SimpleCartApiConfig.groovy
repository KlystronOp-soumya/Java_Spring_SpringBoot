/**
 * 
 */
package com.demo.cartapi.config

import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Conditional
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import
import org.springframework.context.annotation.Profile
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

import com.demo.cartapi.config.dev.DbPersistentDevConfig
import com.demo.cartapi.config.dev.SimpleCartSecurityDevConfig

/**
 * @author SOUMYADEEP PAUL
 *
 */
@Configuration(value="simpleCartApiConfig" ,proxyBeanMethods=false)
@Import(value=[DbPersistentDevConfig.class , SimpleCartSecurityDevConfig.class])
@ComponentScan(basePackages=["com.demo.cartapi"])
@EnableJpaRepositories(basePackages=["com.demo.cartapi.repo"])
class SimpleCartApiConfig {
	
	
}
