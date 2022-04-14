package com.demo.mockito.business;

import java.util.ArrayList;
import java.util.List;

import com.demo.mockito.data.api.TodoService;

public class TodoBusinessImpl {
	private TodoService todoService ;
	
	public TodoBusinessImpl (TodoService todoService) {
		this.todoService = todoService ;
	}
	
	public List<String> retrieveTodoRelatedToSpring (String User) {
		List<String> filteredTodoStrings = new ArrayList<>();
		List<String> todoStrings = this.todoService.retrieveTodos(User);
		
		for(String todoString : todoStrings)
		{
			if(todoString.contains("Spring"))
			{
				filteredTodoStrings.add(todoString) ;
			}
		}
		return filteredTodoStrings ;
	}
}
