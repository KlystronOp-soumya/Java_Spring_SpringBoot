package com.demo.ssedemo;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.CopyOnWriteArrayList;

import jakarta.servlet.AsyncContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(asyncSupported = true, description = "servlet to handle sse", urlPatterns = { "/sse" })
public final class SseServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private static final CopyOnWriteArrayList<AsyncContext> clients = new CopyOnWriteArrayList<>();

    public SseServlet() {
	super();

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	System.out.println("SSE client connected");

	response.setContentType("text/event-stream");
	response.setCharacterEncoding("UTF-8");
	response.setHeader("Cache-control", "no-cache");
	response.setHeader("Connection", "keep-alive");
	response.setHeader("X-Accel-Buffering", "no");

	AsyncContext asyncContext = request.startAsync();
	asyncContext.setTimeout(0); // infinite

	clients.add(asyncContext);

	asyncContext.getResponse().getWriter().write(": connected\n\n");

    }

    public static void broadcast(String message) {
	for (AsyncContext client : clients) {
	    try {
		PrintWriter writer = client.getResponse().getWriter();

		writer.write("data: " + message + "\n\n");
		writer.flush();
	    } catch (IOException e) {
		clients.remove(client);
		// client.complete();
	    }
	}
    }

}
