package com.infmglproject;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/**
 * Servlet implementation class ClearAllReservations
 */

@WebServlet("/clearAllReservations")
public class ClearAllReservations extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	// Method that clears all the rows in the "ReservationList" table:
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {	      
	        // SQL delete query:
	        String queryDelete = "DELETE FROM [Table]"; //Dummy table

	        // Connect to the database. Change the url based on your computer
	     	// Sample format: jdbc:sqlserver://<serverName>\;databaseName=<databaseName>;username=<username>;password=<password>encrypt=false;
	        String url = "jdbc:sqlserver://MSI\\SQLEXPRESS;databaseName=Xylo;username=Franze;password=hatdog;encrypt=false;";
	        
	        // Establishes a connection to the database:
	        Connection connection = DriverManager.getConnection(url);
	        Statement statement = connection.createStatement();
	        
	        // Executes the query; Deletes all the customer's reservations:
	        statement.executeUpdate(queryDelete);

	        // Redirect the user back to the "AdminCustomerReservations" page
	        response.sendRedirect(request.getContextPath() + "/AdminCustomerReservations.jsp");
	    } catch (Exception e) {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
	    }
	}

}
