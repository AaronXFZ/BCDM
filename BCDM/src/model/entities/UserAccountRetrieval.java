package model.entities;

import model.dataccess.AccountAccess;

public class UserAccountRetrieval {
	private AccountAccess acc_access_obj = new AccountAccess();

	private User user_obj;
	
	private String username;
	private Double db_discount_rate;
	
	
	public UserAccountRetrieval() {}
	
	public UserAccountRetrieval(String username)
	{
		this.username = username;
		
		acc_access_obj.set_username(username);
		user_obj = acc_access_obj.get_user_from_backend();
		db_discount_rate = acc_access_obj.get_user(username).get_discount_rate();
	}
	
	public void establish_query_user(String username)
	{
		acc_access_obj.set_username(username);
	}
	
	public Double get_discount_from_db()
	{
		return this.db_discount_rate;
	}
	public User get_user(String username)
	{
		return acc_access_obj.get_user(username);
	}
	public String get_username()
	{
		return username;
	}
	
	public double get_discount()
	{
		return acc_access_obj.get_discount_rate(username);
	}
	
	public boolean get_account_type()
	{
		return acc_access_obj.is_professor();
	}
}
