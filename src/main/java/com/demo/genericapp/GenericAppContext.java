package com.demo.genericapp;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class GenericAppContext implements ApplicationContextAware {

	private transient Object bean;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		System.out.println("Inside App context");
		bean = applicationContext.getBean("oracleDbConfigProps");

	}

	public Object getBean() {
		return bean;
	}

}
