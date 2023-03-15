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
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Servlet implementation class Home
 */

@WebServlet("/home")
public class Home extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = null;
		Connection connection = null;
		
		int tablesAvailable = 0;
	    int currentGuests = 0;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

			// Connect to the database
			String url = "jdbc:sqlserver://MSI\\SQLEXPRESS;databaseName=Xylo;username=Franze;password=hatdog;encrypt=false;";
			
			connection = DriverManager.getConnection(url);
			
			tablesAvailable = getTablesAvailable(request, response, connection);
			currentGuests = getCurrentGuests(request, response, connection);
            
            request.setAttribute("tablesAvailable", tablesAvailable);
			request.setAttribute("currentGuests", currentGuests);
            request.getRequestDispatcher("Home.jsp").forward(request, response);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}	
	}
	
	public int getTablesAvailable (HttpServletRequest request, HttpServletResponse response, Connection connection) {
		DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate dateNow = LocalDate.now();
		String formattedDateNow = dateNow.format(formatDate);
		int tablesAvailable = 35;
		
		try {
			PreparedStatement pst = connection.prepareStatement("SELECT Date, SUM(numberOfTables) AS TotalTables FROM ReservationList WHERE Date = ? GROUP BY Date;");
			pst.setString(1, formattedDateNow);
			
			ResultSet rs = pst.executeQuery();
			
			if (rs.next()) {
				int totalTables = rs.getInt("TotalTables");		    
				tablesAvailable = tablesAvailable - totalTables;
			}
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}	
		return tablesAvailable;		
	}

	public int getCurrentGuests (HttpServletRequest request, HttpServletResponse response, Connection connection) {
		DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate dateNow = LocalDate.now();
		String formattedDateNow = dateNow.format(formatDate);
		int currentGuests = 0;
		
		try {
			PreparedStatement pst = connection.prepareStatement("SELECT Date, SUM(numberOfGuests) AS TotalGuests FROM ReservationList WHERE Date = ? GROUP BY Date;");
			pst.setString(1, formattedDateNow);
			
			ResultSet rs = pst.executeQuery();
			
			if (rs.next()) {
				int totalGuests = rs.getInt("TotalGuests");		    
			    currentGuests = totalGuests;
			}
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}	
		return currentGuests;		
	}
}
