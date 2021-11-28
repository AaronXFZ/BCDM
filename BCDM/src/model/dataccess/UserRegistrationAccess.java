package model.dataccess;

import org.hibernate.Session;

import model.dataccess.ConnectionFactory_Hibernate;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import model.entities.User;
//import model.dataccess.ConnectionFactory;

public class UserRegistrationAccess {
	
	private ConnectionFactory_Hibernate conn_factory_hib = new ConnectionFactory_Hibernate();
	
	private static UserRegistrationAccess reg_access_singleton = new UserRegistrationAccess();
	
	public UserRegistrationAccess()
	{}
	
	public static UserRegistrationAccess get_singleton()
	{
		return reg_access_singleton;
	}
	
	public boolean registration_user(String userName, String password, String first_name, String last_name, boolean is_professor) throws ClassNotFoundException, SQLException
	{
		User temp_user = User.getSingletonObject();
				
		temp_user.setAttributes(userName, password, first_name, last_name, is_professor);
		
		Session session = conn_factory_hib.getSession();
		
		session.beginTransaction();
		
		session.save(temp_user);
		
		session.getTransaction().commit();
		
		
		Session confirm_reg_session = conn_factory_hib.getSession();
		
		confirm_reg_session.beginTransaction();
		
		User newly_registered_user = confirm_reg_session.get(User.class, userName);
	
		if(newly_registered_user.getUserName() == userName)
			return true;
		
		return false;
	
	}
//	
//	public Boolean verify_registration(User user, String realName, String role) throws ClassNotFoundException, SQLException
//	{
//		
//		final String non_default_URL = "jdbc:postgresql://localhost:5432/BCDM_DB";
//
//		final String non_default_USER = "postgres";
//
//		final String non_default_PWD = "123";
//		
//		//Create an instance of the ConnectionFactory class to obtain the non-defaulted server
//		ConnectionFactory connection_factory_custom_database_obj = new ConnectionFactory(non_default_URL, non_default_USER, non_default_PWD);
//
//		
//		PreparedStatement stmt = connection_factory_custom_database_obj.getConnection().prepareStatement("SELECT * FROM users WHERE username=?");
//
//		stmt.setString(1, user.getUserName());
//
//		ResultSet rs = stmt.executeQuery();
//		
//		//If entered username already exist in database
//		if(rs.next())
//		{
//			System.out.println("\nUsername has been taken.\n");	
//			return false;
//		}
//		
//		stmt = connection_factory_custom_database_obj.getConnection().prepareStatement("INSERT INTO users (username, password, realName, role) VALUES (?, ?, ?, ?)");
//		
//		stmt.setString(1, user.getUserName());
//		stmt.setString(2, user.getPassword());
//		stmt.setString(3, realName);
//		stmt.setString(4, role);
//		
//		rs = stmt.executeQuery();
//		
//		return rs.next();
//	}
}
