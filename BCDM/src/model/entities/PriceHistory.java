package model.entities;

import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name ="historicalprice")
public class PriceHistory
{
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name = "price_id")
	private int price_id;
	
	@Column(name = "item_id")
	private int item_id; 
	
	@Column(name = "price")
	private float price;
	
	@Column(name = "start")
	private Date price_date;
	
	public PriceHistory() {}
	
	public PriceHistory(int item_id, float price, Date price_date)
	{
		this.item_id = item_id;
		this.price=price;
		this.price_date = price_date;
	}
	
	
	public float get_price()
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
	
	public int get_ttem_id()
	{
		return item_id; 
	}
	
	
}
