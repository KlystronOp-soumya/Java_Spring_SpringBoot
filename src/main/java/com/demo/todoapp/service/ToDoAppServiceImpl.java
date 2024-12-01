package com.demo.todoapp.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;

import com.demo.todoapp.dao.ToDoAppDAO;
import com.demo.todoapp.entity.ToDoEntity;
import com.demo.todoapp.exceptions.ToDoAppException;
import com.demo.todoapp.exceptions.ToDosNotFoundException;
import com.demo.todoapp.service.intf.ToDoService;

@Service("todoAppSrvc")
public class ToDoAppServiceImpl implements ToDoService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ToDoAppServiceImpl.class);

	private transient ToDoAppDAO toDoAppDAO;

	public ToDoAppServiceImpl(final ToDoAppDAO toDoAppDAO) {
		this.toDoAppDAO = toDoAppDAO;
	}

	@Override
	public List<ToDoEntity> getAllToDos() throws ToDoAppException, ToDosNotFoundException {
		LOGGER.debug("Service method called :: trying to get all todos");
		Optional<List<ToDoEntity>> allTodos = null;
		allTodos = this.toDoAppDAO.getAllToDos();
		LOGGER.debug("Fetched");
		return allTodos.orElseThrow(() -> new ToDoAppException("No ToDos were found! Please add ToDo"));
	}

	@Override
	public void saveAllTodos(List<ToDoEntity> todos) throws ToDoAppException {
		LOGGER.info("todoService :: trying to insert all todos");
		try {
			this.toDoAppDAO.addTodos(todos);
		} catch (Exception e) { //hide the dataaccess exception
			LOGGER.error("saveAllTodos :: " + e.getCause().toString() +"\n"+e.getMessage());
			throw new ToDoAppException("Todos list could not be saved") ;
		}
		
		
	}
}
