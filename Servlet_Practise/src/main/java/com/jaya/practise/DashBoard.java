package com.jaya.practise;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/DashBoard")
public class DashBoard extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public DashBoard() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String display ="<html>\r\n"
				+ "<body>\r\n"
				+ "<h1>Welcome! "+request.getAttribute("LoggedInUser")+"</h1>\r\n"
				+ "<h2> This is your dashboard page </h2>\r\n"
				+ "</body>\r\n"
				+ "</html>";
		response.getWriter().append(display);
		doGet(request, response);
	}

}
