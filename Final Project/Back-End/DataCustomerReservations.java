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

public class DataCustomerReservations {
	String ID;
	String name;
	String username;
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
	
	public void setUsername (String username) {
		this.username = username;
	}	
	public String getUsername () {
		return username;
	}
	
	public void setEmail (String email) {
		this.email = email;
	}
	public String getEmail () {
		return email;
	}
	
	public void setContactNumber (String contactNumber) {
		this.contactNumber = contactNumber;
	}
	public String getContactNumber () {
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
	
	// Method that retrieves data from the table if view all button is clicked:
	public static List<DataCustomerReservations> getDataList(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		Connection connection = null;
		
		// Created a list to store the data retrieved from the database:
		List<DataCustomerReservations> dataList = new ArrayList<>();
		
		try {
			// Connect to the database. Change the url based on your computer
			// Sample format: jdbc:sqlserver://<serverName>\;databaseName=<databaseName>;username=<username>;password=<password>encrypt=false;
			String url = "jdbc:sqlserver://MSI\\SQLEXPRESS;databaseName=Xylo;username=Franze;password=hatdog;encrypt=false;";
			connection = DriverManager.getConnection(url);
			
			// Query that retrieves all data from the ReservationList table:
			PreparedStatement pstGetData = connection.prepareStatement("SELECT * FROM ReservationList ORDER BY Date DESC");
			
			// Executes the query:
			ResultSet rsGetData = pstGetData.executeQuery();
			
			// Iterates the rows and retrieves its data to its designated location:
			while (rsGetData.next()) {
				DataCustomerReservations data = new DataCustomerReservations();
				data.setID(rsGetData.getString("ID"));
				data.setName(rsGetData.getString("Name"));
				data.setUsername(rsGetData.getString("Username"));
				data.setEmail(rsGetData.getString("Email"));
				data.setContactNumber(rsGetData.getString("ContactNumber"));
				data.setDate(rsGetData.getString("Date"));
				data.setTime(rsGetData.getString("Time"));
				data.setNumberOfGuests(rsGetData.getString("NumberOfGuests"));
				data.setNumberOfTables(rsGetData.getString("NumberOfTables"));
				data.setPayment(rsGetData.getString("Payment"));			
				dataList.add(data);
			}
		} 
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dataList;
	}
	
	// Method that retrieves all data from the table based on the search input:
	public static List<DataCustomerReservations> getSearchList(HttpServletRequest request, HttpServletResponse response, String searchTerm) throws SQLException {
		Connection connection = null;
		
		// Created a list to store the data retrieved from the database:
		List<DataCustomerReservations> dataList = new ArrayList<>();
		
		try {
			// Connect to the database. Change the url based on your computer
			// Sample format: jdbc:sqlserver://<serverName>\;databaseName=<databaseName>;username=<username>;password=<password>encrypt=false;
			String url = "jdbc:sqlserver://MSI\\SQLEXPRESS;databaseName=Xylo;username=Franze;password=hatdog;encrypt=false;";
			
			// Establishes a connection to the database:
			connection = DriverManager.getConnection(url);
			
			// Query that retrieves all data that matches with the search input from the UserAccounts table:
			PreparedStatement pstGetData = connection.prepareStatement("SELECT * FROM ReservationList WHERE ID LIKE ? OR Name LIKE ? OR Username LIKE ? OR Email LIKE ? "
					+ "OR ContactNumber LIKE ? OR Date LIKE ? OR Time LIKE ? OR NumberOfGuests LIKE ? OR NumberOfTables LIKE ? OR Payment LIKE ?;");
			pstGetData.setString(1, searchTerm + "%");
			pstGetData.setString(2, searchTerm + "%");
			pstGetData.setString(3, searchTerm + "%");
			pstGetData.setString(4, searchTerm + "%");
			pstGetData.setString(5, searchTerm + "%");
			pstGetData.setString(6, searchTerm + "%");
			pstGetData.setString(7, searchTerm + "%");
			pstGetData.setString(8, searchTerm + "%");
			pstGetData.setString(9, searchTerm + "%");
			pstGetData.setString(10, searchTerm + "%");
			
			// Executes the query:
			ResultSet rsGetData = pstGetData.executeQuery();
			
			// Retrieves the matched data:
			while (rsGetData.next()) {
				DataCustomerReservations data = new DataCustomerReservations();
				data.setID(rsGetData.getString("ID"));
				data.setName(rsGetData.getString("Name"));
				data.setUsername(rsGetData.getString("Username"));
				data.setEmail(rsGetData.getString("Email"));
				data.setContactNumber(rsGetData.getString("ContactNumber"));
				data.setDate(rsGetData.getString("Date"));
				data.setTime(rsGetData.getString("Time"));
				data.setNumberOfGuests(rsGetData.getString("NumberOfGuests"));
				data.setNumberOfTables(rsGetData.getString("NumberOfTables"));
				data.setPayment(rsGetData.getString("Payment"));			
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