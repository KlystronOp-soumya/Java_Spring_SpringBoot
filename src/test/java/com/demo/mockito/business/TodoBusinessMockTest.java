package com.demo.mockito.business;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.demo.mockito.data.api.TodoService;
import com.demo.mockito.data.api.TodoServiceStub;

public class TodoBusinessMockTest {

	/*
	 * mocking is creating objects that simulate the behavoir of real objects.
	 * Unlike stubs, mocks can be dynamically created from code-at runtime.
	 * Mocks offer more functionality than stubbing
	 * */
	@Test
	public void testRetrieveTodosRelatedToSpringn_usingMock() {
		TodoService mockTodoService = mock(TodoService.class);
		List<String> todosList = Arrays.asList("Learn Spring MVC","Learn Spring","Learn to swim");
		when(mockTodoService.retrieveTodos("Dummy")).thenReturn(todosList) ;
		/*The above implementation is similar to the Stub method*/
		TodoBusinessImpl todoBusinessImpl = new TodoBusinessImpl(mockTodoService) ;
		List<String> filteredTodoList = todoBusinessImpl.retrieveTodoRelatedToSpring("Dummy") ;
		assertEquals(2, filteredTodoList.size());
	}
	@Test
	public void testRetrieveTodosRelatedToSpringn_withEmptyList() {
		TodoService mockTodoService = mock(TodoService.class);
		List<String> todosList = Arrays.asList();
		when(mockTodoService.retrieveTodos("Dummy")).thenReturn(todosList) ;
		/*The above implementation is similar to the Stub method*/
		TodoBusinessImpl todoBusinessImpl = new TodoBusinessImpl(mockTodoService) ;
		List<String> filteredTodoList = todoBusinessImpl.retrieveTodoRelatedToSpring("Dummy") ;
		assertEquals(2, filteredTodoList.size());
	}
}
