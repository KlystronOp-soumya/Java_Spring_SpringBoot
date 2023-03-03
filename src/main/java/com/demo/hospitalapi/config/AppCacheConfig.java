package com.demo.hospitalapi.config;

import java.time.Duration;
import java.util.Arrays;

import org.springframework.boot.autoconfigure.cache.RedisCacheManagerBuilderCustomizer;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheManager.RedisCacheManagerBuilder;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext.SerializationPair;

@Configuration(value = "appCacheConfig", proxyBeanMethods = false)
@EnableCaching
public class AppCacheConfig {

	// bean config without using any third party secondlevel cache

	@Bean("cacheManager")
	public CacheManager cacheManager() {
		ConcurrentMapCacheManager mgr = new ConcurrentMapCacheManager();
		mgr.setAllowNullValues(false);
		mgr.setCacheNames(Arrays.asList("cacheAllPatientRecords", "patients"));
		return mgr;
	}

	/*
	 * @Bean public RedisCacheConfiguration cacheConfiguration() { return
	 * RedisCacheConfiguration.defaultCacheConfig()
	 * .entryTtl(Duration.ofMinutes(60)) .disableCachingNullValues()
	 * .serializeValuesWith(SerializationPair.fromSerializer(new
	 * GenericJackson2JsonRedisSerializer())); }
	 */
	/*
	 * @Bean public RedisCacheManagerBuilderCustomizer
	 * redisCacheManagerBuilderCustomizer() {
	 * 
	 * return (builder) -> builder.withCacheConfiguration("cacheAllPatientRecords",
	 * RedisCacheConfiguration.defaultCacheConfig().
	 * entryTtl(Duration.ofMinutes(5))) .withCacheConfiguration("patients",
	 * RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofMinutes(10))
	 * ) ;
	 * 
	 * }
	 */
}
