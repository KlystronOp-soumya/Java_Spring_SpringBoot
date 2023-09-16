package com.demo.cartapi.config

import org.ehcache.event.CacheEvent
import org.ehcache.event.CacheEventListener
import org.springframework.context.annotation.Configuration


class CacheEventLogger implements CacheEventListener<Object , Object> {

	@Override
	public void onEvent(CacheEvent event) {
		// TODO Auto-generated method stub
		
	}
}
