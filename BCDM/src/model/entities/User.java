/* Name: Kevin T. Vo
 * Course: CS 5800
 * Assignment: BCDM Project
 * Filename: User.java
 * 
 * Description: Singleton design pattern was used to create a single instance of the classes User (in User.java) 
 * and LoginBusiness (in LoginBusiness.java) no matter how many times you need to use these classes during authentication.
 * 
 */

package model.entities;

import java.util.Date;

import javax.persistence.*;

import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;

@Entity
@Table(name="users")
public class User {

    private static User singleton_instance = new User();

    @Id
	@Column(name="username", nullable=false)
    private String userName;

    @Column(name="password", nullable=false)
    private String password;
    
    @Column(name="first_name", nullable=false)
    private String first_name;
    
    @Column(name="last_name", nullable=false)
    private String last_name;
    
    @Column(name="start_date")
    private Date membership_date;
    
    @Column(name="is_professor")
    private boolean is_professor;
    
    @Column(name="discount_rate")
    private Double discount_rate;
        
    @Transient
    private int num_year_of_membership;

    private User () { }

    //Using Singleton design pattern
    //Allows class to have only one instance despite number of class calls
    public static User getSingletonObject()
    {
        return singleton_instance;
    }
    
    //setAttributes for logging in
    public void setAttributes(String userName, String password)
    {
        this.userName = userName;
        this.password = password;

        
        set_start_member_date();

    }

    //Overload setAttributes for registering
    public void setAttributes(String userName, String password, String first_name, String last_name, boolean is_professor, Double discount_rate)
    {
        this.userName = userName;
        this.password = password;
        this.first_name = first_name;
        this.last_name = last_name;
        this.is_professor = is_professor;
        this.discount_rate = discount_rate;
        
        set_start_member_date();
    }

    public String get_first_name()
    {
    	return first_name;
    }
    
    public String get_last_name()
    {
    	return last_name;
    }
    
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
    
    public void set_is_professor(boolean is_professor)
    {
    	this.is_professor = is_professor;
    }
    
    public void set_discount_rate(Double discount_rate)
    {
    	this.discount_rate = discount_rate;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean get_is_professor()
    {
    	return this.is_professor;
    }
    
    public double get_discount_rate()
    {    	   	
    	return discount_rate;
    }
    
    public double get_discounted_price(double price)
    {   	
    	return price - (discount_rate*price);
    }
    
    public void set_start_member_date()
    {
        Date current_date = new Date(); // This object contains the current date value
        SimpleDateFormat formatter_date = new SimpleDateFormat("dd-MM-yyyy");

        membership_date = current_date;
        //String formatted_current_date = formatter_date.format(current_date);
        
        System.out.println("Start DATE = " + current_date + " -- " + membership_date);

        //int num_year_of_membership = Math.abs(this.membership_date.getYear() - current_date.getYear())

    }

}