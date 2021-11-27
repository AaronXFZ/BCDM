package model.entities;

import java.util.Date;

import javax.persistence.*;


@Entity
@Table(name = "customer")
public class Customer 
{
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name = "id")
	private int id; 
	
	@Column(name = "first_name")
	private String first_name;
	
	@Column(name = "last_name")
	private String last_name;
	
	public Customer() {}
	
	public Customer(int id, String first_name, String last_name)
	{
		this.id = id;
		this.first_name = first_name;
		this.last_name = last_name;
	}
	
	public void set_id(int id)
	{
		this.id = id;
	}
	
	public void set_first_name(String first_name)
	{
		this.first_name = first_name;
	}
	
	public void set_last(String last_name)
	{
		this.last_name = last_name;
	}
	
	public int get_id()
	{
		return id; 
	}
	public String get_first_name()
	{
		return first_name;
	}
	public String get_last_name()
	{
		return last_name;
	}
	
}