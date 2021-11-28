package model.entities;

import java.sql.SQLException;
import java.util.List;

import model.entities.ItemOrder;

import model.dataccess.ItemsAccess; //For getting the price of each item

import org.hibernate.Session;
import model.dataccess.ConnectionFactory_Hibernate;

public class Receipt {
	
	ConnectionFactory_Hibernate conn_factory = new ConnectionFactory_Hibernate();
	
	
	private String date;
	private String time;
	
	private String customer_full_name;
	
	List<ItemOrder> ordered_items;
	
	public Receipt(List<ItemOrder> item_orders)
	{
		this.ordered_items = item_orders;
		
		this.date = ordered_items.get(0).get_date();
		
		this.time = ordered_items.get(0).get_time();
		
		
	}
	

	public List<ItemOrder>get_items()
	{
		return ordered_items;
	}
	
	public int get_number_of_ordered_items()
	{
		return ordered_items.size();
	}
	
	public String get_item_name_by_index(int index)
	{
		return ordered_items.get(index).get_item_name();
	}
	
	public double get_item_price_by_index(int index) throws ClassNotFoundException, SQLException
	{
		ItemsAccess items_access_obj = new ItemsAccess();
		
		String item_name = get_item_name_by_index(index);
		
		return items_access_obj.get_online_item(item_name).get_price();
	}
	
	public String customer_first_name() throws ClassNotFoundException, SQLException
	{
		Session session = conn_factory.getSession();
		
		session.beginTransaction();
		
		String customer_username = ordered_items.get(0).get_username();
		
		User customer = session.get(User.class, customer_username);
		
		this.customer_full_name = customer.get_first_name() + " " + customer.get_last_name();
		
		return customer_full_name;
	}
	
	public String get_customer_username()
	{
		return ordered_items.get(0).get_username();
	}
	
	public String get_time()
	{
		return time;
	}
	
	public String get_date()
	{
		return date;
	}
	
}
