package model.entities;

import model.dataccess.ConnectionFactory_Hibernate;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.hibernate.Session;

import model.dataccess.ReceiptAccess;
import model.dataccess.ItemsAccess;


//Show all or by user
//filters by date
public class Intelligent_Report {
	
	ConnectionFactory_Hibernate conn_factory = new ConnectionFactory_Hibernate();
	
	private double total_revenue_all;
	private double total_revenue_username;
	
	private Date lowerbnd_date;
	private Date upperbnd_date;
	private String username;
	
	private String name_most_profitable_item_all;
	private int quantity_most_profitable_item_all = -1;
	
	private String name_most_profitable_item_by_username;
	private int quantity_most_profitable_item_by_username = -1;


	private HashMap<String, Integer> items_record = new HashMap<String, Integer>();
	
	private HashMap<String, Integer> items_record_by_username = new HashMap<String, Integer>();
	
	private HashMap<String, Integer> S = new HashMap<String, Integer>();
	
	//ReceiptAccess receipt_access_obj = new ReceiptAccess();
	
	public Intelligent_Report() {}
	
	public void set_lowerbnd_date(Date lowerbnd_date)
    {
    	this.lowerbnd_date = lowerbnd_date;
    }
    
	public void set_upperbnd_date(Date upperbnd_date)
    {
    	this.upperbnd_date = upperbnd_date;
    }
    
    
	public void set_username(String username)
    {
    	this.username = username;
    }
    
    public void record_ordered_items_onto_map() throws ClassNotFoundException, SQLException
    {
    	try
		{
	    	items_record.clear();
	    	
	    	Session session = conn_factory.getSession();
	    	session.beginTransaction();
	    	
	    	List<ItemOrder> orders = session.createQuery("from ItemOrder").getResultList();
	    	
	    	for(int i = 0; i < orders.size(); i++)
	    	{
	    		String item_name = orders.get(i).get_item_name();
	    		
	    		if (items_record.get(item_name) == null)
	    		{
	    			Integer first = 1;
	    			items_record.put(item_name, first);
	    		}
	    		else
	    		{
	    			int new_quantity = items_record.get(item_name) + 1;
	    			
	    			items_record.put(item_name, new_quantity);
	    			
	    		}
	    		
	    		if(items_record.get(item_name) > quantity_most_profitable_item_all)
	    		{
	    			quantity_most_profitable_item_all = items_record.get(item_name);
	    			name_most_profitable_item_all = item_name;
	    		}
	    		
	    	}
    	
   
    	} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		finally
		{
			conn_factory.get_factory().close();
		}
    }
    
//    //u -> username
//    //s -> startDate
//    //f -> finalDate
//    public void calculateDate(String username, String startDate, String finalDate) throws ClassNotFoundException, SQLException
//    {
//    	items_record_by_username.clear();
//    	
//    	Session session = conn_factory.getSession();
//    	session.beginTransaction();
//    	
//    	List<ItemOrder> orders = session.createQuery("from ItemOrder").getResultList();
//    	
//    	final String u = username;
//    	final String s = startDate;
//    	final String f = finalDate;
//
//    	
//    }
    
    
    //
    public void record_ordered_items_onto_map_by_username(String userName) throws ClassNotFoundException, SQLException
    {
    	System.out.println("userNameuserNameuserNameuserName userName = " + userName );
    	try
		{
    		items_record_by_username.clear();
	    	
	    	Session session = conn_factory.getSession();
	    	session.beginTransaction();
	    	
	    	List<ItemOrder> orders = session.createQuery("from ItemOrder").getResultList();
	    	
	    	for(int i = 0; i < orders.size(); i++)
	    	{
	    		
	    		System.out.println(orders.get(i).get_username() + " ====== " + userName + " ^^^ " +
	    				orders.get(i).get_username().equals(userName));
	    		
	    		
	    		if(orders.get(i).get_username().equals(userName))
	    		{
		    		String item_name = orders.get(i).get_item_name();
		    		
		    		System.out.println(item_name + " --- = " + orders.get(i).get_item_name() );
		    		
		    		if (items_record_by_username.get(item_name) == null)
		    		{
		    			Integer first = 1;
		    			items_record_by_username.put(item_name, first);
		    		}
		    		else
		    		{
		    			Integer new_quantity = items_record_by_username.get(item_name) + 1;
		    			
		    			items_record_by_username.put(item_name, new_quantity);
		    			
		    		}
		    		
		    		if(items_record_by_username.get(item_name) > quantity_most_profitable_item_by_username)
		    		{
		    			quantity_most_profitable_item_by_username = items_record_by_username.get(item_name);
		    			name_most_profitable_item_all = item_name;
		    		}
	    		}
	    		
	    	}
    	
   
    	} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		finally
		{
			conn_factory.get_factory().close();
		}
    }
    
    
    
//    public void record_ordered_items_onto_map_by_username(String username) throws ClassNotFoundException, SQLException
//    {
//    	try
//		{
//	    	items_record_by_username.clear();
//	    	
//	    	Session session = conn_factory.getSession();
//	    	session.beginTransaction();
//	    	
//	    	List<ItemOrder> orders = session.createQuery("from ItemOrder where username = 'eric'").getResultList();
//	    	
//	    	
//	    	for(int i = 0; i < orders.size(); i++)
//	    	{
//	    		System.out.println("AAA!@#$$ = " + orders.get(i).get_username());
//	    		
//	    		String item_name = orders.get(i).get_item_name();	
//	    		System.out.println(i + " = ORDER ==== fddf === " + orders.get(i).get_item_name());
//	    		if (items_record_by_username.get(item_name) == null)
//	    		{
//	    			Integer first = 1;
//	    			
//	    			items_record_by_username.put(item_name, first);
//	    			
//	    			System.out.println("1st USERNAME ISERTION ==== " + items_record_by_username.get(item_name));
//	    		}
//	    		else
//	    		{
//	    			Integer new_quantity = items_record_by_username.get(item_name) + 1;
//	    			
//	    			items_record_by_username.put(item_name, new_quantity);
//	    			
//	    			System.out.println("2nd USERNAME ISERTION ==== " + items_record_by_username.get(item_name));
//
//	    		}
//	    		
//	    		
//	    		if(items_record_by_username.get(item_name) > quantity_most_profitable_item_by_username)
//	    		{
//	    			quantity_most_profitable_item_by_username = items_record.get(item_name);
//	    			name_most_profitable_item_by_username = item_name;
//	    		}
//	    		
//	    	}
//	    	
//		} 
//		catch (Exception e) 
//		{
//			e.printStackTrace();
//		}
//		finally
//		{
//			conn_factory.get_factory().close();
//		}
//	}
//    
    
