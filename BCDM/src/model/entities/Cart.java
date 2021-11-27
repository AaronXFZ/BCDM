package model.entities;

import java.util.List;
import java.util.ArrayList;

public class Cart {
	private List<String> cart = new ArrayList<String>();
	
	public Cart() {}
	
	
	public void add_item(String item_name)
	{
		//This implementation has no item quantities
		if(!cart.contains(item_name))
			cart.add(item_name);
		
	}
	
	public List<String> get_items()
	{
		return cart;
	}

	public void remove_item(String item_name)
	{
		cart.remove(item_name);
	}
	
	
	public void clear_cart(String item_name)
	{
		cart.clear();
	}
	
	
	
	
}
