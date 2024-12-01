package com.demo.todoapp.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.todoapp.entity.ToDoAppResponseDTO;
import com.demo.todoapp.entity.ToDoEntity;
import com.demo.todoapp.exceptions.ToDoAppException;
import com.demo.todoapp.exceptions.ToDosNotFoundException;
import com.demo.todoapp.service.DateServiceUtil;
import com.demo.todoapp.service.ToDoAppServiceImpl;
import com.demo.todoapp.service.intf.ToDoService;
import com.demo.todoapp.validator.ToDoValidator;

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
	public ResponseEntity<List<ToDoEntity>> getAllToDos(HttpServletRequest request) throws ToDosNotFoundException {
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

}
