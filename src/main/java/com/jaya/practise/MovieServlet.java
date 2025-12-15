package com.jaya.practise;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/MovieServlet")
public class MovieServlet extends HttpServlet {
	String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
	String DB_URL = "jdbc:mysql://localhost:3306/ticket";
	String DB_USERNAME = "root";
	String DB_PASSWORD = "root";

	public Connection getcon() throws Exception {
		Class.forName(DB_DRIVER);
		Connection con = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
		if (con != null) {
			System.out.println("Done");
		} else {
			System.out.println("Not done");
		}
		return con;

	}

	private static final long serialVersionUID = 1L;

	public MovieServlet() {
		super();

		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        try (Connection con = getcon();
   		     Statement s = con.createStatement();
   		     ResultSet rs = s.executeQuery("SELECT * FROM movie")) {
        	
        	
   		    StringBuilder movieListRow = new StringBuilder();

   		    while (rs.next()) {
   		    	int id = rs.getInt("id");
   		    	String name = rs.getString("name");
   		    	String genre = rs.getString("genre");
   		    	Double rating = rs.getDouble("rating");
   		    	String releasedate = rs.getString("releasedate");
   		    	
   		        movieListRow.append("<tr>");
   		        movieListRow.append("<td>").append(id).append("</td>");
   		        movieListRow.append("<td>").append(name).append("</td>");
   		        movieListRow.append("<td>").append(genre).append("</td>");
   		        movieListRow.append("<td>").append(rating).append("</td>");
   		        movieListRow.append("<td>").append(releasedate).append("</td>");
   		        

   		        movieListRow.append("</tr>");
   		    }
   		    String responseStr = "<html>"
   		            + "<body>"
   		            + "<h2>Movie List</h2>"
   		            + "<table border='1' cellpadding='5' cellspacing='0'>"
   		            + "<tr>"
   		            + "<th>ID</th>"
   		            + "<th>Movie Name</th>"
   		            + "<th>Genre</th>"
   		            + "<th>rating</th>"
   		            + "<th>releasedate</th>"
   		            + "</tr>"
   		            + movieListRow
   		            + "</table>"
   		            + "</body>"
   		            + "</html>";

   		    out.print(responseStr);
		        
		}catch (Exception ex) {
			System.out.println("exception occured");
			ex.printStackTrace();
		}

		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	 protected void doPost(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {

	        response.setContentType("text/html");
	        PrintWriter out = response.getWriter();

	        String name = request.getParameter("name");
	        String genre = request.getParameter("genre");
	        String ratingStr = request.getParameter("rating");
	        String releaseDateStr = request.getParameter("releasedate");

	        try (Connection con = getcon()) {

	            String query = "INSERT INTO movie (name, genre, rating, releasedate) VALUES (?, ?, ?, ?)";
	            PreparedStatement ps = con.prepareStatement(query);

	            
	            ps.setString(1, name);
	            ps.setString(2, genre);

	            
	            double rating = 0;
	            try {
	                if (ratingStr != null && !ratingStr.isEmpty()) {
	                    rating = Double.parseDouble(ratingStr);
	                }
	            } catch (NumberFormatException e) {
	                rating = 0;
	            }
	            ps.setDouble(3, rating);

	            java.sql.Date releasedate = null;
	            try {
	                if (releaseDateStr != null && !releaseDateStr.isEmpty()) {
	                    releasedate = java.sql.Date.valueOf(releaseDateStr); // format yyyy-MM-dd
	                }
	            } catch (IllegalArgumentException e) {
	                releasedate = null;
	            }
	            ps.setDate(4, releasedate);

	            int rows = ps.executeUpdate();
	            if (rows > 0) {
	                System.out.println("Record inserted successfully!");
	            } else {
	                System.out.println("Insert failed!");
	            }

	        } catch (Exception e) {
	            e.printStackTrace();
	        }

	        doGet(request, response);
	}


}
