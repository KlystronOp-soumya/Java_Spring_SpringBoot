package com.demo.todoapp.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.todoapp.entity.ToDoAppResponseDTO;
import com.demo.todoapp.entity.ToDoEntity;
import com.demo.todoapp.exceptions.ToDoAppException;
import com.demo.todoapp.exceptions.ToDosNotFoundException;
import com.demo.todoapp.exceptions.TodoCanNotUpdateException;
import com.demo.todoapp.exceptions.TodosBlankList;
import com.demo.todoapp.service.DateServiceUtil;
import com.demo.todoapp.service.ToDoAppServiceImpl;
import com.demo.todoapp.service.intf.ToDoService;

@RestController("todoAppController")
@RequestMapping("/api/v1/todos")
public class ToDoAppController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ToDoAppController.class);

	private transient ToDoService todoService;

	// private Validator validator ;
	public ToDoAppController(final ToDoAppServiceImpl todoService /* , final ToDoValidator validator */) {
		this.todoService = todoService;
		// this.validator = validator ;
	}

	@GetMapping(path = "/index")
	public String getIndex() {
		LOGGER.info("Request received :: index");
		LOGGER.debug("Request processed");
		LOGGER.error("Request received :: index");
		return "index";
	}

	@GetMapping(path = "/alltodos", produces = MediaType.APPLICATION_JSON_VALUE)
	public  ResponseEntity<List<ToDoEntity>> getAllToDos(HttpServletRequest request) throws ToDosNotFoundException {
		LOGGER.info("request received :: getAlltodos");
		List<ToDoEntity> allToDoRecords = null;
		ResponseEntity<List<ToDoEntity>> responseEntity = null;
		try {
			LOGGER.info("getAllTodos :: fetching all todos ");
			allToDoRecords = this.todoService.getAllToDos();
			responseEntity = new ResponseEntity<List<ToDoEntity>>(allToDoRecords, HttpStatus.FOUND);

		} catch (Exception e) {
			LOGGER.error("" + e);
			throw new ToDosNotFoundException(e.getMessage(), request.getRequestURI(),
					"No ToDos were found ! Please add some");

		}
		LOGGER.debug(" getAlltodos :: response returned");
		return responseEntity;
	}

	@PostMapping(path = "/addtodos", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<ToDoAppResponseDTO> addTodos(@RequestBody(required = true) List<ToDoEntity> todos,
			HttpServletRequest request) throws ToDoAppException {
		LOGGER.debug("addtodos :: recived list of todos ->" + todos.size());
		ResponseEntity<ToDoAppResponseDTO> responseEntity = null;
		// Errors errors = new BeanPropertyBindingResult(todos, "todos") ;
		try {
			LOGGER.debug("controller :: service method called for save all todos");
			// this.validator.validate(todos, errors);
			// empty list check to be handled by the front end
			this.todoService.saveAllTodos(todos);
			ToDoAppResponseDTO responseDTO = new ToDoAppResponseDTO();
			responseDTO.setSuccessMessage("Todos Added Successfully");
			responseDTO.setSucess(true);
			responseDTO.setCode(HttpStatus.CREATED.value());
			responseDTO.setErrorPresent(false);
			responseDTO.setErrorMap(null);
			responseDTO.setDate(DateServiceUtil.getDate());
			responseDTO.setTime(DateServiceUtil.getTime());
			responseEntity = new ResponseEntity<ToDoAppResponseDTO>(responseDTO, HttpStatus.CREATED);
			LOGGER.debug("addtodos :: all todos saved");
		} catch (Exception e) {
			LOGGER.error("addTodos :: " + e);
			throw new ToDoAppException(e.getMessage(), "Please contact Administrator", request.getRequestURI());

		}
		LOGGER.debug("addTodos :: response returned");
		return responseEntity;
	}
	
	@PostMapping(path = "/updateTodo" , consumes = MediaType.APPLICATION_JSON_VALUE , produces =MediaType.APPLICATION_JSON_VALUE)
	public  ResponseEntity<ToDoAppResponseDTO> updateTodos(@RequestBody(required = true) ToDoEntity todos,
			HttpServletRequest request) throws ToDoAppException {
			ResponseEntity<ToDoAppResponseDTO> responseEntity = null ;
			ToDoAppResponseDTO responseDTO = null;
			LOGGER.info("updateTodos :: trying to update #todos ->" + todos.getTodoId());
			try {
				responseDTO = new ToDoAppResponseDTO() ;
				int updatedTodosCount = todoService.updateTodo(todos) ;
				responseDTO.setSucess(true);
				responseDTO.setSuccessMessage("Todo was updated successfully");
				responseDTO.setCode(HttpStatus.OK.value());
				responseDTO.setErrorPresent(false);
				responseDTO.setErrorMap(null);
				responseDTO.setDate(DateServiceUtil.getDate());
				responseDTO.setTime(DateServiceUtil.getTime());
				responseEntity = new ResponseEntity<ToDoAppResponseDTO>(responseDTO, HttpStatus.OK);
				LOGGER.debug("updateTodos :: todos updated#" + updatedTodosCount);
			} catch (Exception e) {
				LOGGER.error("updateTodos :: " + e);
				ToDoAppException tde = new ToDoAppException(e.getMessage(), "Please contact Administrator", request.getRequestURI()) ;
				tde.initCause(new TodoCanNotUpdateException()) ;
				throw tde ;
			}
			LOGGER.debug("updateTodos :: response returned");
		return responseEntity ;
	}
	
	@DeleteMapping(path = "/deleteTodo", consumes =MediaType.APPLICATION_JSON_VALUE , produces =MediaType.APPLICATION_JSON_VALUE )
	public ResponseEntity<ToDoAppResponseDTO> deleteTodo( List<ToDoEntity> todos , HttpServletRequest request) throws ToDoAppException{
		ResponseEntity<ToDoAppResponseDTO> responseEntity = null ;
		ToDoAppResponseDTO responseDTO = null;
		Optional<List<ToDoEntity>> todosOptional = Optional.ofNullable(todos) ;
		
		try {
			
			this.todoService.deleteTodos( todosOptional.orElseThrow(()-> new TodosBlankList("No todo to delete") ) ) ;
			responseDTO = new ToDoAppResponseDTO() ;
			responseDTO.setSucess(true);
			responseDTO.setSuccessMessage("Todo was deleted successfully");
			responseDTO.setCode(HttpStatus.OK.value());
			responseDTO.setErrorPresent(false);
			responseDTO.setErrorMap(null);
			responseDTO.setDate(DateServiceUtil.getDate());
			responseDTO.setTime(DateServiceUtil.getTime());
			responseEntity = new ResponseEntity<ToDoAppResponseDTO>(responseDTO, HttpStatus.OK);
		
		} catch (TodosBlankList e) {
			LOGGER.error("deleteTodo :: " + e) ;
			
		}catch (ToDoAppException e) {
			LOGGER.error("deletedTodo :: " + e) ;
			ToDoAppException tde = new ToDoAppException(e.getMessage(), "Please contact Administrator", request.getRequestURI()) ;
			tde.initCause(new TodoCanNotUpdateException("Todos could not be deleted")) ;
			throw tde ;
			
		}
		
		LOGGER.debug("deleteTodo :: response returned") ;
		return responseEntity ;
		
	}
}
