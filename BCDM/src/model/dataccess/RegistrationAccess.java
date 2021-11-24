package model.dataccess;

public class RegistrationAccess {
	private static RegistrationAccess reg_access_singleton = new RegistrationAccess();
	
	public RegistrationAccess()
	{}
	
	public static RegistrationAccess get_singleton()
	{
		return reg_access_singleton;
	}
	
	
}
