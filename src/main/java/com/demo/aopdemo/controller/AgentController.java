package com.demo.aopdemo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demo.aopdemo.entity.Agent;
import com.demo.aopdemo.entity.ResponseModel;
import com.demo.aopdemo.service.AgentService;

import io.micrometer.core.lang.NonNull;
import io.micrometer.core.lang.Nullable;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;


@RestController
@RequestMapping(path = "/api/v1")
public class AgentController {
	
	// TODO implement method for Update
	// TODO implement method for delete
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AgentController.class) ;
	
	private transient AgentService agentService ;
	
	public AgentController(final AgentService agentService) {
		this.agentService = agentService ;		
	}

	@GetMapping(path = "/index")
	public ResponseEntity<?> index() {
		Map<String, ResponseModel> responseMap = new HashMap<String, ResponseModel>();
		ResponseModel responseModel = new ResponseModel();
		responseModel.setSucess(true);
		responseModel.setStatusCode(HttpStatus.OK.value());
		responseModel.setObject("Hello World") ;
		responseMap.put("data", responseModel);
		ResponseEntity<?> responseEntity = new ResponseEntity<> (responseMap , HttpStatus.FOUND) ;
		return responseEntity ;
	}
	
	@ApiResponses(
			value = {
					@ApiResponse(responseCode = "201" , description = "Agent was added" ,
			content = {
					@Content ( mediaType = "application/json" , schema = @Schema(implementation = Agent.class) )
			}) 
	})
	@PostMapping(path = "/addAgentDetails" , consumes = MediaType.APPLICATION_JSON_VALUE , produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<String, ResponseModel>> addAgent(@NonNull @RequestBody final Agent agent , final HttpServletRequest request) {
		Map<String, ResponseModel> responseMap = new HashMap<String, ResponseModel>();
		ResponseModel responseModel = new ResponseModel();
		ResponseEntity<Map<String, ResponseModel>> responseEntity = null ;
		try {
			agentService.saveAgent(agent) ;
			responseModel.setSucess(true);
			responseModel.setStatusCode(HttpStatus.CREATED.value());
			responseModel.setObject(agent) ;
			responseModel.setErrorMessage("") ;
			responseMap.put("data", responseModel);
			responseEntity = new ResponseEntity<> (responseMap , HttpStatus.OK) ; 
		} catch (Exception e) {			
			responseModel.setSucess(false);
			responseModel.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
			responseModel.setObject(agent) ;
			responseModel.setErrorMessage(e.getMessage()) ;
			responseMap.put("data", responseModel);
			responseEntity = new ResponseEntity<> (responseMap , HttpStatus.INTERNAL_SERVER_ERROR) ;
		}
		return responseEntity ;
				
	}
	
	@GetMapping(path = "/allAgentDetails" , produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<String, ResponseModel>> allAgents() {
		Map<String, ResponseModel> responseMap = new HashMap<String, ResponseModel>();
		ResponseModel responseModel = new ResponseModel();
		ResponseEntity<Map<String, ResponseModel>> responseEntity = null ;
		try {
			List<Agent> agents = agentService.getAllAgents() ;
			responseModel.setSucess(true);
			responseModel.setStatusCode(HttpStatus.FOUND.value());
			responseModel.setObject(agents) ;
			responseModel.setErrorMessage("") ;
			responseMap.put("data", responseModel);
			responseEntity = new ResponseEntity<> (responseMap , HttpStatus.OK) ; 
		} catch (Exception e) {			
			responseModel.setSucess(false);
			responseModel.setStatusCode(HttpStatus.NOT_FOUND.value());
			responseModel.setObject(null) ;
			responseModel.setErrorMessage(e.getMessage()) ;
			responseMap.put("data", responseModel);
			responseEntity = new ResponseEntity<> (responseMap , HttpStatus.NOT_FOUND) ;
		}		
		return responseEntity ;
	}
	
	@GetMapping(path = "/agentDetails" , produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<String, ResponseModel>>  getAgentDetils(@NonNull @RequestParam final String agtId , 
																	  @Nullable @RequestParam final String agtNum ) {
		Map<String, ResponseModel> responseMap = new HashMap<String, ResponseModel>();
		ResponseModel responseModel = new ResponseModel();
		ResponseEntity<Map<String, ResponseModel>> responseEntity = null ;
		try {
			Agent foundAgent = agentService.getAgentById(agtId , agtNum) ;
			responseModel.setSucess(true);
			responseModel.setStatusCode(HttpStatus.FOUND.value());
			responseModel.setObject(foundAgent) ;
			responseModel.setErrorMessage("") ;
			responseMap.put("data", responseModel);
			responseEntity = new ResponseEntity<> (responseMap , HttpStatus.OK) ; 
		} catch (Exception e) {
			responseModel.setSucess(false);
			responseModel.setStatusCode(HttpStatus.NOT_FOUND.value());
			responseModel.setObject(null) ;
			responseModel.setErrorMessage(e.getMessage()) ;
			responseMap.put("data", responseModel);
			responseEntity = new ResponseEntity<> (responseMap , HttpStatus.BAD_REQUEST) ;
		}
		
		return responseEntity ;
	}
	
	@DeleteMapping(path = "/agentDetails" , consumes = MediaType.APPLICATION_JSON_VALUE , produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<String, ResponseModel>> deleteAgentDetails(@NotNull final Agent agent)
	{
		return null ;
	}
	
	@PostMapping(path = "/agentDetails" , consumes = MediaType.APPLICATION_JSON_VALUE , produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<String, ResponseModel>> updateAgentDetails(@NotNull final Agent agent)
	{
		return null ;
	}
	
	
}
