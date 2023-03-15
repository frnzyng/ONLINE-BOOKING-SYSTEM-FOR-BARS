package com.infmglproject;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Servlet implementation class AdminSignIn
 */

@WebServlet("/signInAdmin")
public class AdminSignIn extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	// Method that handles the admin's input on the admin sign in page:
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Retrieves the admin's input on the form:
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		// Passes the admin's input to the SignInUser() method to handle the validation:
		SignInAdmin(request, response, username, password);
	}

	// Method that validates the username and password before signing in:
	public static void SignInAdmin (HttpServletRequest request, HttpServletResponse response, String username, String password) {
		// The dispatcher is used to direct incoming web requests to different server-side components such as web pages:
		RequestDispatcher dispatcher = null;
		
		// The connection is used to connect to the database
		Connection connection = null;
		
		// HttpSession is used for storing specific information; Gagamitin ito para i-store yung username and password after signing in
		HttpSession session = request.getSession();
		
		try {
			// Connect to the database. Change the url based on your computer
			// Sample format: jdbc:sqlserver://<serverName>\;databaseName=<databaseName>;username=<username>;password=<password>encrypt=false;
			String url = "jdbc:sqlserver://MSI\\SQLEXPRESS;databaseName=Xylo;username=Franze;password=hatdog;encrypt=false;";
			connection = DriverManager.getConnection(url);
			
			// Query that validates the username and password:
			PreparedStatement pstValidate = connection.prepareStatement("SELECT * FROM AdminAccounts WHERE Username=? AND Password=?");	   
			pstValidate.setString(1, username);
			pstValidate.setString(2, password);
			
			// Executes the query
			ResultSet rsValidate = pstValidate.executeQuery(); 
			
			// Checks if there's a row that matches the query:
			if (rsValidate.next()) {
				// Stores the signed-in username and password in the session attribute after validation:
				session.setAttribute("username", rsValidate.getString("username"));
				session.setAttribute("password", rsValidate.getString("password"));
			
				// Sends the admin to the customer reservations page:
				dispatcher = request.getRequestDispatcher("AdminCustomerReservations.jsp");
			  }
			else {
				request.setAttribute("status", "failed");
				dispatcher = request.getRequestDispatcher("AdminSignIn.jsp");
	      }
	      dispatcher.forward(request, response);
		} 
		catch (Exception e) {
			e.printStackTrace();
		} 		
	}
}