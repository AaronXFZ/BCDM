package model.dataccess;

import model.dataccess.ConnectionFactory_Hibernate;


import org.hibernate.Session;

import java.util.List;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.text.SimpleDateFormat;

import model.entities.Cart;
import model.entities.User;
import model.dataccess.ConnectionFactory;

import model.entities.ItemOrder;

import model.entities.Receipt;

/*
Have an class for Orders
Parameter for Class Order:
	1) Customer's Username
	2) Items: Array of { new Pair <Integer, Integer> (Item_ID, Quantity) }
*/
public class OrderAccess {
	
	private ConnectionFactory_Hibernate conn_factory = new ConnectionFactory_Hibernate();
	
	private String username;
	
	private List<ItemOrder> ordered_items = new ArrayList();
	
	private List<ItemOrder> ordered = new ArrayList();
	
	public OrderAccess(String username)
	{
		this.username = username;
	}
	
	public List<ItemOrder> get_ALL_order_items_orders()
	{
		Session session;
		try {
			session = conn_factory.getSession();
			session.beginTransaction();
			
			List<ItemOrder>all_ordered_items = session.createQuery("from ItemOrder").getResultList();
			
			return all_ordered_items;
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	public void submit_order(Cart cart) throws ClassNotFoundException, SQLException
	{
		Date date = new Date(); // This object contains the current date value
		
		SimpleDateFormat formatter_date = new SimpleDateFormat("dd-MM-yyyy");
		SimpleDateFormat formatter_time = new SimpleDateFormat("HH:mm:ss");
		
		final String current_date = formatter_date.format(date);
		
		final String current_time = formatter_time.format(date);
		
		List<String> list_of_items = cart.get_items();
		
		Session session = conn_factory.getSession();
		
		session.beginTransaction();
		
        String order_id_hashvalue = username+current_date+current_time;
		
		int order_id = order_id_hashvalue.hashCode();
		
		for(int i = 0; i < cart.get_items().size(); i++)
		{
			ItemOrder item_order_obj = new ItemOrder(username, current_date, current_time, cart.get_items().get(i), order_id) ;
			
			ordered_items.add(item_order_obj);
			
			session.save(item_order_obj);
		}
		
		session.getTransaction().commit();
		
		
		Receipt receipt_obj = new Receipt(ordered_items);
		
		
		
		
		//Clears the cart once order is submitted
		cart.clear_cart();
		
	}
	
	
	
	//To use this function, it has to call the get all orders from the table which is the get all orders function within this class
	public void delete_item_from_order_by_item_id(int order_id) throws ClassNotFoundException, SQLException 
	{
		Session session = conn_factory.getSession();
		
		session.beginTransaction();
		
		session.createQuery("delete from ItemOrder where order_id='" + order_id + "'")
		       .executeUpdate();
		
		session.close();
	}
	
	//To use this function, it has to call the get all orders from the table which is the get all orders function within this class
	public void delete_entire_order_by_id(int id) throws ClassNotFoundException, SQLException 
	{
		Session session = conn_factory.getSession();
		
		session.beginTransaction();
		
		session.createQuery("delete from ItemOrder where id='" + id + "'")
		       .executeUpdate();
		
		session.close();
		
	}

	
//	
//	public Integer submitOrder(User user) throws ClassNotFoundException, SQLException {
//
//		//Creates an instance of the ConnectionFactory class to obtain the server
//		//ConnectionFactory connection_factory_obj = new ConnectionFactory(user);
//		
//
//		final String non_default_URL = "jdbc:postgresql://localhost:5432/BCDM_DB";
//
//		final String non_default_USER = "postgres";
//
//		final String non_default_PWD = "123";
//		
//
//		
//		//Create an instance of the ConnectionFactory class to obtain the non-defaulted server
//		ConnectionFactory connection_factory_custom_database_obj = new ConnectionFactory(non_default_URL, non_default_USER, non_default_PWD);
//
//		Date date = new Date(); // This object contains the current date value
//		SimpleDateFormat formatter_date = new SimpleDateFormat("dd-MM-yyyy");
//		SimpleDateFormat formatter_time = new SimpleDateFormat("HH:mm:ss");
//
//		final String SQL_cmd_insert_order = "INSERT INTO order_mgmt (username, date, time) VALUES (?,?,?)";
//		
//		PreparedStatement stmt = connection_factory_custom_database_obj.getConnection().prepareStatement(SQL_cmd_insert_order);
//		
//		
//		final String current_date = formatter_date.format(date);
//		
//		final String current_time = formatter_time.format(date);
//
//		stmt.setString(1, user.getUserName());
//		stmt.setString(2, current_date);
//		stmt.setString(3, current_time);
//
//		Integer rs_status = stmt.executeUpdate();
//		
//		if(rs_status == 1)
//		{
//			final String SQL_cmd_insert_items = "INSERT INTO order_mgmt (Order_ID, Item_ID) VALUES (?,?,?)";
//		}
//
//		return rs_status;
//		
//	}
	

	
}
