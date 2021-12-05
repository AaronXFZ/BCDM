package control;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.business.ReservationBusiness;

@SuppressWarnings("serial")
public class GetReservationControl extends HttpServlet {
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
		
	}
		
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String full_name = request.getParameter("full_name");
		String phone_number = request.getParameter("phone_number");
		
		ReservationBusiness reserve_business_obj = ReservationBusiness.getSingletonObject(); 
		
		reserve_business_obj.set_full_name(full_name);
		
		reserve_business_obj.set_phone_number(phone_number);
		
		reserve_business_obj.get_order_id();
		
		reserve_business_obj.transition_to_a_reservation_view(full_name, phone_number);
		
		
	}
}
