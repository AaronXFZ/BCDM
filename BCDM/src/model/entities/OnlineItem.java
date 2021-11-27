package model.entities;

import javax.persistence.*;

@Entity
@Table(name="online_item")
public class OnlineItem {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id", nullable=false)
	private int id;
	
	@Column(name="name", nullable=false)
	private String name = "";
	
	@Column(name="is_beverage", nullable=false)
	private boolean isBeverage;
	
	@Column(name="price", nullable=false)
	private double price;
	
	public OnlineItem() {}
	
	public OnlineItem(String name, double price, boolean isBeverage) {
		this.name = name;
		this.isBeverage = isBeverage;
		this.price = price;
	}
	
	public String get_name()
	{
		return name;
	}
	
	public boolean get_isBeverage()
	{
		return isBeverage;
	}
	
	public double get_price()
	{
		return price;
	}
	
	public void set_name(String name)
	{
		this.name = name;
	}
	
	public void set_isBeverage(boolean isBeverage)
	{
		this.isBeverage = isBeverage;
	}
	
	public void set_price(double price)
	{
		this.price = price;
	}
	
}
