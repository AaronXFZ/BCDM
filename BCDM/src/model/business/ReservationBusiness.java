package model.business;


import java.io.*;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.business.LoginBusiness;
import model.dataccess.LoginDataAccess;


import model.entities.MessageException;
import model.entities.User;
import view.LoginSuccessView;

import view.RegisterSucessView;

import view.ReceiptView;
import model.dataccess.OrderAccess;
import model.dataccess.ReservationAccess;
import model.dataccess.UserRegistrationAccess;
import model.entities.Cart;
import model.dataccess.AccountAccess;
import model.dataccess.ItemsAccess;

import view.ProductRegistrationView;
import view.IntelligentReportView;

import view.OrderView;
public class ReservationBusiness {
	private String address = "";
	private String fullName = "";
	private String phoneNumber = "";
	
	private Integer order_id;
	private String new_status;
	private String current_status;
	
	private HttpServletRequest request;
	private HttpServletResponse response;

	
	//Status string values for try/catch functions
	//Change here will affect the entire class
	private final String inc_credentials_status = "Incorrect credentials.";
	private final String blank_username_status = "Username not informed.";
	private final String blank_password_status = "Password not informed.";
	private final String failed_connection_DB_status = "Database connection failed.";
	
	private static ReservationBusiness singleton_instance = new ReservationBusiness();
	
	private static User user = User.getSingletonObject();
	
	private Cart cart;
	
	public static ReservationBusiness getSingletonObject()
	{
		
		return singleton_instance;
	}

	
	public void setAttributes(String in_fullName, String in_phoneNumber, Cart cart, HttpServletRequest in_request, HttpServletResponse in_response)
	{
		this.fullName = in_fullName;
		this.phoneNumber = in_phoneNumber;

		this.request = in_request;
		this.response = in_response;
		
		this.cart = cart;

	}
	
	public void setAttributes(Integer order_id, String new_status, Cart cart, HttpServletRequest in_request, HttpServletResponse in_response)
	{
		this.order_id = order_id;
		this.new_status = new_status;
		
		this.request = in_request;
		this.response = in_response;


	}
	
	public void set_phone_number(String phone_number)
	{
		this.phoneNumber = phone_number;
	}
	
	public void set_full_name(String full_name)
	{
		this.fullName = full_name;
	}
	
	public Integer get_order_id()
	{
		Integer order_id = 1;
		
		ReservationAccess reserve_access_obj = new ReservationAccess(this.fullName, this.phoneNumber);
		
		order_id = reserve_access_obj.get_order_id(this.phoneNumber);
		
		this.order_id = order_id;
		
		return order_id;
	}
	
	public Integer get_order_id(String full_name, String phone_number)
	{
		Integer order_id = 1;
		
		ReservationAccess reserve_access_obj = new ReservationAccess(full_name, phone_number);
		
		order_id = reserve_access_obj.get_order_id(phone_number);
		
		this.order_id = order_id;
		
		return order_id;
	}
	

	
	public void transition_to_a_reservation_view(String full_name, String phone_number)
	{
		this.order_id = get_order_id(full_name, phone_number);
		
		ReservationAccess reserve_access_obj = new ReservationAccess(full_name, phone_number);
		
		this.current_status = reserve_access_obj.get_reservation_status(full_name, phone_number);
		
		if(this.current_status.equals("online-pending"))
		{
			callWebFunctions(6); //set address to setReservations
			callWebFunctions(5); //transitions to setReverations
		}
		else
		{	//Transition to get reservation status page
			callWebFunctions(7); //set address to reservation status once with no input needed
			callWebFunctions(5); //transitions to setReverations
		}
	}
	
	public void update_reserveation_status()
	{
		ReservationAccess reserve_access_obj = new ReservationAccess(this.fullName, this.phoneNumber);
		this.order_id = reserve_access_obj.get_order_id(this.phoneNumber);
		reserve_access_obj.update_reservation_status(this.order_id);
	}
	
	public void update_reserveation_status(String full_name, String phone_number)
	{
		ReservationAccess reserve_access_obj = new ReservationAccess(full_name, phone_number);
		Integer current_order_id = reserve_access_obj.get_order_id(full_name, phone_number);
		reserve_access_obj.update_reservation_status(current_order_id);
		this.new_status = "online-complete";
	}
	
	private void callWebFunctions(int situation)
	{
		try {
			selectWebFunctions(situation);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private void selectWebFunctions(int situation)
			throws ServletException, IOException {
		
		switch(situation)
		{
			case 0: request.setAttribute("Phone", request.getParameter("phone_number"));
					address = "/view/ReservationView.jsp";
					break;
					
			case 1: request.setAttribute("ErrorLogin", blank_username_status);
					address = "/view/LoginView.jsp"; 
					break;
					
			case 2: request.setAttribute("ErrorLogin", blank_password_status);
					address = "/view/LoginView.jsp";
					break;
					
			case 3: request.setAttribute("ErrorLogin", inc_credentials_status);
					address = "/view/LoginView.jsp";
					break;
					
			case 4: request.setAttribute("ErrorLogin", failed_connection_DB_status);
					address = "/view/LoginView.jsp"; 
					break;
					
			case 5: RequestDispatcher rd = request.getRequestDispatcher(address);
					rd.forward(request, response);
					break;
					
			case 6: request.setAttribute("Status", this.new_status);
					address = "/view/SetStatusReservation.jsp";
					break;
					
			case 7: request.setAttribute("Status", this.new_status);
					address = "/view/FinalStatusReservation.jsp";
					break;
			
			case 8: request.setAttribute("Status", this.new_status);
					address = "/view/FindReservation.jsp";
					break;
			
			default: ;break;
		}
	}
	
	
public void validate(boolean is_online) {
			
		
		try {
			
			if (fullName.equals(""))
			{
	
				throw new MessageException(blank_username_status);
	
			}
			else if (phoneNumber.equals(""))
			{
	
				throw new MessageException(blank_password_status);
	
			}
			
			System.out.println("WIUGVBNWEUIBVIUEBIUEBVIUEBVIUEBVIUEBVIB$*B*#B*&B#&*B*IUB#GUBG");
			ReservationAccess reserve_access_obj = new ReservationAccess(this.fullName, this.phoneNumber);
			try {
				
				reserve_access_obj.submit_order(cart, is_online);
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			System.out.println("WIUGVBNWEUIBVIUEBIUEBVIUEBVIUEBVIUEBVIB$*B*#B*&B#&*B*IUB#GUBG");
			//Successfully reserved			
			//goto check status page with name and phone
			//callWebFunctions(0);

			System.out.println("\n\n\n\n\nStatus=============== " + 
					reserve_access_obj.get_reservation_status(this.fullName, this.phoneNumber));
		}
		catch (MessageException e) 
		{
			
			if (e.getMessage().equals(blank_username_status)) {
				callWebFunctions(1);
		
			} else if (e.getMessage().equals(blank_password_status)) {

				callWebFunctions(2);
		
			} else if (e.getMessage().equals(inc_credentials_status)) {
					callWebFunctions(3);
	
		    }
	

		}
	
		
		callWebFunctions(8);

		callWebFunctions(5);
	
		
	}
}





