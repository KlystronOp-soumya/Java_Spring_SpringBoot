package com.demo.todoapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.demo.todoapp.entity.ToDoEntity;
import com.demo.todoapp.exceptions.ToDoAppException;
import com.demo.todoapp.exceptions.ToDosNotFoundException;
import com.zaxxer.hikari.HikariDataSource;

@Repository("todoAppDAO")
public class ToDoAppDAO {

	private static final Logger LOGGER = LoggerFactory.getLogger(ToDoAppDAO.class);

	private transient DataSource dataSource;
	private transient NamedParameterJdbcTemplate jdbcTemplate ;
	public ToDoAppDAO(final HikariDataSource dataSource , final NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		this.dataSource = dataSource;
		this.jdbcTemplate = namedParameterJdbcTemplate ;
	}

	@Transactional(readOnly = true)
	public Optional<List<ToDoEntity>> getAllToDos() throws ToDosNotFoundException, ToDoAppException {
		LOGGER.info("getAllToDos :: trying to fetch all todos from DB");
		Connection connection = null;
		ResultSet rs = null;
		Statement stmt = null;
		final String query = "SELECT * FROM TODOS";
		List<ToDoEntity> todos = new LinkedList<>();
		Optional<List<ToDoEntity>> todosOptional = Optional.of(todos);
		try {
			connection = this.dataSource.getConnection();
			stmt = connection.createStatement();
			rs = stmt.executeQuery(query);

			while (rs.next()) {
				ToDoEntity toDoEntity = new ToDoEntity();
				todos.add(toDoEntity);
			}
			
			if(todos.isEmpty()) throw new NullPointerException("Blank List") ;
			
		} catch (SQLException e) {
			LOGGER.error("Execption occurred :: " + e);
			throw new ToDoAppException(e.getMessage() + " SQL Error Code: " + e.getErrorCode(), e.getCause());
		} 
		finally {
			try {
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
				if (connection != null)
					connection.close();
			} catch (Exception exp) {
				
				LOGGER.error("Issues while closing resource :: " + exp);
				throw new ToDoAppException(exp.getMessage() + " " + exp.getCause());
			}

		}
		LOGGER.info("getAllToDos :: Total #todos - " + todos.size());
		return todosOptional ;
	}
	
	@Transactional
	public void saveTodos(final List<ToDoEntity> todos) throws ToDoAppException
	{
		LOGGER.debug("saveTodos :: trying to sava all todos ");
		final String query = "INSERT INTO TODOS( TODO_ID ,TODO_DESC , STARTDATE , ENDDATE , COMPLETED) VALUES( :todoId, :todoDesc , :startDate , :endDate , :isCompleted)" ;
		try {
			
			SqlParameterSource[] parameterSource = new  MapSqlParameterSource[todos.size()] ;
			for(int i = 0 ; i<todos.size() ; i++)
			{
				MapSqlParameterSource ps = new MapSqlParameterSource() ;
				
				ps.addValue("todoId", todos.get(i).getTodoId()) ;
				ps.addValue("todoDesc", todos.get(i).getTodoDesc()) ;
				ps.addValue("startDate", todos.get(i).getStartDate()) ;
				ps.addValue("endDate", todos.get(i).getEndDate()) ;
				ps.addValue("isCompleted", todos.get(i).getIsCompleted()) ;
				parameterSource[i] = ps ;
			}
			
			int[] rowsInserted = this.jdbcTemplate.batchUpdate(query, parameterSource) ;
			LOGGER.debug("addTodos :: #of todos inserted-> " , rowsInserted.length);
		
		}catch (DataIntegrityViolationException dte) {
			LOGGER.error("addTodos :: " + dte.getMessage());
			throw new ToDoAppException("Database issue") ;
		}
		catch (Exception e) {
			LOGGER.error("addTodos :: " + e.getMessage());
			ToDoAppException re = new ToDoAppException("Something is wrong") ;
			throw re ;
		}
	}
	
	public int deleteTodoById(final List<Long> todoIds) throws ToDoAppException
	{
		int deletedTodoCount = 0 ;
		final String query = " DELETE FROM TODOS WHERE TODO_ID = :todoIds " ;
		LOGGER.debug("deleteTodoById :: trying to delete all todos in the list");
		try {
			
			SqlParameterSource[] parameterSource = new  MapSqlParameterSource[todoIds.size()] ;
			for(int i = 0 ; i<todoIds.size() ; i++)
			{
				MapSqlParameterSource ps = new MapSqlParameterSource() ;
				
				ps.addValue("todoId", todoIds.get(i) );
				parameterSource[i] = ps ;
			}
			
			 int rowsDeleted[] = this.jdbcTemplate.batchUpdate(query, parameterSource) ;
			 deletedTodoCount = rowsDeleted.length ;
			 LOGGER.info("Selected todos were deleted");
			 LOGGER.debug("deleteTodoById :: #todos deleted " + deletedTodoCount );
			 
		} catch (Exception e) {
			LOGGER.error("addTodos :: " + e);
			ToDoAppException re = new ToDoAppException("Could not deleted the ToDos") ;
			throw re ;
		}
		return deletedTodoCount ;
	}
	
	public int updateTodoById(final ToDoEntity todo) throws ToDoAppException
	{
		LOGGER.debug("updateTodoById :: updating todo " + todo.getTodoId());
		final String query = "UPDATE TODOS SET TODO_DESC = :todoDesc , STARTDATE = :startDate , ENDDATE = :endDate , COMPLETED = :isCompleted "
				+ " WHERE TODO_ID = :todoId" ;
		try {
				
		} catch (Exception e) {
			LOGGER.error("updateTodoById :: " + e);
			ToDoAppException re = new ToDoAppException("Could not update the ToDo") ;
			throw re ;
		}
		return 0 ;
	}
	
}
