package com.demo.todoapp.exceptions;

import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.demo.todoapp.entity.ErrorMap;
import com.demo.todoapp.entity.ToDoAppResponseDTO;
import com.demo.todoapp.service.DateServiceUtil;

@RestControllerAdvice(basePackages = {"com.demo.todoapp.controller"})
public class ToDoAppExceptionResolver {

	private static final Logger LOGGER = LoggerFactory.getLogger(ToDoAppExceptionResolver.class) ;
	
	//@ResponseStatus(reason = "No ToDos were found" , code = HttpStatus.NOT_FOUND)
	@ExceptionHandler(value = ToDosNotFoundException.class)
	public ResponseEntity<ToDoAppResponseDTO> todosNotFoundException(final ToDosNotFoundException toDosNotFoundException) {
		ErrorMap error = new ErrorMap() ;
		// can be introduces builder method
		error.setErrorExcep(toDosNotFoundException.getMessage());
		error.setErrorDesc(toDosNotFoundException.getDescription());
		error.setErrorCode( String.valueOf( HttpStatus.NOT_FOUND.value()));
		error.setErrorDate(DateServiceUtil.getDate());
		error.setErrorTime(DateServiceUtil.getTime());
		error.setPath(toDosNotFoundException.getPath());
		
		ToDoAppResponseDTO responseDTO = new ToDoAppResponseDTO() ;
		responseDTO.setSucess(false);
		responseDTO.setErrorPresent(true);
		responseDTO.setErrorMap(error);
		
		LOGGER.error("ERROR PROCESSING REQUEST :: " + error.getErrorExcep());
		
		ResponseEntity<ToDoAppResponseDTO> errorResponseEntity = new ResponseEntity<ToDoAppResponseDTO>(responseDTO, HttpStatus.NOT_FOUND) ;
		
		return errorResponseEntity ;
		
	}
	
	@ExceptionHandler(value = ToDoAppException.class)
	public ResponseEntity<ToDoAppResponseDTO> handleTodoAppException(final ToDoAppException toDoAppException) {
		ErrorMap errorMap = new ErrorMap() ;
		errorMap.setErrorExcep(toDoAppException.getMessage());
		errorMap.setErrorDesc(toDoAppException.getDescription());
		errorMap.setPath(toDoAppException.getPath());
		errorMap.setErrorTime(DateServiceUtil.getTime());
		errorMap.setErrorDate(DateServiceUtil.getDate());

		
		ToDoAppResponseDTO responseDTO = new ToDoAppResponseDTO() ;
		responseDTO.setErrorPresent(true);
		responseDTO.setSucess(false);
		responseDTO.setErrorMap(errorMap);
		
		return new ResponseEntity<ToDoAppResponseDTO>(responseDTO , HttpStatus.INTERNAL_SERVER_ERROR) ;
	}
}
