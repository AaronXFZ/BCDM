package model.dataccess;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;

import model.entities.Cart;
import model.entities.Reservation;

public class ReservationAccess {

	private ConnectionFactory_Hibernate conn_factory = new ConnectionFactory_Hibernate();
	
	private String fullName;
	private String phoneNumber;
	
	public ReservationAccess(String fullname, String phoneNumber)
	{
		this.fullName = fullname;
		this.phoneNumber = phoneNumber;
	}
	
	private List<Reservation> ordered_items = new ArrayList();
	
	private List<Reservation> ordered = new ArrayList();
	
	public String get_reservation_status(String fullname, String phoneNumber)
	{
		String status="";
		
		try {
			Session session = conn_factory.getSession();
			
			if(!session.getTransaction().isActive())
				session.beginTransaction();
			
			List<Reservation> reser_query = session.createQuery("from Reservation where firstlastname='"+
							fullname+"' AND phonenumber='"+phoneNumber+"'").getResultList();
			
			if(reser_query.size() > 0)
			{
				for(int i = 0; i < reser_query.size(); i++)
				{
					if(reser_query.get(i).get_status() != null )
					{
						status = reser_query.get(i).get_status();
					}
				}
				reser_query.get(0).get_status();
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return status;
	}
	
	public void submit_order(Cart cart, boolean is_online) throws ClassNotFoundException, SQLException
	{
		Date date = new Date(); // This object contains the current date value
		
		SimpleDateFormat formatter_date = new SimpleDateFormat("dd-MM-yyyy");
		SimpleDateFormat formatter_time = new SimpleDateFormat("HH:mm:ss");
		
		final String current_date = formatter_date.format(date);
		
		final String current_time = formatter_time.format(date);
		
		List<String> list_of_items = cart.get_items();
		
		Session session = conn_factory.getSession();
		
		session.beginTransaction();
		
        String order_id_hashvalue = phoneNumber+fullName+current_date+current_time;
		
		int order_id = order_id_hashvalue.hashCode();
		
		for(int i = 0; i < cart.get_items().size(); i++)
		{
			Reservation reservation_obj = new Reservation(fullName, phoneNumber, current_date, current_time, cart.get_items().get(i), order_id, is_online) ;
			
			ordered_items.add(reservation_obj);
			
			session.save(reservation_obj);
		}
		
		session.getTransaction().commit();

		
		
		
		//Clears the cart once order is submitted
		cart.clear_cart();
		
	}
}
