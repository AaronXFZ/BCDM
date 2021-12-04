package model.entities;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.hibernate.Session;

import model.dataccess.ConnectionFactory_Hibernate;

import model.dataccess.OrderAccess;

public class OrderManagement {
	
	ConnectionFactory_Hibernate conn_fact = new ConnectionFactory_Hibernate();
	
	private List<ItemOrder> all_items = new ArrayList();
	private HashMap<Integer, Boolean> visited_order_id = new HashMap<Integer, Boolean>();
	private List<String> list_of_all_orders_str = new ArrayList();
	public OrderManagement() {
		all_items = new OrderAccess("").get_ALL_order_items_orders();
	}
	
	public String print_ordered_items(int cap)
	{
		String results = "";
		
		if(cap < 0 && cap > all_items.size())
			cap = all_items.size();
		
		for(int i = 0 ; i < cap; i++)
		{
			ItemOrder o = all_items.get(i);
			if(visited_order_id.get(o.get_order_id()) == null)
			{
				results += "ID: " + i +" "+ o.get_username() + " " + o.get_date() + " " + o.get_time() + "\n";
				visited_order_id.put(o.get_order_id(), true);
			}
		}
		
		return results;
		
	}
	
	
	public List<String> get_list_of_all_orders_str()
	{
		String results = "";
		
		for(int i = 0 ; i < all_items.size(); i++)
		{
			ItemOrder o = all_items.get(i);
			if(visited_order_id.get(o.get_order_id()) == null)
			{
				results += "     "+ o.get_username() + "     " + o.get_date() + "     " + o.get_time() + "\n";
				
				visited_order_id.put(o.get_order_id(), true);
				
				list_of_all_orders_str.add(results);
				
				results = "";
			}
		}
		
		return list_of_all_orders_str;
		
	}
	
	public List<ItemOrder> get_all_items_list()
	{
		return all_items;
	}
	
	public Integer get_list_size()
	{
		return all_items.size();
	}
	
	public void delete_order_by_index(int index)
	{
		if(index < 0 && index > all_items.size())
			return;
		
		try {
			Session session = conn_fact.getSession();
			
			session.beginTransaction();
			
			session.createQuery("delete from ItemOrder where order_id='" + all_items.get(index).get_order_id() + "'").executeUpdate();
			
			session.getTransaction().commit();
			
			//session.close();
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
}
