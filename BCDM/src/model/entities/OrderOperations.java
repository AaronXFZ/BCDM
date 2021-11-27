package model.entities;

import java.util.HashMap; //For storing order

import model.entities.Offline_Item;


// NOTE: Make GUI in this for tomorrow.

//This Class makes an order then submits it
public class OrderOperations {

	private HashMap<String, Integer> carted_items = new HashMap<String, Integer>();
	
	private Offline_Item items_obj = Offline_Item.get_singleton();
	
	private double subtotal;
	
	public OrderOperations()
	{
		subtotal = 0.00;

	}
	
	
	//This function will add/edit an item onto the cart based on the parameter Integer quantity
	public void add_edit_item(Integer item_ID, Integer quantity)
	{
		if(quantity < 0)
		{
			System.out.println("\nError: add_edit_item(Integer, Integer) in the Order class does not take a negative qauntity.\n");
			return;
		}
		
		final String item_name = items_obj.get_name(item_ID);
		
		if (quantity == 0)
		{
			delete_item(item_ID);
		}
		else
		{
			carted_items.put(item_name, quantity);
		}	

		
	}
	
	//Deletes an item from cart completely
	public void delete_item(Integer Item_ID)
	{
		final String item_name = items_obj.get_name(Item_ID);
		
		carted_items.remove(item_name);

	}
	
	//Gets quantity of specific item in cart based on item's ID
	public Integer get_item_quantity(Integer Item_ID)
	{
		final String item_name = items_obj.get_name(Item_ID);
		
		return carted_items.get(item_name);
	}
	
	//Empties entire cart
	public void clear_cart()
	{
		carted_items.clear();
	}
	
	
	//Get total cart count
	public Integer count_items_cart()
	{
		return carted_items.size();
	}
	
	public Double get_price(Integer item_ID)
	{
		final String item_name = items_obj.get_name(item_ID);
		
		double price =  items_obj.get_price(item_ID) * carted_items.get(item_name);
		
		return price;
			
	}
	

	private void update_subtotal()
	{
		subtotal = 0.00;
		
		if(carted_items.size() <= 0)
			return;
		
		for(String str_key : carted_items.keySet()) 
		{	
			Integer item_ID = Integer.parseInt(str_key);
		
			Double item_price = items_obj.get_price(item_ID);
			
			subtotal = item_price * carted_items.get(str_key);
		}
	}
	
	public double get_subtotal()
	{
		update_subtotal();
		
		return subtotal;
	}
	
	

}
