/* Name: Kevin T. Vo
 * Course: CS 5800
 * Assignment: Assignment 4 - Problem 6
 * Filename: LoginBusiness.java
 * 
 * Description: Singleton design pattern was used to create a single instance of the classes User (in User.java) and LoginBusiness (in LoginBusiness.java) no matter how many times you need to use these classes during authentication.
 * 
 */

package model.entities;

public class User {
	
	private static User singleton_instance = new User();
	
	private String userName;
	
	private String password;
	
	private User () { }
	
	//Using Singleton design pattern
	//Allows class to have only one instance despite number of class calls
	public static User getSingletonObject()
	{
		return singleton_instance;
	}
	
	public void setAttributes(String userName, String password)
	{
		this.userName = userName;
		this.password = password;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
