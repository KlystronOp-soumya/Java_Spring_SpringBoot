package com.demo.SpringOrmDemo;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.demo.SpringOrmDemo.Util.ConfigPathEnum;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		AbstractApplicationContext context = new ClassPathXmlApplicationContext(
				ConfigPathEnum.SPRING_CONTEXT_PATH.value);
		System.out.println("Hello World!");
		context.close();
	}
}
