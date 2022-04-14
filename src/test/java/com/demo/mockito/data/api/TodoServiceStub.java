package com.demo.mockito.data.api;

import java.util.Arrays;
import java.util.List;

public class TodoServiceStub implements TodoService {

	@Override
	public List<String> retrieveTodos(String User) {
		// TODO Auto-generated method stub
		if(null != User)
			return Arrays.asList("Learn Spring MVC","Learn Spring","Learn to swim");
		else {
			return Arrays.asList() ;
		}
	}

}
