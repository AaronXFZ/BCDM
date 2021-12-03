package model.dataccess;

import model.entities.*;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.Session;

import model.dataccess.ConnectionFactory_Hibernate;

//Purpose: Able to access User, Student, and Professor details from backend
public class AccountAccess {
	
	private ConnectionFactory_Hibernate conn_factory_hiber = new ConnectionFactory_Hibernate();
	
	private User user_obj;
	
	private String username;
	
	public AccountAccess() {}
	
	//Parameters: username PK to find an account
	//Look in the users table and make its ID with the professor OR student table
	public AccountAccess(String username) throws ClassNotFoundException, SQLException
	{
		Session session = conn_factory_hiber.getSession();

		session.beginTransaction();
		
		user_obj = session.get(User.class, username);
		

	}
	
	public User get_user(String username)
	{
		Session session;
		try {
			session = conn_factory_hiber.getSession();
			
			if(!session.getTransaction().isActive())
				session.beginTransaction();
			
			user_obj = session.get(User.class, username);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("\nI am from AccountAccess.java -> user_obj.get_first_name() = " 
				+ user_obj.get_first_name() + " === " + user_obj.get_discount_rate());

		return user_obj;
	}
	
	
	public void set_username(String username)
	{
		this.username = username;
	}
	
	public void update_discount(Double discount_decimal_rate)
	{
		Session session;
		try {
			session = conn_factory_hiber.getSession();
			session.beginTransaction();
			
			user_obj = session.get(User.class, username);
			
			user_obj.set_discount_rate(discount_decimal_rate);
			
			session.getTransaction().commit();
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Double get_discount_rate(String username)
	{
		System.out.println("PLM - username = " + username);
		Session session;
		
		Double discount_rate = 0.0;
		
		try {
			session = conn_factory_hiber.getSession();
			
			if(!session.getTransaction().isActive())
				session.beginTransaction();
			
			user_obj = session.get(User.class, username);
			
			discount_rate = user_obj.get_discount_rate();
					
			//user_obj = session.get(User.class, username);
//			List<User> list_user = session.createQuery("from User where username='" + username +"'").getResultList();
//			
//			if(list_user.size() > 0)
//			{
//				user_obj = list_user.get(0).getSingletonObject();
//				discount_rate = user_obj.get_discount_rate();
//				
//				System.out.println("\nI am from the MNB discount_rate = " + discount_rate);
//			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return discount_rate;
	}
	
	public User get_user_from_backend()
	{
		return user_obj;
	}
	
	public boolean is_professor()
	{
		return user_obj.get_is_professor();
	}
	
}
