package model.dataccess;

import java.sql.SQLException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import model.entities.Customer;
import model.entities.Professor;
import model.entities.Student;
import model.entities.OfflineItem;
import model.entities.OnlineItem;
import model.entities.PriceHistory;

public class ConnectionFactory_Hibernate {
	
	SessionFactory factory = new Configuration().
            configure("hibernate.cfg.xml")
            .addAnnotatedClass(Customer.class)
            .addAnnotatedClass(Student.class)
            .addAnnotatedClass(Professor.class)
            .addAnnotatedClass(OnlineItem.class)
            .addAnnotatedClass(PriceHistory.class)
            .buildSessionFactory();
	
	private ConnectionFactory_Hibernate() {}
	
	public Session getSession() throws ClassNotFoundException, SQLException
	{
		return factory.getCurrentSession();
	}
}
