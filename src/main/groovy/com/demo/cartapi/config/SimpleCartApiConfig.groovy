/**
 * 
 */
package com.demo.cartapi.config

import org.ehcache.Cache
import org.ehcache.core.EhcacheManager
import org.springframework.cache.CacheManager
import org.springframework.cache.annotation.EnableCaching
import org.springframework.cache.concurrent.ConcurrentMapCache
import org.springframework.cache.ehcache.EhCacheCacheManager
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean
import org.springframework.cache.support.SimpleCacheManager
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Conditional
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import
import org.springframework.context.annotation.Primary
import org.springframework.context.annotation.Profile
import org.springframework.core.io.ClassPathResource
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
@EnableCaching
class SimpleCartApiConfig {
	
	//@Bean
	public EhCacheManagerFactoryBean  cacheManagerFactoryBean() {
		final EhCacheManagerFactoryBean cacheManager = new EhCacheManagerFactoryBean();
		cacheManager.setCacheManagerName("cartApiCacheManager")
		cacheManager.setConfigLocation(new ClassPathResource("ehcache.xml"))
		cacheManager.setShared(false)
		return cacheManager;
	}
	
	//@Bean
	//@Primary
	public CacheManager cacheManager() {
	  return new EhCacheCacheManager(cacheManagerFactoryBean().getObject());
	}
}
