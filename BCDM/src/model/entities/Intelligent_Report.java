package model.entities;

import model.dataccess.ConnectionFactory_Hibernate;

import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.hibernate.Session;

import model.dataccess.ReceiptAccess;

//Show all or by user
//filters by date
public class Intelligent_Report {
	
	ConnectionFactory_Hibernate conn_factory = new ConnectionFactory_Hibernate();
	
	private double total_revenue;
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
	
	public Intelligent_Report() {
		
	}
	
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
    	items_record.clear();
    	
    	Session session = conn_factory.getSession();
    	session.beginTransaction();
    	
    	List<ItemOrder> orders = session.createQuery("from ItemOrder").getResultList();
    	
    	for(int i = 0; i < orders.size(); i++)
    	{
    		String item_name = orders.get(i).get_item_name();
    		
    		if (items_record.get(item_name) == null)
    		{
    			items_record.put(item_name, 1);
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
    
    public void record_ordered_items_onto_map_by_username(String username) throws ClassNotFoundException, SQLException
    {
    	items_record_by_username.clear();
    	
    	Session session = conn_factory.getSession();
    	session.beginTransaction();
    	
    	List<ItemOrder> orders = session.createQuery("from ItemOrder where username = '" + username + "'").getResultList();
    	
    	for(int i = 0; i < orders.size(); i++)
    	{
    		String item_name = orders.get(i).get_item_name();	
    		
    		if (items_record_by_username.get(item_name) == null)
    		{
    			items_record_by_username.put(item_name, 1);
    		}
    		else
    		{
    			int new_quantity = items_record_by_username.get(item_name) + 1;
    			
    			items_record_by_username.put(item_name, new_quantity);
    			
    		}
    		
    		if(items_record.get(item_name) > quantity_most_profitable_item_by_username)
    		{
    			quantity_most_profitable_item_by_username = items_record.get(item_name);
    			name_most_profitable_item_by_username = item_name;
    		}
    		
    	}
    }
    
    public int get_quantity_by_item_name(String item_name)
    {
    	return items_record.get(item_name);
    }
    
    public HashMap<String, Integer> get_all_items_ordered()
    {
    	return items_record;
    }
    
    public String get_most_sold_item_all()
    {
    	return name_most_profitable_item_all + ": " + quantity_most_profitable_item_all;
    }
    
    public String get_most_sold_item_by_username()
    {
    	return name_most_profitable_item_by_username + ": " + quantity_most_profitable_item_by_username;
    }
	
}
