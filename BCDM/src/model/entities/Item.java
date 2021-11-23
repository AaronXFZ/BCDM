package model.entities;

public class Item {
	
	final private Integer max_num_items = 11;
	private String name[];
	private Boolean isBeverage[];
	private Double price[];

	//In case of stack overflow it resorts to these values
	final private String error_name = "N/A";
	final private Boolean error_isBeverage = false;
	final private Double error_price = 0.00;
	
	final private String stackoverflow_err_msg = "\nError: Inserted ID must be [0,"+(max_num_items-1)+"] within the Item class.\n";
	
	private static Item singleton_obj = new Item();
	
	public Item() {
		name = new String[max_num_items];
		isBeverage = new Boolean[max_num_items];
		price = new Double[max_num_items];
		
		set_items(0, "Cheeseburger", false, 3.99);
		set_items(1, "Hamburger", false, 3.25);
		set_items(2, "Fries", false, 2.99);
		set_items(3, "Coke", true, 2.99);
		set_items(4, "Diet Coke", true, 2.99);
		set_items(5, "Sprite", true, 2.99);
		set_items(6, "Fanta Orange", true, 2.99);
		set_items(7, "Coffee", true, 2.99);
		set_items(8, "Beef Taco", false, 1.99);
		set_items(9, "3 Pc. Chicken Tenders", false, 4.25);
		set_items(10, "California Burrito", false, 6.95);

	}
	
	private void set_items(Integer ID_num, String name, Boolean isBeverage, Double price)
	{
		this.name[ID_num] = name;
		this.isBeverage[ID_num] = isBeverage;
		this.price[ID_num] = price;
	}
	
	public static Item get_singleton()
	{
		return singleton_obj;
	}
	
		
	public String get_name(Integer ID)
	{
		if(ID > max_num_items)
		{
			System.out.println(stackoverflow_err_msg);
			return error_name;
		}
		
		return name[ID];
	
	}
	
	public Boolean get_isBeverage(Integer ID)
	{
		if(ID > max_num_items)
		{
			System.out.println(stackoverflow_err_msg);
			return error_isBeverage;
		}
		
		return isBeverage[ID];
	}
	
	public Double get_price(Integer ID)
	{
		if(ID > max_num_items)
		{
			System.out.println(stackoverflow_err_msg);
			return error_price;
		}
		
		return price[ID];
	}
}
