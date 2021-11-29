package model.entities;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import model.dataccess.PriceHistoryAccess;

@Entity
@Table(name ="historical_price")
public class PriceHistory
{
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name = "id")
	private int price_id;
	
	@Column(name = "item_id")
	private int item_id; 
	
	@Column(name = "price")
	private double price;
	
	@Column(name = "price_date")
	private Date price_date;
	
	@Column(name= "item_name")
	private String item_name;
	
	public PriceHistory() {}
	
	public PriceHistory(int item_id, String item_name, double price, Date price_date)
	{
		this.item_id = item_id;
		this.price=price;
		this.price_date = price_date;
		this.item_name = item_name;
	}
	
	
	public double get_price()
	{
		return price;
	}
	
	public int get_price_id()
	{
		return price_id;
	}
	
	public Date get_price_date()
	{
		return price_date; 
	}
	
	public int get_item_id()
	{
		return item_id; 
	}
	
	public void add_price_to_history()
	{
		PriceHistoryAccess price_hist_access = new PriceHistoryAccess();
		
		price_hist_access.set_price_hist(new PriceHistory(item_id, item_name, price, price_date));
	}
	
	public List<PriceHistory> get_all_price_history()
	{
		
		
		try {
			PriceHistoryAccess price_hist_access = new PriceHistoryAccess();
			return price_hist_access.get_all_price_history();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
}
