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
import java.sql.Statement;

/**
 * Servlet implementation class ChangeUsernamePassword
 */
@WebServlet("/chUsername")
public class ChUsername extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	// Method that changes the user's username:
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Gets the signed-in username in the session; It is going to be used for changing the username:
		HttpSession session = request.getSession();
		String username = (String)session.getAttribute("username");
		String newUsername = request.getParameter("newUsername");
		String confirmNewUsername = request.getParameter("confirmNewUsername");
		
		try {
			Connection connection = null;
			RequestDispatcher dispatcher = null;
			
			// Connect to the database. Change the url based on your computer
			// Sample format: jdbc:sqlserver://<serverName>\;databaseName=<databaseName>;username=<username>;password=<password>encrypt=false;
			String url = "jdbc:sqlserver://MSI\\SQLEXPRESS;databaseName=Xylo;username=Franze;password=hatdog;encrypt=false;";
			
			// Establishes a connection to the database:
			connection = DriverManager.getConnection(url);
			
			// Query that checks if the username already exists:
			PreparedStatement pstGetUsername = connection.prepareStatement("SELECT Username FROM UserAccounts WHERE Username = ?;");
			pstGetUsername.setString(1, newUsername);
			
			// Executes the query:
			ResultSet rsGetUsername = pstGetUsername.executeQuery();
			
			// Query that updates the username of the user in the "UserAccounts" & "ReservationList" table:
			Statement statement = connection.createStatement();
			String queryUpdateUsernameUA = "UPDATE UserAccounts SET Username = '" + newUsername + "' WHERE Username = '" + username + "'";
			String queryUpdateUsernameRL = "UPDATE ReservationList SET Username = '" + newUsername + "' WHERE Username = '" + username + "'";
			
			// Checks if the session has expired:
			if (username == null || username.isBlank()) {
		    	request.setAttribute("sessionExpired", "failed");
		    	dispatcher = request.getRequestDispatcher("ChUsername.jsp");
		    }
			// Checks if the fields are empty:
			else if (newUsername == null || newUsername.isBlank()  || confirmNewUsername == null || confirmNewUsername.isBlank()) {
				request.setAttribute("fieldsEmpty", "empty");
				dispatcher = request.getRequestDispatcher("ChUsername.jsp");
			}
			// Checks if the username is the same as the new username:
			else if (username.equals(newUsername)) {
				request.setAttribute("sameUsername", "failed");
				dispatcher = request.getRequestDispatcher("ChUsername.jsp");
			}
			// Checks if the username already exists:
			else if (rsGetUsername.next()) {
				request.setAttribute("usernameExists", "failed");
				dispatcher = request.getRequestDispatcher("ChUsername.jsp");
			}
			// Checks if the new username matches the confirmed username:
			else if (newUsername.equals(confirmNewUsername)) {
				int updateUsernameUA = statement.executeUpdate(queryUpdateUsernameUA); // Updates username in UserAccounts table
				int updateUsernameRL = statement.executeUpdate(queryUpdateUsernameRL); // Updates username in ReservationList table
				
				// Checks if update is successful
				if (updateUsernameUA > 0 || updateUsernameRL > 0) { 
					request.setAttribute("status", "success");
					dispatcher = request.getRequestDispatcher("ChUsername.jsp");			
				}
				else {
					request.setAttribute("status", "failed");
					dispatcher = request.getRequestDispatcher("ChUsername.jsp");
				}	
			}
			else {
				request.setAttribute("confirmUsername", "failed");
				dispatcher = request.getRequestDispatcher("ChUsername.jsp");	
			}	
			dispatcher.forward(request, response);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}