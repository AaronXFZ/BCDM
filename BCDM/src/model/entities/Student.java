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

}
