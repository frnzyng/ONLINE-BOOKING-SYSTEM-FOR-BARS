package com.infmglproject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class UserReservationDetails {
	String ID;
	String name;
	String email;
	String contactNumber;
	String date;
	String time;
	String numberOfGuests;
	String numberOfTables;
	String payment;
	
	public void setID (String ID) {
		this.ID = ID;
	}
	public String getID () {
		return ID;
	}
	
	public void setName (String name) {
		this.name = name;
	}	
	public String getName () {
		return name;
	}
	
	public void setEmail (String email) {
		this.email = email;
	}
	public String getEmail () {
		return email;
	}
	
	public void setContact (String contactNumber) {
		this.contactNumber = contactNumber;
	}
	public String getContact () {
		return contactNumber;
	}
	
	public void setDate (String date) {
		this.date = date;
	}
	public String getDate () {
		return date;
	}
	
	public void setTime (String time) {
		this.time = time;
	}
	public String getTime () {
		return time;
	}
	
	public void setNumberOfGuests (String numberOfGuests) {
		this.numberOfGuests = numberOfGuests;
	}
	public String getNumberOfGuests () {
		return numberOfGuests;
	}
	
	public void setNumberOfTables (String numberOfTables) {
		this.numberOfTables = numberOfTables;
	}
	public String getNumberOfTables () {
		return numberOfTables;
	}
	
	public void setPayment (String payment) {
		this.payment = payment;
	}
	public String getPayment () {
		return payment;
	}
	
	// Method that retrieves data from the table:
	public static List<UserReservationDetails> getDataList(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		Connection connection = null;
		List<UserReservationDetails> dataList = new ArrayList<>();
		
		try {
			// Gets the signed-in username and password in the session; It is going to be used for displaying the reservation details of the user:
			HttpSession session = request.getSession();
			String username = (String)session.getAttribute("username");
			
			// Connect to the database. Change the url based on your computer
			// Sample format: jdbc:sqlserver://<serverName>\;databaseName=<databaseName>;username=<username>;password=<password>encrypt=false;
			String url = "jdbc:sqlserver://MSI\\SQLEXPRESS;databaseName=Xylo;username=Franze;password=hatdog;encrypt=false;";
			
			// Establishes a connection to the database:
			connection = DriverManager.getConnection(url);
			
			// Query that retrieves the reservation details from the "ReservationList" table:
			PreparedStatement pstGetData = connection.prepareStatement("SELECT ID, Name, Email, ContactNumber, Date, Time, NumberOfGuests, NumberOfTables, Payment FROM ReservationList WHERE Username = ? ORDER BY Date DESC");
			pstGetData.setString(1, username);
			
			// Executes the query:
			ResultSet resultSet = pstGetData.executeQuery();
						
			// Iterates the rows and retrieves its data to its designated location:
			while (resultSet.next()) {
				UserReservationDetails data = new UserReservationDetails();
				data.setID(resultSet.getString("ID"));
				data.setName(resultSet.getString("Name"));
				data.setEmail(resultSet.getString("Email"));
				data.setContact(resultSet.getString("ContactNumber"));
				data.setDate(resultSet.getString("Date"));
				data.setTime(resultSet.getString("Time"));
				data.setNumberOfGuests(resultSet.getString("NumberOfGuests"));
				data.setNumberOfTables(resultSet.getString("NumberOfTables"));
				data.setPayment(resultSet.getString("Payment"));			
				dataList.add(data);
			}
		} 
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dataList;
	}
}