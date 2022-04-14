package com.demo.mockito.business;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import com.demo.mockito.data.api.TodoService;
import com.demo.mockito.data.api.TodoServiceStub;

public class TodoBusinessStubTest {

	@Test
	public void testRetrieveTodosRelatedToSpringn_usingStub() {
		TodoService todoServiceStub = new TodoServiceStub() ;
		TodoBusinessImpl todoBusinessImpl = new TodoBusinessImpl(todoServiceStub);//coupling
		String userString = "Dummy" ;
		List<String> filteredTodoStrings = todoBusinessImpl.retrieveTodoRelatedToSpring(userString);
		
		assertEquals(2, filteredTodoStrings.size());
	}
	
	@Test
	public void testRetrieveTodosRelatedToSpringn_usingStub2() {
		TodoService todoServiceStub = new TodoServiceStub() ;
		TodoBusinessImpl todoBusinessImpl = new TodoBusinessImpl(todoServiceStub);
		List<String> filteredTodoStrings = todoBusinessImpl.retrieveTodoRelatedToSpring(null);
		
		assertEquals(0, filteredTodoStrings.size());
	}

}
