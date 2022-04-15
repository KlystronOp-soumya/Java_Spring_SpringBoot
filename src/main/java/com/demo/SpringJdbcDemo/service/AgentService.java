package com.demo.SpringJdbcDemo.service;

import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.demo.SpringJdbcDemo.DAO.AgentDAO;

@Service(value = "agentServiceBean")
public class AgentService {
	/* The Logger */
	private static final Logger LOGGER = LogManager.getLogger(AgentService.class);

	// Injection
	/*
	 * @Autowired private transient TestService testService;
	 */

	// Add the DAO
	private transient AgentDAO agentDAO;

	// Default Constructor
	public AgentService() {
		// TODO Auto-generated constructor stub
	}

	public AgentService(AgentDAO agentDAO) {
		// TODO Auto-generated constructor stub
		this.agentDAO = agentDAO;
	}
	/*
	 * Constructor Injection
	 * 
	 * @Autowired public AgentService(TestService testService) { // TODO
	 * Auto-generated constructor stub this.testService = testService; }
	 */

	/*
	 * public void doCheck() { LOGGER.info("Inside AgentService :: doCheck");
	 * this.testService.doGreet(); }
	 */

	public void doTestDBConnection() {
		LOGGER.info("Inside AgentService:: doTestDBConnection");
		try {
			this.agentDAO.checkConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/*
	 * @Autowired public void setTestService(TestService testService) {
	 * this.testService = testService; }
	 */
}
