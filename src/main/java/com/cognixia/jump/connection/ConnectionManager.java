package com.cognixia.jump.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConnectionManager {
	
	private static Connection connection = null;
	
	// Windows: jdbc:mysql://localhost:3306/crud_db
	// Mac: jdbc:mysql://localhost:3306/crud_db?serverTimezone=EST5EDT
	private static final String URL = "jdbc:mysql://localhost:3306/library";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "root"; // Windows: root
													   // Mac: Root@123
	
	private static void makeConnection() {
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			
		} catch(SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static Connection getConnection() {
		
		if(connection == null) {
			makeConnection();
		}
		
		return connection;
	}

}
