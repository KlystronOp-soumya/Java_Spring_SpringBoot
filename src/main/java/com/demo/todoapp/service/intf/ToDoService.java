package com.demo.todoapp.service.intf;

import java.util.List;

import com.demo.todoapp.entity.ToDoEntity;
import com.demo.todoapp.exceptions.ToDoAppException;
import com.demo.todoapp.exceptions.ToDosNotFoundException;

public interface ToDoService {
	List<ToDoEntity> getAllToDos() throws ToDoAppException, ToDosNotFoundException ;
	void saveAllTodos(List<ToDoEntity> todos) throws ToDoAppException ;
	int deleteTods(List<Long> todoIdList) throws ToDoAppException;
	int updateTodos(List<ToDoEntity> todos) throws ToDoAppException;
	int updateTodo(final ToDoEntity todo) throws ToDoAppException ;
	void deleteTodo(final String todoId) throws ToDoAppException ;
	void deleteTodos(final List<ToDoEntity> todos) throws ToDoAppException ;
}
