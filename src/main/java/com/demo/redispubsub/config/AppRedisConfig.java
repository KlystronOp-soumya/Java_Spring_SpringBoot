package com.demo.redispubsub.config;

import org.springframework.boot.autoconfigure.data.redis.JedisClientConfigurationBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration.JedisClientConfigurationBuilder;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.redis.serializer.GenericToStringSerializer;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.demo.redispubsub.sub.Receiver;

@Configuration
@EnableRedisRepositories
public class AppRedisConfig {

//	@Bean
//	public JedisClientConfigurationBuilderCustomizer jedisClientConfigurationBuilderCustomizer() {
//		JedisClientConfigurationBuilderCustomizer clientBuilder = (JedisClientConfigurationBuilder builder) -> builder
//				.clientName("redisApiClient").usePooling().build();// only implementation provided
//
//		return clientBuilder;
//	}

	@Bean("jedisConnectionFactory")
	public JedisConnectionFactory connectionFactory() {
		RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration();
		configuration.setHostName("localhost");
		configuration.setPort(6379);
		return new JedisConnectionFactory(configuration);
	}

	@Bean("redisTemplate")
	public RedisTemplate<String, Object> template() {
		RedisTemplate<String, Object> template = new RedisTemplate<>();
		template.setConnectionFactory(connectionFactory());
		template.setKeySerializer(new StringRedisSerializer());
		template.setHashKeySerializer(new StringRedisSerializer());
		template.setHashKeySerializer(new JdkSerializationRedisSerializer());
		template.setValueSerializer(new GenericToStringSerializer<Object>(Object.class));
		template.setEnableTransactionSupport(true);
		template.afterPropertiesSet();
		return template;
	}
	
	//configure the channel or the topic
	@Bean("channelTopic")
	public ChannelTopic topic()
	{
		return new ChannelTopic("pubsub:demo-channel") ;
	}
	
	//configure the messasge listener adapter
	@Bean("messageListenerAdapter")
	public MessageListenerAdapter messageListenerAdapter()
	{
		return new MessageListenerAdapter(new Receiver()) ;
	}
	
	@Bean("redisMessageListenerContainer")
	public RedisMessageListenerContainer redisMessageListenerContainer()
	{
		RedisMessageListenerContainer container = new RedisMessageListenerContainer() ;
		container.setConnectionFactory(connectionFactory());
		container.addMessageListener(messageListenerAdapter(), topic());
		return container ;
	}

}
