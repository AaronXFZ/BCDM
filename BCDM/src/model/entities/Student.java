package model.entities;

import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name = "student")
public class Student 
{
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	final double discount_percentage = 0.12;
	
	public Student() {}
	
	public Student(int id)
	{
		this.id = id;
	}
	
	
	public void set_id(int id)
	{
		this.id = id;
	}
	
	public int getID()
	{
		return id;
	}
	
	public double get_discounted_price(double full_price)
	{
		
		final double discounted_price = full_price - (full_price*discount_percentage);
		
		return discounted_price;
	}

}
