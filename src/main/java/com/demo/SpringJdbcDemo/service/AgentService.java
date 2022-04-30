package com.demo.SpringJdbcDemo.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.demo.SpringJdbcDemo.intf.AgentRepositoryIntf;
import com.demo.SpringJdbcDemo.intf.AgentServiceIntf;

@Service(value = "agentServiceBean")
public class AgentService implements AgentServiceIntf {
	/* The Logger */
	private static final Logger LOGGER = LogManager.getLogger(AgentService.class);

	// Injection
	/*
	 * @Autowired private transient TestService testService;
	 */

	// Add the DAO
	private transient AgentRepositoryIntf agentDAO;

	// Default Constructor
	public AgentService() {
	}

	// Constructor injection
	public AgentService(AgentRepositoryIntf agentDAO) {
		this.agentDAO = agentDAO;
		populateResources();
	}

	private void populateResources() {
		// TODO Auto-generated method stub
		this.agentDAO.populateReosurces();
	}
	/*
	 * Constructor Injection
	 * 
	 * @Autowired public AgentService(TestService testService) { // TODO
	 * Auto-generated constructor stub this.testService = testService; }
	 */

	@Override
	public void getAllAgentsList() {
		// TODO Auto-generated method stub
		LOGGER.info("Inside Service class:: getAllAgentList");
		try {
			this.agentDAO.getAllAgentsList().stream().forEach((agent) -> System.out.println(agent));
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println(e);
		}

	}

	@Override
	public void addAgent() {
		// TODO Auto-generated method stub

	}

	/*
	 * public void doCheck() { LOGGER.info("Inside AgentService :: doCheck");
	 * this.testService.doGreet(); }
	 */

	/*
	 * public void doTestDBConnection() {
	 * LOGGER.info("Inside AgentService:: doTestDBConnection"); try {
	 * this.agentDAO.checkConnection(); } catch (SQLException e) { // TODO
	 * Auto-generated catch block e.printStackTrace(); } }
	 */

	/*
	 * @Autowired public void setTestService(TestService testService) {
	 * this.testService = testService; }
	 */
}
