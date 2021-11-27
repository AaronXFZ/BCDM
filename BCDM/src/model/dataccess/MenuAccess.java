package model.dataccess;

import java.sql.Array;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
//Import the HashMap class for storing items with its ID
import java.util.HashMap;
import java.util.ArrayList;


import model.entities.User;
import model.dataccess.ConnectionFactory;

import model.entities.Offline_Item;

//This class will be use to obtain a list of items from the backend
public class MenuAccess {
//	private ArrayList<String> list = new ArrayList<String>();
//	
//	final private Integer num_of_items = 11;
//	
//	private Item item;
//	
//	public MenuAccess() throws ClassNotFoundException, SQLException {
//		
//		item = new Item();
//		
//		System.out.println(item.get_name(0));
//		
//		//Obtains list of items from back-end
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
//		
//		setMenu(connection_factory_custom_database_obj);
//	}
//	
//	private void setMenu(ConnectionFactory database_connection)  throws ClassNotFoundException, SQLException {
//		final String SQL_cmd_get_all_items = "SELECT * from menu";
//		
//		PreparedStatement stmt = database_connection.getConnection().prepareStatement(SQL_cmd_get_all_items); 
//		
//		Array array = database_connection.getConnection().createArrayOf("VARCHAR", this.list.toArray());
//		stmt.setArray(1, array);
//		ResultSet rs = stmt.executeQuery();
//	
//		
//		System.out.println("zzz = " + rs);
//	}
	


}
