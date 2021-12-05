package control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.business.ReservationBusiness;

@SuppressWarnings("serial")
public class SetReservationControl extends HttpServlet  {

	
	String new_status;
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
		
	}
		
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String full_name = request.getParameter("full_name");
		String phone_number = request.getParameter("phone_number");
		new_status = request.getParameter("customer_confirmation");

		
		if(!new_status.equals("online-complete"))
		{
			ReservationBusiness reservation_business_obj = ReservationBusiness.getSingletonObject();
			
			
			reservation_business_obj.get_order_id(full_name, phone_number);
			
			reservation_business_obj.update_reserveation_status();
		}
	}
}


