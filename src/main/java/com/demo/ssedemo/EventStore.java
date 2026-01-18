package com.demo.ssedemo;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class EventStore implements ServletContextListener {

    private Thread publisherThread;

    @Override
    public void contextInitialized(ServletContextEvent sce) {

	System.out.println("EventStore initialized");

	publisherThread = new Thread(() -> {
	    int counter = 1;
	    try {
		Thread.sleep(2000);
		while (true) {
		    Thread.sleep(3000);
		    SseServlet.broadcast("Event #" + counter++);
		}
	    } catch (InterruptedException ignored) {
	    }
	});

	publisherThread.start();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
	publisherThread.interrupt();
    }

}
