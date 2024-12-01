package com.demo.todoapp;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component("todoAppContextAw")
public class ToDoAppContext implements ApplicationContextAware {

	private transient ApplicationContext applicationContext ;
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
			this.applicationContext = applicationContext ;
		
	}

	public ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	

}
