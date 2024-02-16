package com.demo.elkdemo.controller;

import java.awt.PageAttributes.MediaType;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.demo.elkdemo.entity.Agent;
import com.demo.elkdemo.service.AgentService;

import net.bytebuddy.asm.Advice.OffsetMapping.ForOrigin.Renderer.ForReturnTypeName;

@RestController
@RequestMapping("/api/v1")
public class AgentController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AgentController.class) ;
	
	private transient AgentService agentService ;
	public AgentController(final AgentService agentService) {
		
		this.agentService = agentService ;
	
	}
	
	@GetMapping(path = "/allAgents" , produces = org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody List<Agent> allAgents() {
		
		LOGGER.info("Inside allAgents :: trying to fetch all agent list") ;
		return this.agentService.getAllAgents() ;
		
	}
	
	@GetMapping(path = "/agentById" , produces = org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody Agent agentById(@RequestParam("id") final int id) {
		LOGGER.info("Inside agentById :: trying to fetch agent entry by id") ;
		return this.agentService.getAgentDetailsById(id) ;
		
	}
}
