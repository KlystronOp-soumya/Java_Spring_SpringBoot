package com.demo.todoapp.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
		//TODO add exception handling for the system failures
		return allTodos.orElseThrow(() -> new ToDoAppException("No ToDos were found! Please add ToDo"));
	}

	@Override
	public void saveAllTodos(List<ToDoEntity> todos) throws ToDoAppException {
		LOGGER.info("todoService :: trying to insert all todos");
		try {
			this.toDoAppDAO.saveTodos(todos);
		} catch (Exception e) { //hide the dataaccess exception
			LOGGER.error("saveAllTodos :: " + e.getCause().toString() +"\n"+e.getMessage());
			throw new ToDoAppException("Todos list could not be saved") ;
		}
		
	}

	@Override
	public int deleteTods(List<Long> todoIdList) throws ToDoAppException {
		LOGGER.info("todoService :: trying to delete todos");
		int deletedTodos = 0 ;
		try {
			deletedTodos = this.toDoAppDAO.deleteTodoById(todoIdList) ;
		} catch (Exception e) {
			LOGGER.error("deleteTods :: " + e.getCause().toString() +"\n"+e.getMessage());
			throw new ToDoAppException("Todos list could not be deleted") ;
		}
		return deletedTodos;
	}
	
	/**
	 * Method to update the todos
	 * 
	 * */
	@Override
	public int updateTodos(List<ToDoEntity> todos) throws ToDoAppException {
		
		return 0;
	}
	
	/**
	 * Update a todo by ID
	 *  
	 *  
	 *  */
	@Override
	public int updateTodo(ToDoEntity todo) throws ToDoAppException {
		LOGGER.debug("todoService :: trying to update todo: " + todo.getTodoId());
		int updateRowCount = -1 ;
		try {
			updateRowCount = toDoAppDAO.updateTodoById(todo) ; //updates todo corresponds to the id in this
			
			
		} catch (Exception e) {
			LOGGER.error("uppdateTodo :: " + e.getCause().toString() +"\n"+e.getMessage());
			throw new ToDoAppException("Todo:" + todo.getTodoId() +" could not be updated") ;
		}
		return updateRowCount ;
	}

	@Override
	public void deleteTodo(String todoId) throws ToDoAppException {
		try {
			
			
		} catch (Exception e) {
			
		}
		
	}

	@Override
	public void deleteTodos(List<ToDoEntity> todos) throws ToDoAppException {
		try {
			List<Long> todoIds = todos.stream().map((eachTodo)-> eachTodo.getTodoId()).collect(Collectors.toUnmodifiableList()) ;
			int deletedTodosCount = toDoAppDAO.deleteTodoById(todoIds) ;
		} catch (Exception e) {
			LOGGER.error("uppdateTodo :: " + e.getCause().toString() +"\n"+e.getMessage());
			throw new ToDoAppException("Todos:" +" could not be deleted") ;
		}
		
		
	}
}
