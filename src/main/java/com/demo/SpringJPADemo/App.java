package com.demo.SpringJPADemo;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.demo.SpringJPADemo.intf.AgentServiceIntf;
import com.demo.SpringJPADemo.models.AgentEntity;
import com.demo.SpringJPADemo.service.AgentServiceImpl;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		System.out.println("Hello World!");
		try {
			ApplicationContext contextXML = new ClassPathXmlApplicationContext("context/spring-jpa-context.xml");
			((AbstractApplicationContext) contextXML).refresh();

			AgentServiceIntf agentSrvce = (AgentServiceImpl) contextXML.getBean("agentService");

			AgentEntity agtEntity = agentSrvce.createAgentEntity();
			agentSrvce.saveAgent(agtEntity);
			System.out.println("Showing all agents");
			agentSrvce.getAllActiveAgents().stream().forEach(System.out::println);

			((AbstractApplicationContext) contextXML).close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}
}
