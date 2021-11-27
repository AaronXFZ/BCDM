package model.dataccess;

import java.util.List;
import java.sql.SQLException;
import java.util.ArrayList;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import model.dataccess.ConnectionFactory_Hibernate;

import model.entities.OnlineItem;

public class ItemsAccess {
	
	private ConnectionFactory_Hibernate conn_factory_hiber = new ConnectionFactory_Hibernate();
	
	private List<OnlineItem> online_items;

	
	public ItemsAccess() throws ClassNotFoundException, SQLException
	{
		
		Session session = conn_factory_hiber.getSession();
	
		session.beginTransaction();

		online_items = session.createQuery("from OnlineItem").getResultList();
				
	}


	
	public OnlineItem get_online_item(String name)
	{
		for(int i = 0; i < online_items.size(); i++)
		{
			if(online_items.get(i).get_name() == name)
			{
				return online_items.get(i);
			}
		}
		
		System.out.print("\nError: Item not found on database.\n");
		return null;
	}

	public void add_new_item_to_db(String item_name, double price, boolean isBeverage) throws ClassNotFoundException, SQLException
	{
		OnlineItem new_item = new OnlineItem(item_name, price, isBeverage);

		System.out.println("new_item = " + new_item);
		//Session session = conn_factory_hiber.getSession();
		
		SessionFactory factory = new Configuration().
                configure("hibernate.cfg.xml").
                addAnnotatedClass(OnlineItem.class).
                buildSessionFactory();

		Session session = factory.getCurrentSession();
		
		session.beginTransaction();
		
		session.save(new_item);
		
		session.getTransaction().commit();
	}
	
	
	
}
