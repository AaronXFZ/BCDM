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

}
