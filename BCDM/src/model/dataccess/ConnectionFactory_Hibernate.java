package model.dataccess;

import java.sql.SQLException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import model.entities.User;
import model.entities.Customer;
import model.entities.Professor;
import model.entities.Student;
import model.entities.OfflineItem;
import model.entities.OnlineItem;
import model.entities.PriceHistory;
import model.entities.ItemOrder;

public class ConnectionFactory_Hibernate {
	
	SessionFactory factory = new Configuration().
            configure("hibernate.cfg.xml")
            .addAnnotatedClass(User.class)
            .addAnnotatedClass(Customer.class)
            .addAnnotatedClass(Student.class)
            .addAnnotatedClass(Professor.class)
            .addAnnotatedClass(OnlineItem.class)
            .addAnnotatedClass(PriceHistory.class)
            .addAnnotatedClass(ItemOrder.class)
            .buildSessionFactory();
	
	public ConnectionFactory_Hibernate() {}
	
	public SessionFactory get_factory()
	{
		return factory;
	}
	public Session getSession() throws ClassNotFoundException, SQLException
	{
		return factory.getCurrentSession();
	}
	
	public void restart_session()
	{
		factory.close();
		
		factory = new Configuration().
	            configure("hibernate.cfg.xml")
	            .addAnnotatedClass(User.class)
	            .addAnnotatedClass(Customer.class)
	            .addAnnotatedClass(Student.class)
	            .addAnnotatedClass(Professor.class)
	            .addAnnotatedClass(OnlineItem.class)
	            .addAnnotatedClass(PriceHistory.class)
	            .addAnnotatedClass(ItemOrder.class)
	            .buildSessionFactory();
	}
}
