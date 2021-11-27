package model.dataccess;

import model.entities.*;

import java.sql.SQLException;

import org.hibernate.Session;

import model.dataccess.ConnectionFactory_Hibernate;

//Purpose: Able to access User, Student, and Professor details from backend
public class AccountAccess {
	
	private ConnectionFactory_Hibernate conn_factory_hiber = new ConnectionFactory_Hibernate();
	
	private User user_obj;
	
	public AccountAccess() {}
	
	//Parameters: username PK to find an account
	//Look in the users table and make its ID with the professor OR student table
	public AccountAccess(String username, String account_type) throws ClassNotFoundException, SQLException
	{
		Session session = conn_factory_hiber.getSession();

		session.beginTransaction();
		
		user_obj = session.get(User.class, username);
		

	}
	
	public User get_user_from_backend()
	{
		return user_obj;
	}
	
}
