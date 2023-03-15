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
import java.sql.Statement;

/**
 * Servlet implementation class ChPassword
 */
@WebServlet("/chPassword") //name should be unique; cant use the same name as the file
public class ChPassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	// Method that changes the user's password:
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Gets the signed-in username and password in the session; It is going to be used for changing the password:
		HttpSession session = request.getSession();
		String username = (String)session.getAttribute("username");
		String password = (String)session.getAttribute("password");
		String newPassword = request.getParameter("newPassword");
		String confirmNewPassword = request.getParameter("confirmNewPassword");	
		
		try {
			Connection connection = null;
			RequestDispatcher dispatcher = null;
					
			// Connect to the database. Change the url based on your computer
			// Sample format: jdbc:sqlserver://<serverName>\;databaseName=<databaseName>;username=<username>;password=<password>encrypt=false;
			String url = "jdbc:sqlserver://MSI\\SQLEXPRESS;databaseName=Xylo;username=Franze;password=hatdog;encrypt=false;";
			connection = DriverManager.getConnection(url);
			
			// QUery that updates the user's password in the "UserAccounts" table:
			Statement statement = connection.createStatement(); 
			String queryUpdatePassword = "UPDATE UserAccounts SET Password = '" + newPassword + "' WHERE Username = '" + username + "' AND Password = '" + password + "'";
			
			// Checks if the session has expired:
			if (username == null || username.isBlank()) {
		    	request.setAttribute("sessionExpired", "failed");
		    	dispatcher = request.getRequestDispatcher("ChPassword.jsp");
		    }
			// Checks if the fields are empty:
			else if (newPassword == null || newPassword.isBlank()  || confirmNewPassword == null || confirmNewPassword.isBlank()) {
				request.setAttribute("fieldsEmpty", "empty");
				dispatcher = request.getRequestDispatcher("ChPassword.jsp");
			}
			// Checks if the password is the same as the new password:
			else if (password.equals(newPassword)) {
				request.setAttribute("samePassword", "failed");
		    	dispatcher = request.getRequestDispatcher("ChPassword.jsp");
			}
			// Checks if the new password matches the confirmed password:
			else if (newPassword.equals(confirmNewPassword)) {
				// Executes the query:
				int updatePassword = statement.executeUpdate(queryUpdatePassword);
							
				// Checks if update is successful
				if (updatePassword > 0) { 
					request.setAttribute("status", "success");
					dispatcher = request.getRequestDispatcher("ChPassword.jsp");			
				}
				else {
					request.setAttribute("status", "failed");
					dispatcher = request.getRequestDispatcher("ChPassword.jsp");
				}	
			}
			else {
				request.setAttribute("confirmNewPassword", "failed");
				dispatcher = request.getRequestDispatcher("ChPassword.jsp");	
			}	
			dispatcher.forward(request, response);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}