package model.entities;

import javax.persistence.*;

@Entity
@Table(name="order_mgmt")
public class ItemOrder {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id", nullable=false)
	private int id;
	
	@Column(name="username", nullable=false)
	private String username;
	
	@Column(name="date", nullable=false)
	private String order_date;
	
	@Column(name="time", nullable=false)
	private String order_time;
	
	@Column(name="order_id", nullable=false)
	private int order_id;	//Binds the items together for an order
	
	@Column(name="item_name", nullable=false)
	private String item_name;
	
	public ItemOrder(String username, String date, String time, String item_name, int order_id) 
	{
		this.username = username;
		this.order_date = date;
		this.order_time = time;
		this.item_name = item_name;
	
		this.order_id = order_id;

	}
	
	public String get_username()
	{
		return username;
	}
	
	public String get_date()
	{
		return order_date;
	}
	
	public String get_time()
	{
		return order_time;
	}
	
	public int get_order_id()

	{
		return order_id;
	}
	
	public String get_item_name()
	{	
		return item_name;
	}

}
