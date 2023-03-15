package com.infmglproject;

import jakarta.servlet.*;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.*;
import java.sql.*;
import java.time.LocalDate;
import java.time.Period;

import org.apache.tomcat.jakartaee.commons.compress.utils.IOUtils;

/**
 * Servlet implementation class SignUp
 */

@WebServlet("/signUp")
@MultipartConfig
public class SignUp extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	// Method that handles the user's input on the sign up page:
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Retrieves the user's input on the sign up page:
		String fullName = request.getParameter("fullName");
		String username = request.getParameter("username");
		String email = request.getParameter("email");	
		String contactNumber = request.getParameter("contactNumber");
		String password = request.getParameter("password");
		String confirmPassword = request.getParameter("confirmPassword");
		String birthdate = request.getParameter("birthdate");			
		Part partValidID = request.getPart("validID");
		
		// Converts the image to byte:		
	    byte[] validID = IOUtils.toByteArray(partValidID.getInputStream());
	    
	    // Computes the user's age:
	    LocalDate birthdateLD = LocalDate.parse(birthdate);
	    LocalDate dateNow = LocalDate.now();
		Period computeAge = Period.between(birthdateLD, dateNow);
		int age = computeAge.getYears();
		
		try {
			// The dispatcher is used to direct incoming web requests to different server-side components:
			RequestDispatcher dispatcher = null;
			
			if (fullName == null || fullName.isBlank() || username == null || username.isBlank() || email == null || email.isBlank() || contactNumber == null || contactNumber.isBlank() || password == null || password.isBlank() || confirmPassword == null || confirmPassword.isBlank()) {
				request.setAttribute("fieldsEmpty", "empty");
				dispatcher = request.getRequestDispatcher("SignUp.jsp");
			}		
			else if (age < 18) {
				request.setAttribute("ageStatus", "minor");
				dispatcher = request.getRequestDispatcher("SignUp.jsp");
			}
			else if (password.equals(confirmPassword)) {
				// Passes the user's input to the signUpUser() method
				signUpUser(request, response, fullName, username, email, contactNumber, password, birthdate, validID);
			}
			else {
				request.setAttribute("passwordMatch", "failed");
				dispatcher = request.getRequestDispatcher("SignUp.jsp");
			}	
			// Forwards the request to the parameter of the dispatcher:
		    dispatcher.forward(request, response);
	    } 
	    catch (Exception e) {
	    	e.printStackTrace();
	    } 
	}
	
	// Method that inserts the data to the database:
	public static void signUpUser (HttpServletRequest request, HttpServletResponse response, String fullName, String username, String email, String contactNumber, String password, String birthdate, byte[] validID) {
		try {
			// The dispatcher is used to direct incoming web requests to different server-side components:
			RequestDispatcher dispatcher = null;
			
			// The connection is used to connect to the database
			Connection connection = null;
			
			// Connect to the database. Change the url based on your computer
			// Sample format: jdbc:sqlserver://<serverName>\;databaseName=<databaseName>;username=<username>;password=<password>encrypt=false;
		    String url = "jdbc:sqlserver://MSI\\SQLEXPRESS;databaseName=Xylo;username=Franze;password=hatdog;encrypt=false;";
		    
		    // Establishes a connection to the database:
		    connection = DriverManager.getConnection(url);
		      
		    // Query that checks if the username already exists:
			PreparedStatement pstUsernameExists = connection.prepareStatement("SELECT Username FROM UserAccounts WHERE Username=?");
			pstUsernameExists.setString(1, username);
		      
		    // Query that checks if the email already exists:
		    PreparedStatement pstEmailExists = connection.prepareStatement("SELECT Email FROM UserAccounts WHERE Email=?");
		    pstEmailExists.setString(1, email);
		    
		    // Executes the queries:
		    ResultSet rsUsernameExists = pstUsernameExists.executeQuery();
		    ResultSet rsEmailExists = pstEmailExists.executeQuery();
		    
	    	if (rsUsernameExists.next()) {
	    		request.setAttribute("usernameExists", "failed");
	    		dispatcher = request.getRequestDispatcher("SignUp.jsp");
			}
			else if (rsEmailExists.next()) {
				request.setAttribute("emailExists", "failed");
				dispatcher = request.getRequestDispatcher("SignUp.jsp");
			}
			else {
				// Query that inserts the following values entered by the user on the sign up page:
				PreparedStatement pstInsert = connection.prepareStatement("INSERT INTO UserAccounts(Fullname, Username, Email, ContactNumber, Password, Birthdate, ValidID) VALUES (?,?,?,?,?,?,?) ");
			    pstInsert.setString(1, fullName);
			    pstInsert.setString(2, username);
			    pstInsert.setString(3, email);
			    pstInsert.setString(4, contactNumber);
			    pstInsert.setString(5, password);
			    pstInsert.setString(6, birthdate);	
			    pstInsert.setBytes(7, validID); 
			    
			    // Executes the query and returns the number of rows affected in the table:
			    int rowCount = pstInsert.executeUpdate();
			    dispatcher = request.getRequestDispatcher("SignUp.jsp");
			    
			    // Checks if a row is affected in the table:
			    if (rowCount > 0) {
			    	request.setAttribute("status", "success");
			    }
			    else {
			    	request.setAttribute("status", "failed");
			    }
			}
	    	// Forwards the request to the parameter of the dispatcher:
	    	dispatcher.forward(request, response);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}