package com.demo.aopdemo.configs;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.cache.RedisCacheManagerBuilderCustomizer;
import org.springframework.cache.CacheManager;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.cache.CacheKeyPrefix;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheManager.RedisCacheManagerBuilder;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.domain.geo.RadiusShape;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext.SerializationPair;
import org.springframework.http.ResponseEntity;

import com.demo.aopdemo.AppConstant;
import com.demo.aopdemo.entity.Agent;

@Configuration(proxyBeanMethods = false)
public class AgentMgmtRedisConfig {

	//get the connection factory
	@Bean
	public RedisConnectionFactory redisConnectionFactory()
	{
		RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration() ;
		redisStandaloneConfiguration.setDatabase(0);
		redisStandaloneConfiguration.setHostName("localhost");
		redisStandaloneConfiguration.setPort(6379);
		
		
		LettuceConnectionFactory lettuceConnectionFactory = new LettuceConnectionFactory(redisStandaloneConfiguration) ;
		lettuceConnectionFactory.setClientName("agentCacheClient");
		
		return lettuceConnectionFactory ;
	}
	
	//configure the RedisTemplate
	@Bean
	public RedisTemplate<String	, Agent> redisTemplate(final RedisConnectionFactory redisConnectionFactory)
	{
		RedisTemplate<String, Agent> redisTemplate = new RedisTemplate<>() ;
		redisTemplate.setConnectionFactory(redisConnectionFactory);
		
		redisTemplate.afterPropertiesSet();
		
		return redisTemplate ;		
	}
	
	//redis cache config
	@SuppressWarnings("static-access")
	//@Bean("agentCacheManager")
	public RedisCacheManagerBuilderCustomizer redisCacheManagerBuilderCustomizer(final RedisConnectionFactory redisConnectionFactory)
	{
		RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig() ;
		redisCacheConfiguration.disableCachingNullValues();
		redisCacheConfiguration.entryTtl(Duration.ofMinutes(5)) ;
		redisCacheConfiguration.prefixCacheNameWith("CACHE_") ;
		redisCacheConfiguration.prefixKeysWith("cachedAgent_") ;
		
		return (builder) -> builder.
				fromConnectionFactory(redisConnectionFactory).
				transactionAware().
				withCacheConfiguration("agentCache", redisCacheConfiguration)
				.enableStatistics().build() ;
	}
	
	//@Bean(name = "defaultRedisConfig")
	public RedisCacheConfiguration redisCacheConfiguration()
	{
		CacheKeyPrefix cacheKeyPrefix = (cacheName) -> cacheName.toUpperCase().concat("::") ; // this returns target type anonymous function to match -> String compute(String cacheName);
		RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig()
				.disableCachingNullValues()
				.entryTtl(Duration.ofMinutes(2))
				.computePrefixWith((cacheName) -> cacheName.toUpperCase().concat("::"))
				.serializeValuesWith(SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer())) ;
		
		
		return redisCacheConfiguration ;
	}
	
	//Configure the redis cache
	@Bean
	//@Primary
	public CacheManager agentCacheManager(final RedisConnectionFactory redisConnectionFactory) {
		CacheKeyPrefix cacheKeyPrefix = (cacheName) -> cacheName.concat(CacheKeyPrefix.SEPARATOR).concat("_cachedAll") ;
		RedisCacheManager.RedisCacheManagerBuilder  redisCacheManagerBuilder = RedisCacheManager.builder(redisConnectionFactory) ;
		return redisCacheManagerBuilder
								.transactionAware()		
								.enableStatistics()
								.withCacheConfiguration(AppConstant.AGENT_CACHE_NAME.value(), RedisCacheConfiguration.defaultCacheConfig()
										.disableCachingNullValues()
										.entryTtl(Duration.ofMinutes(1))
										.computePrefixWith((cacheName) -> cacheName.toUpperCase().concat("::"))
										.serializeValuesWith(SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer())) )
								
								.withCacheConfiguration(AppConstant.ALL_AGENT_CACHE_NAME.value(), RedisCacheConfiguration.defaultCacheConfig()
										.disableCachingNullValues()
										.computePrefixWith((cacheName) -> cacheName.toUpperCase().concat(":: ALLVALUES ::"))
										.entryTtl(Duration.ofMinutes(1)))
								.build() ;
																
		
	}
	/*
	 * @Bean("allAgentCacheManager") public CacheManager allAgentCacheManager(final
	 * RedisConnectionFactory redisConnectionFactory) { CacheKeyPrefix
	 * cacheKeyPrefix = (cacheName) ->
	 * cacheName.concat(CacheKeyPrefix.SEPARATOR).concat("_cachedAll") ;
	 * RedisCacheManager.RedisCacheManagerBuilder redisCacheManagerBuilder =
	 * RedisCacheManager.builder(redisConnectionFactory) ; return
	 * redisCacheManagerBuilder .transactionAware() .enableStatistics()
	 * .withCacheConfiguration(AppConstant.ALL_AGENT_CACHE_NAME.value(),
	 * RedisCacheConfiguration.defaultCacheConfig() .disableCachingNullValues()
	 * .entryTtl(Duration.ofMinutes(2))) .build() ;
	 * 
	 * }
	 */
	
	@Bean("customAgentCacheKey")
	public KeyGenerator CustomAgentCacheKey() {
		return new CustomAgentCacheKey() ;
	}
	
}
