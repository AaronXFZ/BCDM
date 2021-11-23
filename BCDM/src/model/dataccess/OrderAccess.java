package model.dataccess;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.Date;

import java.text.SimpleDateFormat;

import model.entities.User;
import model.dataccess.ConnectionFactory;
/*
Have an class for Orders
Parameter for Class Order:
	1) Customer's Username
	2) Items: Array of { new Pair <Integer, Integer> (Item_ID, Quantity) }
*/
public class OrderAccess {
	private String username;
	
	public OrderAccess(String username)
	{
		this.username = username;
	}
	
	public Integer submitOrder(User user) throws ClassNotFoundException, SQLException {

		//Creates an instance of the ConnectionFactory class to obtain the server
		//ConnectionFactory connection_factory_obj = new ConnectionFactory(user);
		

		final String non_default_URL = "jdbc:postgresql://localhost:5432/BCDM_DB";

		final String non_default_USER = "postgres";

		final String non_default_PWD = "123";
		

		
		//Create an instance of the ConnectionFactory class to obtain the non-defaulted server
		ConnectionFactory connection_factory_custom_database_obj = new ConnectionFactory(user, non_default_URL, non_default_USER, non_default_PWD);

		System.out.println("HELLOOO");
		Date date = new Date(); // This object contains the current date value
		SimpleDateFormat formatter_date = new SimpleDateFormat("dd-MM-yyyy");
		SimpleDateFormat formatter_time = new SimpleDateFormat("HH:mm:ss");

		final String SQL_cmd_insert_order = "INSERT INTO order_mgmt (username, date, time) VALUES (?,?,?)";
		
		PreparedStatement stmt = connection_factory_custom_database_obj.getConnection().prepareStatement(SQL_cmd_insert_order);
		
		
		final String current_date = formatter_date.format(date);
		
		final String current_time = formatter_time.format(date);

		stmt.setString(1, user.getUserName());
		stmt.setString(2, current_date);
		stmt.setString(3, current_time);

		Integer rs_status = stmt.executeUpdate();
		
		if(rs_status == 1)
		{
			//final String SQL_cmd_insert_items = "INSERT INTO order_mgmt (Order_ID, Item_ID) VALUES (?,?,?)";
		}

		return rs_status;
		
	}
	
	public Integer get_item_by_ID(Integer Item_ID)
	{
		
	}
	
}
