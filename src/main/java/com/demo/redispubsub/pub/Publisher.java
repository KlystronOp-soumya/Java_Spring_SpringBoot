package com.demo.redispubsub.pub;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.demo.redispubsub.DTO.Product;

@RestController
public class Publisher {

	@Autowired
	private RedisTemplate<?, ?> redisTemplate ;
	
	@Autowired
	private ChannelTopic  topic ;
	
	@PostMapping("/publish")
	public String publish (@RequestBody final Product product)
	{
		redisTemplate.convertAndSend(topic.getTopic(), product.toString());
		return "event published" ;
	}
}
