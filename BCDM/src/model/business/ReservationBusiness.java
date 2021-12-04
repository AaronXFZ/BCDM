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
		fullName = in_fullName;
		phoneNumber = in_phoneNumber;

		request = in_request;
		response = in_response;
		
		this.cart = cart;

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
			case 0: request.setAttribute("Username", request.getParameter("phone_number"));
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
			
			default: ;break;
		}
	}
	
	
public void validate(boolean is_online) {
			
		
		//try {
			
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

			
//		}
//		catch (MessageException e) 
//		{
//			
//			if (e.getMessage().equals(blank_username_status)) {
//				callWebFunctions(1);
//		
//			} else if (e.getMessage().equals(blank_password_status)) {
//
//				callWebFunctions(2);
//		
//			} else if (e.getMessage().equals(inc_credentials_status)) {
//					callWebFunctions(3);
//	
//		    }
//	
//
//		}
	
		


		//callWebFunctions(5);
	
		
	}
}





