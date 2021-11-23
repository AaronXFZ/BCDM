/* Name: Kevin T. Vo
 * Course: CS 5800
 * Assignment: Assignment 4 - Problem 6
 * Filename: LoginBusiness.java
 * 
 * Description: Uses factory design pattern to simplify the server connection process through abstraction. Two constructors have been made for this class (ConnectionFactory). 
 *              There's a constructor for setting up the connect for the default server and there's a constructor for doing the same but with a server of the programmer's own server.
 * 
 */

package model.dataccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.entities.User;

//This class is responsible for database connection
public class ConnectionFactory {
	
	private Connection conection;
	
	
	//Default constructor for connecting to the default database
	public ConnectionFactory() throws ClassNotFoundException, SQLException {

		final String URL = "jdbc:postgresql://localhost:5432/BCDM_DB";

		final String USER = "postgres";

		final String PWD = "123";

		initiateDatabaseConnection(URL, USER, PWD);

		
	}
	
	//Constructor for when user wishes to connect to other databases
	public ConnectionFactory(String URL, String USER, String PWD) throws ClassNotFoundException, SQLException {
		System.out.println("from ConnectonFactory " + URL);
		initiateDatabaseConnection(URL, USER, PWD);
		
	}
	
	// Sets up the database connection with the provided information:
	// User user -> client-user credentials
	// String URL -> URL of database
	// String USER -> user for accessing database
	// String PWD -> password for accessing database
	private void initiateDatabaseConnection(String URL, String USER, String PWD)
			 throws ClassNotFoundException, SQLException {
		
		Class.forName("org.postgresql.Driver");
		conection = DriverManager.getConnection(URL, USER, PWD);

		
	}
	
	public Connection getConnection()
	{
		return conection;
	}
	
	
	
	
	
}
