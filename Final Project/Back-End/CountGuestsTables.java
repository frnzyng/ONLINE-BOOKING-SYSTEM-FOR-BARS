package com.infmglproject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class CountGuestsTables {
	static int tablesAvailable = 35;
	static int currentGuests = 0;
	static int yourTables = 0;
	static int yourGuests = 0;
	static int reservations = 0;

	// Method that gets the current number of reserved tables:
	public static int getTablesAvailable (HttpServletRequest request, HttpServletResponse response) {
		// Sets the date format:
		DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		
		// Gets the current date and time to be used for the query:
		LocalDate dateNow = LocalDate.now();
		String formattedDateNow = dateNow.format(formatDate);
		
		try {
			// Connect to the database. Change the url based on your computer
			// Sample format: jdbc:sqlserver://<serverName>\;databaseName=<databaseName>;username=<username>;password=<password>encrypt=false;
			String url = "jdbc:sqlserver://MSI\\SQLEXPRESS;databaseName=Xylo;username=Franze;password=hatdog;encrypt=false;";
			
			// Establishes a connection to the database:
			Connection connection = DriverManager.getConnection(url);
			
			// Query that gets the current number of reserved tables:
			PreparedStatement pstGetTables = connection.prepareStatement("SELECT Date, SUM(numberOfTables) AS TotalTables FROM ReservationList WHERE Date = ? GROUP BY Date;");
			pstGetTables.setString(1, formattedDateNow);
			
			// Executes the query:
			ResultSet rsGetTables = pstGetTables.executeQuery();
			
			// Checks if a row matches with the query:
			if (rsGetTables.next()) {
				// Gets the total tables found in the table that matched with the query:
				int totalTables = rsGetTables.getInt("TotalTables");		    
				tablesAvailable = 35 - totalTables;
			}
			else {
				tablesAvailable = 35;
			}
		} 
		catch (Exception e) {
			e.printStackTrace();
		}	
		return tablesAvailable;		
	}

	// Method that gets the current number of guests:
	public static int getCurrentGuests (HttpServletRequest request, HttpServletResponse response) {
		// Sets the date format:
		DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		
		// Gets the current date and time to be used for the query:
		LocalDate dateNow = LocalDate.now();
		String formattedDateNow = dateNow.format(formatDate);
		
		try {
			// Connect to the database. Change the url based on your computer
			// Sample format: jdbc:sqlserver://<serverName>\;databaseName=<databaseName>;username=<username>;password=<password>encrypt=false;
			String url = "jdbc:sqlserver://MSI\\SQLEXPRESS;databaseName=Xylo;username=Franze;password=hatdog;encrypt=false;";
			
			// Establishes a connection to the database:
			Connection connection = DriverManager.getConnection(url);
			
			// Query that gets the current number of guests:
			PreparedStatement pstGetGuests = connection.prepareStatement("SELECT Date, SUM(numberOfGuests) AS TotalGuests FROM ReservationList WHERE Date = ? GROUP BY Date;");
			pstGetGuests.setString(1, formattedDateNow);
			
			// Executes the query:
			ResultSet rsGetGuests = pstGetGuests.executeQuery();
			
			// Checks if a row matches with the query:
			if (rsGetGuests.next()) {
				// Gets the total guests found in the table that matched with the query:
				currentGuests = rsGetGuests.getInt("TotalGuests");
			}
			else {
				currentGuests = 0;
			}
		} 
		catch (Exception e) {
			e.printStackTrace();
		}	
		return currentGuests;		
	}
	
	// Method that gets the number of tables the user reserved:
	public static int getYourTables (HttpServletRequest request, HttpServletResponse response) {
		// Gets the signed-in username in the session; It is going to be used for displaying the reservation details of the user:
		HttpSession session = request.getSession();
		String username = (String)session.getAttribute("username");
		
		try {
			// Connect to the database. Change the url based on your computer
			// Sample format: jdbc:sqlserver://<serverName>\;databaseName=<databaseName>;username=<username>;password=<password>encrypt=false;
			String url = "jdbc:sqlserver://MSI\\SQLEXPRESS;databaseName=Xylo;username=Franze;password=hatdog;encrypt=false;";
			
			// Establishes a connection to the database:
			Connection connection = DriverManager.getConnection(url);
			
			// Query that gets the number of tables the user reserved:
			PreparedStatement pstGetReservedTables = connection.prepareStatement("SELECT SUM(numberOfTables) AS TotalTables FROM ReservationList WHERE Username = ? AND Date >= CONVERT(DATE, GETDATE()) GROUP BY Date;");
			pstGetReservedTables.setString(1, username);
			
			// Executes the query:
			ResultSet rsGetReservedTables = pstGetReservedTables.executeQuery();
			
			// Checks if a row matches with the query: 
			if (rsGetReservedTables.next()) {
				// Gets the number of tables reserved by the user:
				yourTables = rsGetReservedTables.getInt("TotalTables");		    
			}
			else {
				yourTables = 0;
			}
		} 
		catch (Exception e) {
			e.printStackTrace();
		}	
		return yourTables;		
	}
	
	// Method that gets the number of guests the user reserved for:
	public static int getYourGuests (HttpServletRequest request, HttpServletResponse response) {
		// Gets the signed-in username in the session; It is going to be used for displaying the reservation details of the user:
		HttpSession session = request.getSession();
		String username = (String)session.getAttribute("username");
		
		try {
			// Connect to the database. Change the url based on your computer
			// Sample format: jdbc:sqlserver://<serverName>\;databaseName=<databaseName>;username=<username>;password=<password>encrypt=false;
			String url = "jdbc:sqlserver://MSI\\SQLEXPRESS;databaseName=Xylo;username=Franze;password=hatdog;encrypt=false;";
			
			// Establishes a connection to the database:
			Connection connection = DriverManager.getConnection(url);
			
			// Query that gets the number of guests the user reserved for:
			PreparedStatement pstGetReservedGuests = connection.prepareStatement("SELECT SUM(numberOfGuests) AS TotalGuests FROM ReservationList WHERE Username = ? AND Date >= CONVERT(DATE, GETDATE()) GROUP BY Date;");
			pstGetReservedGuests.setString(1, username);
			
			// Executes the query:
			ResultSet rsGetReservedGuests = pstGetReservedGuests.executeQuery();
			
			// Checks if a row matches with the query:
			if (rsGetReservedGuests.next()) {
				// Gets the number of guests the user reserved for:
				yourGuests = rsGetReservedGuests.getInt("TotalGuests");		    
			}
			else {
				yourGuests = 0;
			}
		} 
		catch (Exception e) {
			e.printStackTrace();
		}	
		return yourGuests;		
	}
	
	// Method that checks if the user has a reservation; Returns 0 if no current reservation were made, otherwise, returns 1:
	public static int getReservations (HttpServletRequest request, HttpServletResponse response) {
		// Gets the signed-in username in the session; It is going to be used for displaying the reservation details of the user:
		HttpSession session = request.getSession();
		String username = (String)session.getAttribute("username");
		
		try {
			// Connect to the database. Change the url based on your computer
			// Sample format: jdbc:sqlserver://<serverName>\;databaseName=<databaseName>;username=<username>;password=<password>encrypt=false;
			String url = "jdbc:sqlserver://MSI\\SQLEXPRESS;databaseName=Xylo;username=Franze;password=hatdog;encrypt=false;";
			
			// Establishes a connection to the database:
			Connection connection = DriverManager.getConnection(url);
			
			// Query that checks if the user currently has a reservation:
			PreparedStatement pstCheckReservation = connection.prepareStatement("SELECT SUM(numberOfGuests) AS TotalGuests FROM ReservationList WHERE Username = ? AND Date >= CONVERT(DATE, GETDATE()) GROUP BY Date;");
			pstCheckReservation.setString(1, username);
			
			// Executes the query:
			ResultSet rsCheckReservation = pstCheckReservation.executeQuery();
			
			// Checks if a row matches with the query:
			if (rsCheckReservation.next()) {
				// Gets the number of row that matches with the query:
				reservations = rsCheckReservation.getRow();	   
			}
			else {
				reservations = 0;
			}
		} 
		catch (Exception e) {
			e.printStackTrace();
		}	
		return reservations;
	}
}