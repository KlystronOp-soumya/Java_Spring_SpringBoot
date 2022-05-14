package com.demo.SpringJPADemo;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

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
			((AbstractApplicationContext) contextXML).close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}
}