    public int get_quantity_by_item_name(String item_name)
    {
    	return items_record.get(item_name);
    }
    
    public HashMap<String, Integer> get_all_items_ordered()
    {
    	return items_record;
    }
    
    public HashMap<String, Integer> get_all_items_ordered_by_username()
    {
    	return items_record_by_username;
    }
    
    public String get_most_sold_item_all()
    {
    	return name_most_profitable_item_all + ": " + quantity_most_profitable_item_all;
    }
    
    public String get_most_sold_item_by_username()
    {
    	return name_most_profitable_item_by_username + ": " + quantity_most_profitable_item_by_username;
    }
    
    public double get_total_revenue_all()
    {	total_revenue_all = 0.0;
        	
	    try {
			
			// items_record -> contains all items ordered by quantity
			// item_price_map -> contains every item's price 
			
			ItemsAccess items_access_obj = new ItemsAccess();
			
			HashMap<String, Double> item_price_map = items_access_obj.get_online_item_hash_map();
			
			//Loop through all ordered items hashmap (items_record)
			//Insert items_record's key (aka item's name) as a key into item_price_map hashmap to get price
			for (String item_name_key : items_record.keySet()) {
	            System.out.println(item_name_key + " -Z- " + item_price_map.get(item_name_key));
				if(item_price_map.get(item_name_key) != null)
				{
					Double item_price = item_price_map.get(item_name_key);
					
					total_revenue_username += item_price;
				}
	        }
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	return total_revenue_all;
    	
    }
    
    public double get_total_revenue_username()
    {	total_revenue_username = 0.0;
        	
    	try {
    		
    		// items_record -> contains all items ordered by quantity
    		// item_price_map -> contains every item's price 
    		
    		ItemsAccess items_access_obj = new ItemsAccess();
			
    		HashMap<String, Double> item_price_map = items_access_obj.get_online_item_hash_map();
			
    		//Loop through all ordered items hashmap (items_record)
    		//Insert items_record's key (aka item's name) as a key into item_price_map hashmap to get price
    		for (String item_name_key : items_record_by_username.keySet()) {
                
    			Double item_price = item_price_map.get(item_name_key);
    			
    			total_revenue_username += item_price;
            }
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	return total_revenue_username;
    	
    }
   
	
}
