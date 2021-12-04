package control;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import model.dataccess.OrderAccess;
import model.entities.Cart;

import model.entities.MessageException;
import model.entities.User;
public class ReservationControl {
	
	private User user_obj = User.getSingletonObject();
	
	private Cart cart_obj = new Cart(user_obj);
	
	private OrderAccess order_access_obj = new OrderAccess(user_obj.getUserName());
	
	private String cart = ""; //holds totals all the items
	
	private boolean is_submitted = false;
	
	public ReservationControl()
	{
		is_submitted = false;
	}
		protected void doGet(HttpServletRequest req, HttpServletResponse resp)
				throws ServletException, IOException {
			doPost(req, resp);
			
		}
		
		protected void doPost(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {

			String food_item_name = request.getParameter("food_item");
			
			cart_obj.add_item(food_item_name);
			
			if(is_submitted == true)
			{
				try {
					order_access_obj.submit_order(cart_obj);
				} catch (ClassNotFoundException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		
		
	}
}
