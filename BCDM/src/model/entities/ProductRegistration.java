package model.entities;

import java.sql.SQLException;

import model.dataccess.ItemsAccess;

public class ProductRegistration {
	
	
	
	public ProductRegistration()
	{}
	
	public ProductRegistration(String item_name, double price, boolean isBeverage)
	{
		register_product(item_name, price, isBeverage);
	}
	
	public void register_product(String item_name, double price, boolean isBeverage)
	{
		try {
			ItemsAccess items_access_obj = new ItemsAccess();
			items_access_obj.add_new_item_to_db(item_name, price, isBeverage);
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
