package model.entities;

import javax.persistence.*;

@Entity
@Table(name = "professor")
public class Professor
{
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	double discount_percentage;
	
	public Professor() {}
	
	public Professor(int id)
	{
		this.id = id;
	}
	
	public void set_id(int id)
	{
		this.id = id;
	}
	
	public int get_id()
	{
		return id;
	}
	
//	public int membership_duration()
//	{
//		
//	}
	
	public double get_discounted_price(double full_price)
	{
		
		double discounted_price = full_price - (full_price*discount_percentage);
		
		return discounted_price;
	}

}
