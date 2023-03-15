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
 * Servlet implementation class DeleteAccount
 */
public class DeleteAccount extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	// Method that handles the account deletion:
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Gets the ID of the row to delete
            String ID = request.getParameter("ID");

            // Query that deletes the row from the "ReservationList" table:
            String queryDelete = "DELETE FROM UserAccounts WHERE ID = " + ID;

            // Connect to the database. Change the url based on your computer
 			// Sample format: jdbc:sqlserver://<serverName>\;databaseName=<databaseName>;username=<username>;password=<password>encrypt=false;
            String url = "jdbc:sqlserver://MSI\\SQLEXPRESS;databaseName=Xylo;username=Franze;password=hatdog;encrypt=false;";
            
            // Establishes a connection to the database:
            Connection connection = DriverManager.getConnection(url);
            
            // Creates a statement:
            Statement statement = connection.createStatement();
            
            // Executes the query:
            statement.executeUpdate(queryDelete);

            // Redirect the user back to the table view page
            response.sendRedirect(request.getContextPath() + "/AdminCustomerAccounts.jsp");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}