package model.entities;

import model.entities.User;

public class UserRegistration {
	private String username;
	private String password;
	
	private static UserRegistration reg_singleton = new UserRegistration();
	
	UserRegistration() {}
	
	public static UserRegistration get_singleton()
	{
		return reg_singleton;
	}
	
	public Boolean register_user(User user)
	{
		Boolean isRegistered = false;
		
		this.username = user.getUserName();
		this.password = user.getPassword();
		
		
		
		return isRegistered;
	}
}
