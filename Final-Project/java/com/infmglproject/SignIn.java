package com.infmglproject;

import jakarta.servlet.*;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.*;
import java.sql.*;

/**
 * Servlet implementation class SignIn
 */

@WebServlet("/signIn")
public class SignIn extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	// Method that handles the user's input on the sign in page:
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Retrieves the user's input on the form:
		String username = request.getParameter("username");
		String password = request.getParameter("password");
				
		// Passes the user's input to the signInUser() method to handle the validation:
		signInUser(request, response, username, password);
	}
	
	// Method that validates the username and password before signing in:
	public static void signInUser (HttpServletRequest request, HttpServletResponse response, String username, String password) {
		// The dispatcher is used to direct incoming web requests to different server-side components such as web pages; Parang traffic enforcer na ginaguide yung mga kotse hahah
		RequestDispatcher dispatcher = null;
		
		// The connection is used to connect to the database
		Connection connection = null;
		
		// HttpSession is used for storing/getting specific information; To be used for storing the username and password after signing in
		HttpSession session = request.getSession();
		
		try {
			// Connect to the database. Change the url based on your computer
			// Sample format: jdbc:sqlserver://<serverName>\;databaseName=<databaseName>;username=<username>;password=<password>encrypt=false;
		    String url = "jdbc:sqlserver://MSI\\SQLEXPRESS;databaseName=Xylo;username=Franze;password=hatdog;encrypt=false;";
		    connection = DriverManager.getConnection(url);
		    
		    // Query that validates the username and password:
		    PreparedStatement pstValidate = connection.prepareStatement("SELECT * FROM UserAccounts WHERE Username=? AND Password=?");	   
		    pstValidate.setString(1, username);
		    pstValidate.setString(2, password);
		    
		    // Executes the query
		    ResultSet rsValidate = pstValidate.executeQuery(); 
		    
		    // Checks if there's a row that matches the query:
		    if (rsValidate.next()) {
		    	// Stores the signed-in username and password after validation; It is going to be used for displaying the reservation details of the user:
		    	session.setAttribute("username", username); 
		    	session.setAttribute("password", password);
		    	
		    	// Sends the user to the reservation page:
		    	dispatcher = request.getRequestDispatcher("Reservation.jsp");
		      }
		    else {
				request.setAttribute("status", "failed");
				dispatcher = request.getRequestDispatcher("SignIn.jsp");
		    }
		    dispatcher.forward(request, response);
	    } 
	    catch (Exception e) {
	    	e.printStackTrace();
	    } 
	}
}