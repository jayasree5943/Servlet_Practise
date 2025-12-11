package com.jaya.practise;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.protocol.Resultset;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/MovieServlet")
public class MovieServlet extends HttpServlet {
	String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
	String DB_URL = "jdbc:mysql://localhost:3306/movie_booking_db";
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

		try {
			Connection con = getcon();
			Statement ps = con.createStatement();
			String query = "select * from movies";
			ResultSet rs = ps.executeQuery(query);

			while (rs.next()) {
				int movie_id = rs.getInt("movie_id");
				String movie_name = rs.getString("movie_name");
				String genre = rs.getString("genre");
				Double rating = rs.getDouble("rating");
				String release_date = rs.getString("release_date");

				
				System.out.println(movie_id + " " +movie_name + " " +genre+ " " +rating+ " " +release_date+ "" );
			}
		}catch (Exception ex) {
			System.out.println("exception occured");
			ex.printStackTrace();
		}

		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
