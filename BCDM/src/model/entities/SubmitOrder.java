package model.entities;

import java.sql.SQLException;

import model.dataccess.OrderAccess;

import model.entities.User;

public class SubmitOrder {
	public SubmitOrder() {}
	
	public SubmitOrder(Cart cart_obj)
	{
		
		try {
			
			String username = User.getSingletonObject().getUserName();
			
			OrderAccess orderAccess_obj = new OrderAccess(username);
			
			orderAccess_obj.submit_order(cart_obj);
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
