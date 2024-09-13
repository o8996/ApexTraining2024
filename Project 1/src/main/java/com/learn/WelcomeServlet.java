package com.learn;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Servlet implementation class WelcomeServlet
 */
@WebServlet("/annotation")
public class WelcomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	public void init() throws ServletException {
		System.out.println("This is init method");
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Request is what is sent to us by user (Browser)
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		
		response.setContentType("text/html");
		
		// Response is what is we sent back to user (Browser)
		PrintWriter writer = response.getWriter();
		writer.append("Hello there "  + firstName + " " + lastName + "!");
		writer.close();
	}
	
	@Override
	public void destroy() {
		System.out.println("I am ending");
	}

}
