package com.luv2code.testdb;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/TestDbServlet")
public class TestDbServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String user = "springstd";
		String password = "springstd";
		String jdbcUrl = "jdbc:mysql://localhost:3306/web_customer_tracker?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC";
		String Driver = "com.mysql.cj.jdbc.Driver";	                  //or "com.mysql.jdbc.Driver" is deprecated
		
		try {
			
			PrintWriter out = response.getWriter();
			out.printf("Connecting to database: %s%n", jdbcUrl);
			
			Class.forName(Driver);
			Connection myConnection = DriverManager.getConnection(jdbcUrl, user, password);
			
			myConnection.close();

			out.println("Success!!!");
			
		} catch(Exception e) {
			e.printStackTrace();
			throw new ServletException(e);                         //we can see the exception in the browser when everything goes bad. (it's just a hack)
		}
		
	}

}
