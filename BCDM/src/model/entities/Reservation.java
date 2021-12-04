package model.entities;
import javax.persistence.*;

@Entity
@Table(name="reservation")
public class Reservation {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id", nullable=false)
	private int id;
	
	@Column(name="firstlastname", nullable=false)
	private String fullname;
	
	@Column(name="phonenumber", nullable=false)
	private String phonenumber;
	
	@Column(name="date", nullable=false)
	private String order_date;
	
	@Column(name="time", nullable=false)
	private String order_time;
	
	@Column(name="order_id", nullable=false)
	private int order_id;	//Binds the items together for an order
	
	@Column(name="item_name", nullable=false)
	private String item_name;
	
	@Column(name="status", nullable=false)
	private String status;
	
	public Reservation() {}
	
	public Reservation(String fullname, String phonenumber, String date, String time, String item_name, int order_id, boolean is_online) 
	{
		this.fullname = fullname;
		this.phonenumber = phonenumber;
		this.order_date = date;
		this.order_time = time;
		this.item_name = item_name;
	
		this.order_id = order_id;
		
		if(is_online)
			this.status = "online-pending";
		else
			this.status = "counter";

	}
	
	public void set_status(String status)
	{
		this.status = status;
	}
	
	public String get_status()
	{
		return status;
	}
	
	public String get_fullname()
	{
		return fullname;
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

