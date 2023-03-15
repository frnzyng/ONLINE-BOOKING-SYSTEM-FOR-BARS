package com.infmglproject;

import jakarta.servlet.RequestDispatcher;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Servlet implementation class Reservation
 */

@WebServlet("/reservation")
public class Reservation extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	// Method that handles the user's input on the reservation form:
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Retrieves the user's input on the reservation form:
		String name = request.getParameter("name");	
		String email = request.getParameter("email");
		String contactNumber = request.getParameter("tel");
		String dateTime = request.getParameter("datetimeLocal");
		String numberOfGuests = request.getParameter("numberg");
		String numberOfTables = request.getParameter("numbert");
		String payment = request.getParameter("payment");
		
		// HttpSession is used for storing/getting specific information:
		HttpSession session = request.getSession();
		// Gets the session attribute "username"; It is used for displaying the reservation details of the user that signed in:
		String username = (String)session.getAttribute("username");
		
		// Date and time formatting; To be used to display the appropriate format:
		DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		DateTimeFormatter formatTime = DateTimeFormatter.ofPattern("h:mm a");
		String formattedDate = LocalDateTime.parse(dateTime).format(formatDate);
		String formattedTime = LocalDateTime.parse(dateTime).format(formatTime);
		
		// Gets the current date and use it for checking if the user is trying to reserve at a past date:
		LocalDate date = LocalDate.parse(formattedDate);
		LocalDate dateNow = LocalDate.now();
		
		// Connection is used to connect to the database:
		Connection connection = null;
		
		try {
			// The dispatcher is used to direct incoming web requests to different server-side components;
			RequestDispatcher dispatcher = null;

			// Connect to the database. Change the url based on your computer
			// Sample format: jdbc:sqlserver://<serverName>\;databaseName=<databaseName>;username=<username>;password=<password>encrypt=false;
			String url = "jdbc:sqlserver://MSI\\SQLEXPRESS;databaseName=Xylo;username=Franze;password=hatdog;encrypt=false;";
			
			// Establishes a connection to the database:
			connection = DriverManager.getConnection(url);
			 
			// Query that gets how many current guests and reserved tables are in the bar:
			PreparedStatement pstSumGuestsTables = connection.prepareStatement("SELECT Date, SUM(numberOfGuests) AS TotalGuests, SUM(numberOfTables) AS TotalTables FROM ReservationList WHERE Date = ? GROUP BY Date;");
			pstSumGuestsTables.setString(1, formattedDate);
			 
			// Query that checks if the user has a current reservation because it is one reservation at a time only:
			PreparedStatement pstReservationExists = connection.prepareStatement("SELECT Username FROM ReservationList WHERE Username=? AND Date >= CONVERT(DATE, GETDATE());"); //Checks if the user already reserves a table
			pstReservationExists.setString(1, username);
			  
			// Executes the queries:
			ResultSet rsSumGuestsTables = pstSumGuestsTables.executeQuery();
			ResultSet rsReservationExists = pstReservationExists.executeQuery();
			
			// Checks if fields are empty or null:
			if (name == null || name.isBlank()  || email == null || email.isBlank() || contactNumber == null || contactNumber.isBlank()) {
				request.setAttribute("fieldsEmpty", "empty");
				dispatcher = request.getRequestDispatcher("Reservation.jsp");
			}
			// Checks if the session of the user has expired:
			else if (username == null || username.isBlank()) {
				request.setAttribute("sessionExpired", "failed");
		    	dispatcher = request.getRequestDispatcher("Reservation.jsp");
		    }   	
			// Checks if the date was set on the past:
			else if (date.isBefore(dateNow)) {
				request.setAttribute("pastDate", "past");
		    	dispatcher = request.getRequestDispatcher("Reservation.jsp");
			}
			// Checks the number of current guests and tables at the bar:
			else if (rsSumGuestsTables.next()) {
				int TotalGuests = rsSumGuestsTables.getInt("TotalGuests");
				int TotalTables = rsSumGuestsTables.getInt("TotalTables");
				int numGuests = Integer.parseInt(numberOfGuests); // Gets the number of guests the user typed
			    int numTables = Integer.parseInt(numberOfTables); // Gets the number of tables the user typed
				    
			    // Take note: 200 max guests and 35 max tables
			    // Checks if the bar is fully booked:
			    if (TotalGuests + numGuests > 200 || TotalTables + numTables > 35) {
			    	request.setAttribute("reservationIsFull", "failed");
			    	dispatcher = request.getRequestDispatcher("Reservation.jsp");
	    	    }
			    // Checks if the user has a current reservation:
	    	    else if (rsReservationExists.next()) {
			    	request.setAttribute("reservationExist", "failed");
			    	dispatcher = request.getRequestDispatcher("Reservation.jsp");
			    }
	    	    else {
	    	    	// Calls the method that handles the reservation:
	    	        addReservation(request, response, connection, dispatcher, name, username, email, contactNumber, formattedDate, formattedTime, numberOfGuests, numberOfTables, payment);
	    	    }
			}
	        else if (rsReservationExists.next()) {
	        	request.setAttribute("reservationExist", "failed");
		    	dispatcher = request.getRequestDispatcher("Reservation.jsp");
	        }		     
			// Checks if the user has not booked a reservation:
		    else if (!rsReservationExists.next()) {
		    	// Calls the method that handles the reservation:
		    	addReservation(request, response, connection, dispatcher, name, username, email, contactNumber, formattedDate, formattedTime, numberOfGuests, numberOfTables, payment);
		    }
		    else {
		    	request.setAttribute("status", "failed");
		    	dispatcher = request.getRequestDispatcher("Reservation.jsp");
		    }    
		    dispatcher.forward(request, response);
		} 
	    catch (Exception e) {
	    	e.printStackTrace();
	    } 	
	}
	
	// Method that retrieves the reservation data from the table to display it:
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {		
			// Sets the following attributes to display the following reservation details of the user:
			request.setAttribute("UserReservationDetails", UserReservationDetails.getDataList(request, response)); 
			request.setAttribute("countTablesAvailable", CountGuestsTables.getTablesAvailable(request, response));
			request.setAttribute("countYourTables", CountGuestsTables.getYourTables(request, response));
			request.setAttribute("countYourGuests", CountGuestsTables.getYourGuests(request, response));
			request.setAttribute("countReservations", CountGuestsTables.getReservations(request, response));
			
			// Forwards the request to "Reservation.jsp" (The reservation page):
			request.getRequestDispatcher("Reservation.jsp").forward(request, response);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}      
	}

	// Method that inserts the reservation details to the table:
	public static void addReservation (HttpServletRequest request, HttpServletResponse response, Connection connection, RequestDispatcher dispatcher, String name, 
			String username, String email, String contactNumber, String formattedDate, String formattedTime, String numberOfGuests, String numberOfTables, String payment) {
		try {
			// Query that inserts the following values based on the user's input on the reservation form:
			PreparedStatement pstInsert = connection.prepareStatement("INSERT INTO ReservationList(Name, Username, Email, ContactNumber, Date, Time, NumberOfGuests, NumberOfTables, Payment) VALUES (?,?,?,?,?,?,?,?,?)");
			pstInsert.setString(1, name);
		    pstInsert.setString(2, username); 
		    pstInsert.setString(3, email);
		    pstInsert.setString(4, contactNumber);
		    pstInsert.setString(5, formattedDate);
		    pstInsert.setString(6, formattedTime);
		    pstInsert.setString(7, numberOfGuests);
		    pstInsert.setString(8, numberOfTables);	
		    pstInsert.setString(9, payment); 		      
	      
		    // Executes the query:
		    int rowCount = pstInsert.executeUpdate();
		    
		    // Checks if the a new row has been inserted to the table:
		    if (rowCount > 0) {
		    	request.setAttribute("status", "success");
		    }
		    else {
		    	request.setAttribute("status", "failed");
		    }
		    
		    // Set and forward the dispatcher to "Reservation.jsp":
		    dispatcher = request.getRequestDispatcher("Reservation.jsp");
		    dispatcher.forward(request, response);
   
		} 
		catch (Exception e) {
			e.printStackTrace();
		} 
		finally {
			try {
				connection.close();
			} 
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}