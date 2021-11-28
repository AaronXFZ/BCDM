package model.entities;

import java.util.List;
import java.sql.SQLException;
import java.util.ArrayList;

import model.dataccess.ItemsAccess;

public class Cart {
	private List<String> cart = new ArrayList<String>();
	
	private User user_obj;
	
	public Cart(User user_obj) 
	{ 
		this.user_obj = user_obj;
	}
	
	
	public void add_item(String item_name)
	{
		//This implementation has no item quantities
		if(!cart.contains(item_name))
			cart.add(item_name);
	}
	
	public double get_subtotal() throws ClassNotFoundException, SQLException
	{
		ItemsAccess items_access_obj = new ItemsAccess();
		
		double subtotal = 0.0;
		
		for(int i = 0; i < items_access_obj.get_all_online_items().size(); i++)
		{
			String name_of_item = items_access_obj.get_all_online_items().get(i).get_name();
			
			if(cart.contains(name_of_item))
				subtotal += items_access_obj.get_all_online_items().get(i).get_price();
		}
		
		return subtotal;
	}
	
	public double get_discounted_total() throws ClassNotFoundException, SQLException
	{
		
		double subtotal = get_subtotal();
		
		return user_obj.get_discounted_price(subtotal);
	}
	
	public double get_discount_rate()
	{
		return user_obj.get_discount_rate();
	}
	
	public double get_price_by_item_id(int item_id) throws ClassNotFoundException, SQLException
	{
		ItemsAccess items_access_obj = new ItemsAccess();
		
		return items_access_obj.get_online_item_by_id(item_id).get_price();
	}
	
	public double get_price_by_item_name(String item_name) throws ClassNotFoundException, SQLException
	{
		ItemsAccess items_access_obj = new ItemsAccess();
		
		return items_access_obj.get_online_item(item_name).get_price();
	}
	
	public List<String> get_items()
	{
		return cart;
	}

	public void remove_item(String item_name)
	{
		cart.remove(item_name);
	}
	
	
	public void clear_cart()
	{
		cart.clear();
	}
	
	
	
	
}
