package control;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import model.business.ReservationBusiness;
import model.entities.Cart;

import model.entities.MessageException;
import model.entities.User;

@SuppressWarnings("serial")
public class ReservationControl  extends HttpServlet {
	
	private User user_obj = User.getSingletonObject();
	
	private Cart cart_obj = new Cart(user_obj);
	
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

		String fullName = request.getParameter("fullname");
		String phoneNumber = request.getParameter("phone_number");
		String[] food_item_name = request.getParameterValues("food_items");
		
		String reserve_type = request.getParameter("reserve_type");
		
		System.out.println("\n\n"+phoneNumber+" --- " + fullName + " --- " + food_item_name);
		
		ReservationBusiness reserve_business_obj = ReservationBusiness.getSingletonObject();
		
		for(int i = 0; i < food_item_name.length; i++)
		{
			System.out.println("SDFSFSF =====q3 = " + food_item_name[i]);
			cart_obj.add_item(food_item_name[i]);
		}
		
			
			
	
		
		reserve_business_obj.setAttributes(fullName, phoneNumber, cart_obj, request, response);
		
		boolean is_online = false;
		
		if(reserve_type.equals("online_reservation"))
			is_online = true;
		
		reserve_business_obj.validate(is_online);
		
	}
}
