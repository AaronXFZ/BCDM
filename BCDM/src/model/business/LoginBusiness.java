/* Name: Kevin T. Vo
 * Course: CS 5800
 * Assignment: Assignment 4 - Problem 6
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

import view.ReceiptView;
import model.dataccess.OrderAccess;

public class LoginBusiness extends JFrame implements ActionListener {
	private String address = "";
	private Boolean isWebClient = false;
	private String userName;
	private String password;
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
	
	public void setAttributes(String in_userName, String in_password, Boolean in_isWebClient, HttpServletRequest in_request, HttpServletResponse in_response)
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
					OrderAccess orderAccess_obj = new OrderAccess(userName);
					System.out.println(orderAccess_obj.submitOrder(user));
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
