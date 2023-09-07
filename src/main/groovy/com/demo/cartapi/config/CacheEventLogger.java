package com.demo.cartapi.config;

import org.ehcache.event.CacheEvent;
import org.ehcache.event.CacheEventListener;

public class CacheEventLogger implements CacheEventListener<Object, Object>{

	@Override
	public void onEvent(CacheEvent<? extends Object, ? extends Object> event) {
		// TODO Auto-generated method stub
		
	}
}
