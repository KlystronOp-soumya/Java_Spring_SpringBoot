package com.demo.hospitalapi.config;

import java.time.Duration;
import java.util.Arrays;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;

@Configuration(value = "appCacheConfig", proxyBeanMethods = false)
@EnableCaching
public class AppCacheConfig {

	/**
	 * <h5>Added on: 23/01/2024</h5>
	 * 
	 * Configures Redis cache
	 * 
	 * */
	
	@Bean
    RedisConnectionFactory redisConnectionFactory() {
		RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration();
		configuration.setHostName("localhost");
		configuration.setPort(6379);
		return new JedisConnectionFactory(configuration);
        
    }

    @Bean
    RedisCacheManager cacheManager(RedisConnectionFactory connectionFactory) {

        RedisCacheConfiguration defaults = RedisCacheConfiguration.defaultCacheConfig()
            .entryTtl(Duration.ofMinutes(5)).disableCachingNullValues() ;
            

        return RedisCacheManager.builder()
        		.fromConnectionFactory(connectionFactory)
            .cacheDefaults(defaults)
            .withCacheConfiguration("cacheAllPatientRecords", defaults)
            .build();
    }
	
	
	// bean config without using any third party secondlevel cache
	/*
	@Bean("cacheManager")
	public CacheManager cacheManager() {
		ConcurrentMapCacheManager mgr = new ConcurrentMapCacheManager();
		mgr.setAllowNullValues(false);
		mgr.setCacheNames(Arrays.asList("cacheAllPatientRecords", "patients"));
		return mgr;
	}
	*/

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
