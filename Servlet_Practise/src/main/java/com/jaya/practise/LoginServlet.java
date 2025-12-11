package com.jaya.practise;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public LoginServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.print("doGet is done");
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		if(username.equals(password)) {
			
			request.setAttribute("LoggedInUser", password);
			RequestDispatcher rd = request.getRequestDispatcher("/Dashboard");
			rd.forward(request, response);
		}
		else {
			//System.out.println("Authentication failed");
			//response.getWriter().print("Sorry UserName or Password Error!");   // THIS LINE WILL ONLY WORK WITH INCLUDE NOT FORWORD
			RequestDispatcher rd = request.getRequestDispatcher("/login.html");
			rd.forward(request, response);                   // BY USING THE FORWARD THE URL WILL NOT CHANGE.
			//rd.include(request, response);                 // THIS WILL BE A STATIC OUTPAGE PAGE
			//response.sendRedirect("https://google.com"); // THIS IS WORKING  FOR SENDREDIRECT THE URL WILL CHANGE
			//response.sendRedirect("login.html");         // THIS IS WORKING
		}
		
		
	}

}
