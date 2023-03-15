package com.infmglproject;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class DataCustomerAccounts {
	String ID;
	String name;
	String username;
	String email;
	String contactNumber;
	String password;
	String birthdate;
	ByteArrayInputStream validID;
	
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
	
	public void setPassword (String password) {
		this.password = password;
	}
	public String getPassword () {
		return password;
	}
	
	public void setBirthdate (String birthdate) {
		this.birthdate = birthdate;
	}
	public String getBirthdate () {
		return birthdate;
	}
	
	public void setValidID (ByteArrayInputStream validID) {
		this.validID = validID;
	}
	public ByteArrayInputStream getValidID () {
		return validID;
	}
	
	// Method that retrieves all data from the table if view all button is clicked:
	public static List<DataCustomerAccounts> getDataList(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		Connection connection = null;
		
		// Created a list to store the data retrieved from the database:
		List<DataCustomerAccounts> dataList = new ArrayList<>();
		
		try {
			// Connect to the database. Change the url based on your computer
			// Sample format: jdbc:sqlserver://<serverName>\;databaseName=<databaseName>;username=<username>;password=<password>encrypt=false;
			String url = "jdbc:sqlserver://MSI\\SQLEXPRESS;databaseName=Xylo;username=Franze;password=hatdog;encrypt=false;";
			
			// Establishes a connection to the database:
			connection = DriverManager.getConnection(url);
			
			// Query that retrieves all data from the UserAccounts table:
			PreparedStatement pstGetData = connection.prepareStatement("SELECT ID, FullName, Username, Email, ContactNumber, Password, Birthdate, ValidID FROM UserAccounts");
			
			// Executes the query:
			ResultSet rsGetData = pstGetData.executeQuery();
			
			// Iterates the rows and retrieves its data to its designated location:
			while (rsGetData.next()) {
				DataCustomerAccounts data = new DataCustomerAccounts();
				data.setID(rsGetData.getString("ID"));
				data.setName(rsGetData.getString("FullName"));
				data.setUsername(rsGetData.getString("Username"));
				data.setEmail(rsGetData.getString("Email"));
				data.setContactNumber(rsGetData.getString("ContactNumber"));
				data.setPassword(rsGetData.getString("Password"));
				data.setBirthdate(rsGetData.getString("Birthdate"));
				InputStream validIDStream = rsGetData.getBinaryStream("ValidID");
				
				// Processes the valid id image:
				ByteArrayInputStream bais = new ByteArrayInputStream(validIDStream.readAllBytes());
				data.setValidID(bais);
				
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
	public static List<DataCustomerAccounts> getSearchList(HttpServletRequest request, HttpServletResponse response, String searchTerm) throws SQLException {
		Connection connection = null;
		
		// Created a list to store the data retrieved from the database:
		List<DataCustomerAccounts> dataList = new ArrayList<>();
		
		try {
			// Connect to the database. Change the url based on your computer
			// Sample format: jdbc:sqlserver://<serverName>\;databaseName=<databaseName>;username=<username>;password=<password>encrypt=false;
			String url = "jdbc:sqlserver://MSI\\SQLEXPRESS;databaseName=Xylo;username=Franze;password=hatdog;encrypt=false;";
			
			// Establishes a connection to the database:
			connection = DriverManager.getConnection(url);
			
			// Query that retrieves all data that matches with the search input from the UserAccounts table:
			PreparedStatement pstGetData = connection.prepareStatement("SELECT * FROM UserAccounts WHERE ID LIKE ? OR FullName LIKE ? OR Username LIKE ? OR Email LIKE ? OR ContactNumber LIKE ? OR Password LIKE ? OR Birthdate LIKE ?;");
			pstGetData.setString(1, searchTerm + "%");
			pstGetData.setString(2, searchTerm + "%");
			pstGetData.setString(3, searchTerm + "%");
			pstGetData.setString(4, searchTerm + "%");
			pstGetData.setString(5, searchTerm + "%");
			pstGetData.setString(6, searchTerm + "%");
			pstGetData.setString(7, searchTerm + "%");
			
			// Executes the query:
			ResultSet rsGetData = pstGetData.executeQuery();
			
			// Retrieves the matched data:
			while (rsGetData.next()) {
				DataCustomerAccounts data = new DataCustomerAccounts();
				data.setID(rsGetData.getString("ID"));
				data.setName(rsGetData.getString("FullName"));
				data.setUsername(rsGetData.getString("Username"));
				data.setEmail(rsGetData.getString("Email"));
				data.setContactNumber(rsGetData.getString("ContactNumber"));
				data.setPassword(rsGetData.getString("Password"));
				data.setBirthdate(rsGetData.getString("Birthdate"));
				InputStream validIDStream = rsGetData.getBinaryStream("ValidID");
				
				// Processes the valid id image:
				ByteArrayInputStream bais = new ByteArrayInputStream(validIDStream.readAllBytes());
				data.setValidID(bais);
				
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