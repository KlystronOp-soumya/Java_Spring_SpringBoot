package com.demo.SpringOrmDemo;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.demo.SpringOrmDemo.Util.ConfigPathEnum;
import com.demo.SpringOrmDemo.intf.AgentService;
import com.demo.SpringOrmDemo.service.AgentServiceImpl;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext(ConfigPathEnum.SPRING_CONTEXT_PATH.value);
		System.out.println("Hello World!");
		AgentService agtService = (AgentServiceImpl) context.getBean("agentService");
		agtService.saveAgent(agtService.creatAgentEntity());

		agtService.getAllActiveAgents().stream().forEach((eachAgt) -> System.out.println(eachAgt));
		((AbstractApplicationContext) context).close();
	}
}
