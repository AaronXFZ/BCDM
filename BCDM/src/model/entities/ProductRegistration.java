package model.entities;

import model.dataccess.ConnectionFactory_Hibernate;

import java.sql.SQLException;
import java.util.Date;

import org.hibernate.Session;

import model.dataccess.ItemsAccess;

import model.dataccess.PriceHistoryAccess;

import model.entities.PriceHistory;

public class ProductRegistration {
	ConnectionFactory_Hibernate conn_factory = new ConnectionFactory_Hibernate();
	
	
	public ProductRegistration()
	{}
	
	public ProductRegistration(String item_name, double price, boolean isBeverage)
	{
		register_product(item_name, price, isBeverage);
	}
	
	public boolean register_product(String item_name, double price, boolean isBeverage)
	{
		double old_price;
		boolean item_already_exist = false;
		
		try {
			ItemsAccess items_access_obj = new ItemsAccess(); // for checking if an item exists
			
			//if item does not exist, add it to the db
			if(items_access_obj.get_online_item(item_name) == null)
			{
				
				items_access_obj.add_new_item_to_db(item_name, price, isBeverage);
				
			}
			else
			{
				item_already_exist = true;
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return item_already_exist;
	}
	
	public boolean update_item_price(String item_name, double price, boolean isBeverage)
	{
		
		ItemsAccess items_access_obj;
		try {
			items_access_obj = new ItemsAccess();
			
			
			int in_item_id = items_access_obj.get_online_item(item_name).get_id();
			double in_price = items_access_obj.get_online_item(item_name).get_price();
			
			System.out.println("DSFJWEINGIOUJNOGNEG");
			
			Date current_date = new Date();
			
			//Adding old price to history
			PriceHistory price_hist_obj = new PriceHistory(in_item_id, item_name, in_price, current_date);
			
			PriceHistoryAccess price_hist_acess_obj = new PriceHistoryAccess(price_hist_obj);
			
			//Editing price of item
			
			Session session = conn_factory.getSession();
			
			session.beginTransaction();
			
			session.createQuery("update OnlineItem set price='"+price+"' where name='"+item_name+"'")
					                  .executeUpdate();
			session.getTransaction().commit();
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // for checking if an item exists
		
		return true;
	}
	
	
}
