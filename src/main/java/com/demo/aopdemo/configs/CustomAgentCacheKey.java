package com.demo.aopdemo.configs;

import java.lang.reflect.Method;

import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.interceptor.KeyGenerator;

public class CustomAgentCacheKey implements KeyGenerator {

	public CustomAgentCacheKey() {	}

	@Override
	public Object generate(Object target, Method method, Object... params) {
		
		return target.getClass().getSimpleName() ;
		/*return target.getClass().getSimpleName() + "_"
		          + method.getName() + "_"
		          + StringUtils.joinWith("_", params)*/
	}

}
