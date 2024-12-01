package com.demo.todoapp.service.intf;

import java.util.List;

import com.demo.todoapp.entity.ToDoEntity;
import com.demo.todoapp.exceptions.ToDoAppException;
import com.demo.todoapp.exceptions.ToDosNotFoundException;

public interface ToDoService {
	List<ToDoEntity> getAllToDos() throws ToDoAppException, ToDosNotFoundException ;
	void saveAllTodos(List<ToDoEntity> todos) throws ToDoAppException ;
}
