/* Name: Kevin T. Vo
 * Course: CS 5800
 * Assignment: BCDM Project
 * Filename: LoginBusiness.java
 * 
 * Description: 1. This is an intermediate business layer class that will conduct all business rules including field validation and user authentication.
 *              2. Singleton  design  pattern was used to create a single instance of the classes User (in User.java) and LoginBusiness (in LoginBusiness.java) no matter how many times you need to use these classes during authentication.
 * 
 */

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
import model.dataccess.MenuAccess;

import view.ReceiptView;
import model.dataccess.OrderAccess;
import model.entities.Cart;
import model.dataccess.AccountAccess;
import model.dataccess.ItemsAccess;

public class LoginBusiness extends JFrame implements ActionListener {
	private String address = "";
	private Boolean isWebClient = false;
	private String userName;
	private String password;
	private String first_name;
	private String last_name;
	private boolean is_professor;
	private HttpServletRequest request;
	private HttpServletResponse response;
	
	
	//Status string values for try/catch functions
	//Change here will affect the entire class
	private final String inc_credentials_status = "Incorrect credentials.";
	private final String blank_username_status = "Username not informed.";
	private final String blank_password_status = "Password not informed.";
	private final String failed_connection_DB_status = "Database connection failed.";
	
	
	private static LoginBusiness singleton_instance = new LoginBusiness();
	
	private LoginBusiness()
	{ }
	
	public static LoginBusiness getSingletonObject()
	{
		
		return singleton_instance;
	}
	
	//setAttributes for Registration
	public void setAttributes(String in_userName, String in_password, String in_first_name, String in_last_name, boolean in_is_professor,
			boolean in_isWebClient, HttpServletRequest in_request, HttpServletResponse in_response)
	{
		userName = in_userName;
		password = in_password;
		first_name = in_first_name;
		last_name = in_last_name;
		is_professor = in_is_professor;
		
		isWebClient = in_isWebClient;
		request = in_request;
		response = in_response;
	}
	
	//Overload: setAttribute for Log In
	public void setAttributes(String in_userName, String in_password,
			boolean in_isWebClient, HttpServletRequest in_request, HttpServletResponse in_response)
	{
		userName = in_userName;
		password = in_password;
		
		isWebClient = in_isWebClient;
		request = in_request;
		response = in_response;
	}
	
	
	public void printall()
	{
		System.out.println(userName + " " + password + " " + isWebClient + " ");
		System.out.println(request + " " + response);
	}
	

	
	private void selectWebFunctions(int situation)
			throws ServletException, IOException {
		

		
		switch(situation)
		{
			case 0: request.setAttribute("Username", request.getParameter("username"));
					address = "/view/LoginSuccessView.jsp";
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
	
	public void validate() {
			
		
		try {
			
			if (userName.equals(""))
			{
	
				throw new MessageException(blank_username_status);
	
			}
			else if (password.equals(""))
			{
	
				throw new MessageException(blank_password_status);
	
			}
			
			//User user = new User(userName, password);
			
			User user = User.getSingletonObject();

			user.setAttributes(userName, password);
			
			
			if(!(new LoginDataAccess().verifyCredentials(user)))
			{
		
				throw new MessageException(inc_credentials_status);
	
			}
			else
			{
				if(isWebClient)
				{
							
					callWebFunctions(0);
					
				}
				else
				{
					AccountAccess account_access_obj = new AccountAccess("eric", "professor");
					
					ItemsAccess item_access_obj = new ItemsAccess();
					//item_access_obj.add_new_item_to_db("Sprite", 2.99, true);
					
					System.out.println("\nAll items = " + item_access_obj.get_all_online_items().get(0).get_isBeverage());
					
					System.out.println("\nONE items = " + item_access_obj.get_online_item("Chicken Taco").get_price());
					
					Cart cart_obj = new Cart();
					
					cart_obj.add_item("Cheeseburger");
					cart_obj.add_item("Beef Taco");
					
					MenuAccess menu_obj = new MenuAccess();
					OrderAccess orderAccess_obj = new OrderAccess(userName);
					orderAccess_obj.submit_order(cart_obj);
					
					new LoginSuccessView(userName);
					dispose();
				}
			}
		}
		catch (MessageException e) 
		{
			if(isWebClient)
			{
				if (e.getMessage().equals(blank_username_status)) {
					callWebFunctions(1);
			
				} else if (e.getMessage().equals(blank_password_status)) {

					callWebFunctions(2);
			
				} else if (e.getMessage().equals(inc_credentials_status)) {
						callWebFunctions(3);
		
			    }
			}
			else
			{
				JOptionPane.showMessageDialog (null, e.getMessage());
			}
		}
		catch(ClassNotFoundException e)
		{
			if(isWebClient)
			{
				callWebFunctions(4);
			}
			else
			{
				JOptionPane.showMessageDialog (null, e.getMessage());
			}
		}
		catch (SQLException e)
		{
			if(isWebClient)
			{
				callWebFunctions(4);
			}
			else
			{
				JOptionPane.showMessageDialog (null, e.getMessage());
			}
		}
		
		if (isWebClient)
		{

			callWebFunctions(5);
	
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	

	
}
