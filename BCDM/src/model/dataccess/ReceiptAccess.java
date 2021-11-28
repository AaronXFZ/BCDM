package model.dataccess;

import model.dataccess.ConnectionFactory_Hibernate;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;  
import java.util.Date;  

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import model.entities.Receipt;

//Stores and retrieve Receipt information
public class ReceiptAccess {
	
	ConnectionFactory_Hibernate conn_factory = new ConnectionFactory_Hibernate();
	
	List<Receipt> receipts;
	
	List<Receipt> username_receipts = new ArrayList();
	
	List<Receipt> range_date_receipts = new ArrayList();
	
	public ReceiptAccess() throws ClassNotFoundException, SQLException
	{
		Session session = conn_factory.getSession();
		
		session.beginTransaction();
		
		receipts = session.createQuery("from Receipt").getResultList();
	}
	
	public List<Receipt> get_receipts_by_username(String username)
	{
		username_receipts.clear();
		
		for(int i = 0; i <= receipts.size(); i++)
		{
			if(receipts.get(i).get_customer_username().equals(username))
			{
				username_receipts.add(receipts.get(i));
			}
		}
		
		return username_receipts;
	}
	
	public List<Receipt> get_all_receipts()
	{
		return receipts;
	}
	
	@SuppressWarnings("deprecation")
	public List<Receipt> get_range_receipts_by_months(String start_date, String end_date) throws ParseException
	{
		this.range_date_receipts.clear();
		
		for(int i = 0; i <= receipts.size(); i++)
		{
			Date receipt_date = new SimpleDateFormat("dd/MM/yyyy").parse(receipts.get(i).get_date());  
			Date date_lowerbnd = new SimpleDateFormat("dd/MM/yyyy").parse(start_date);  
			Date date_upperbnd = new SimpleDateFormat("dd/MM/yyyy").parse(end_date);  
			
			int receipt_month = receipt_date.getMonth();
			int lowerbnd_month = date_lowerbnd.getMonth();
			int upperbnd_month = date_upperbnd.getMonth();
			
			if(receipt_month >= lowerbnd_month && receipt_month <= upperbnd_month)
			{
				range_date_receipts.add(receipts.get(i));
			}
		}
		
		return range_date_receipts;
	}
}
