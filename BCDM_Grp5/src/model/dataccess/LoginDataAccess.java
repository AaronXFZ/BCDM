/* Name: Kevin T. Vo
 * Course: CS 5800
 * Assignment: Assignment 4 - Problem 6
 * Filename: LoginBusiness.java
 * 
 * Description: Code has been refactored with factory design pattern. It now uses the ConnectionFactory class (in ConnectionFactory.java) to simplify the server connection process through abstraction.
 *  
 * 
 */

package model.dataccess;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.entities.User;
import model.dataccess.ConnectionFactory;

public class LoginDataAccess {
	
	public Boolean verifyCredentials(User user) throws ClassNotFoundException, SQLException {

		//Creates an instance of the ConnectionFactory class to obtain the server
		//ConnectionFactory connection_factory_obj = new ConnectionFactory(user);
		

		final String non_default_URL = "jdbc:postgresql://localhost:5432/BCDM_DB";

		final String non_default_USER = "postgres";

		final String non_default_PWD = "123";
		
		//Create an instance of the ConnectionFactory class to obtain the non-defaulted server
		ConnectionFactory connection_factory_custom_database_obj = new ConnectionFactory(non_default_URL, non_default_USER, non_default_PWD);

		
		final PreparedStatement stmt = connection_factory_custom_database_obj.getConnection().prepareStatement("SELECT * FROM users WHERE username=? and password=?");

		stmt.setString(1, user.getUserName());
		stmt.setString(2, user.getPassword());

		ResultSet rs = stmt.executeQuery();
		
		return rs.next();
		
	}
	
// Deprecated - Factory Design Pattern was use to have database connectivity be simplified.
// Refer to ConnectionFactory.java for further details.
	
//	public Boolean verifyCredentials(User user) throws ClassNotFoundException, SQLException {
//
//		final String URL = "jdbc:postgresql://localhost:5432/authentication";
//
//		final String USER = "postgres";
//
//		final String PWD = "123";
//
//		Class.forName("org.postgresql.Driver");
//		Connection conection = DriverManager.getConnection(URL, USER, PWD);
//
//		final PreparedStatement stmt = conection.prepareStatement("SELECT * FROM users WHERE username=? and password=?");
//
//		stmt.setString(1, user.getUserName());
//		stmt.setString(2, user.getPassword());
//
//		ResultSet rs = stmt.executeQuery();
//		
//		System.out.println("\nRS = " + rs.next());
//
//		return rs.next();
//		
//	}

}

