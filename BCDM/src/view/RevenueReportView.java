package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.HashMap;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.business.LoginBusiness;
import model.dataccess.ItemsAccess;

@SuppressWarnings("serial")
public class RevenueReportView extends JFrame {
	
	private String username;
	private String most_popular_item;
	private double total_revenue;
	
	private Integer daysAgo;
	
	private HashMap<String, Integer> items_quantity;
	
	private JLabel lblUserName, lbl_most_pop_item, lbl_total_revenue;

	private JPanel panel1, panel2, panel3;
	String individual_row;
	String date_row;
	
	//later include a list into this too for the filtered items
	public RevenueReportView(String username, HashMap<String, Integer> items_quantity, 
			String most_popular_item, double total_revenue) {
		
		System.out.println("1DFFSDFWFWG");
		
		this.username = username;
		this.most_popular_item = most_popular_item;
		this.total_revenue = total_revenue;
		
		this.items_quantity = items_quantity;
		
		this.initializeComponents();

		System.out.println("2DFFSDFWFWG");
		
		this.buildUI();
		
		System.out.println("3DFFSDFWFWG");
	}
	

	private void initializeComponents() {
		individual_row = "";
		date_row = "";
		if(username.equals(""))
			username = "N/A";
		
		this.lblUserName = new JLabel("Username:   " + username + "   ");
		this.lbl_most_pop_item = new JLabel("Most Popular Item:   " + most_popular_item);
		

		this.panel1 = new JPanel();
		this.panel1.setLayout(new FlowLayout(FlowLayout.CENTER));

		this.panel2 = new JPanel();
		this.panel2.setLayout(new FlowLayout(FlowLayout.CENTER));

		this.panel3 = new JPanel();
		this.panel3.setLayout(new FlowLayout(FlowLayout.CENTER));

	}

	private void buildUI() {
		this.panel1.setLayout(new BoxLayout(panel1, BoxLayout.PAGE_AXIS));
		
//		for (String item_name_key : items_quantity.keySet()) {
//
//			String item_name = item_name_key;
//			
//            int quantity = items_quantity.get(item_name_key);
//   
//            
//            
//            individual_row = (item_name + "      " + quantity);
//            this.panel1.add(new JLabel(individual_row));
//            System.out.println(item_name_key + " ****************** individual_row ====== " +  individual_row);
//            
//            //this.panel2.add(new JLabel(individual_row));
//
//        }

		total_revenue = 0.0;

		
		final String column_names = "Item Name      Quantity     Price";

		this.panel2.add(new JLabel(column_names));
		
		try {
			ItemsAccess items_access_obj = new ItemsAccess();
			
			for (String item_name_key : items_quantity.keySet()) {

				String item_name = item_name_key;
				
	            int quantity = items_quantity.get(item_name_key);
	            
	            Double item_price = items_access_obj.get_online_item_hash_map().get(item_name_key);
	            
	            total_revenue += item_price * quantity;
	            
	            individual_row = (item_name + "      " + quantity + "        " + item_price);
	            this.panel1.add(new JLabel(individual_row));
	           
	            
	            //this.panel2.add(new JLabel(individual_row));

	        }

			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		this.lbl_total_revenue = new JLabel("Total Revnue : $ " + String.format("%.2f", total_revenue));
		
		this.panel1.add(this.lblUserName);
		this.panel1.add(this.lbl_most_pop_item);
		this.panel1.add(this.lbl_total_revenue);
		

		this.getContentPane().add(panel1, BorderLayout.NORTH);
		//this.getContentPane().add(panel2, BorderLayout.CENTER);
		//this.getContentPane().add(panel3, BorderLayout.SOUTH);

		this.setTitle("Revenue Result");
		this.setBounds(400, 140, 400, 200);
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		this.setResizable(false);
		this.setVisible(true);
	}

	
}


































//
//import java.awt.BorderLayout;
//import java.awt.FlowLayout;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.sql.SQLException;
//import java.util.HashMap;
//
//import javax.swing.JButton;
//import javax.swing.JFrame;
//import javax.swing.JLabel;
//import javax.swing.JPanel;
//import javax.swing.JTextField;
//
//import model.business.RegisterLoginBusiness;
//import model.dataccess.ItemsAccess;
//
//@SuppressWarnings("serial")
//public class RevenueReportView extends JFrame {
//	
//	private String username;
//	private String most_popular_item;
//	private double total_revenue;
//	
//	private HashMap<String, Integer> items_quantity;
//	
//	private JLabel lblUserName, lbl_most_pop_item, lbl_total_revenue;
//
//	private JPanel panel1, panel2, panel3;
//	
//	//later include a list into this too for the filtered items
//	public RevenueReportView(String username, HashMap<String, Integer> items_quantity, 
//			String most_popular_item, double total_revenue) {
//		
//		this.username = username;
//		this.most_popular_item = most_popular_item;
//		this.total_revenue = total_revenue;
//		
//		this.items_quantity = items_quantity;
//		
//		this.initializeComponents();
//
//		this.buildUI();
//	}
//	
//
//	private void initializeComponents() {
//		
//		if(username.equals(""))
//			username = "N/A";
//		
//		this.lblUserName = new JLabel("Username:   " + username + "   ");
//		this.lbl_most_pop_item = new JLabel("Most Popular Item:   " + most_popular_item);
//		this.lbl_total_revenue = new JLabel("Total Revnue : $ " + total_revenue);
//
//		this.panel1 = new JPanel();
//		this.panel1.setLayout(new FlowLayout(FlowLayout.CENTER));
//
//		this.panel2 = new JPanel();
//		this.panel2.setLayout(new FlowLayout(FlowLayout.CENTER));
//
//		this.panel3 = new JPanel();
//		this.panel3.setLayout(new FlowLayout(FlowLayout.CENTER));
//
//	}
//
//	private void buildUI() {
//		
//		for (String item_name_key : items_quantity.keySet()) {
//
//			String item_name = item_name_key;
//			
//            int quantity = items_quantity.get(item_name_key);
//            
// 
//            
//            String individual_row = item_name + "\t" + quantity + "\t";
//            
//            System.out.println(item_name_key + " ****************** individual_row ====== " +  individual_row);
//            
//            //this.panel2.add(new JLabel(individual_row));
//
//        }
//
//		this.panel1.add(this.lblUserName);
//		this.panel1.add(this.lbl_most_pop_item);
//		this.panel1.add(this.lbl_total_revenue);
//
//		
//		final String column_names = "Item Name      Quantity     Price";
//
//		this.panel2.add(new JLabel(column_names));
//		
//		try {
//			ItemsAccess items_access_obj = new ItemsAccess();
//			
//			for (String item_name_key : items_quantity.keySet()) {
//
//				String item_name = item_name_key;
//				
//	            int quantity = items_quantity.get(item_name_key);
//	            
//	            Double item_price = items_access_obj.get_online_item_hash_map().get(item_name_key);
//	            
//	            String individual_row = item_name + "\t" + quantity + "\t" + item_price;;
//	            
//	            System.out.println("****************** individual_row ====== " +  individual_row);
//	            
//	            //this.panel2.add(new JLabel(individual_row));
//
//	        }
//			
//		} catch (ClassNotFoundException | SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		
//		
//		
//
//		this.getContentPane().add(panel1, BorderLayout.NORTH);
//		//this.getContentPane().add(panel2, BorderLayout.CENTER);
//		//this.getContentPane().add(panel3, BorderLayout.SOUTH);
//
//		this.setTitle("Revenue Result");
//		this.setBounds(1000, 140, 1000, 200);
//		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
//		this.setResizable(false);
//		this.setVisible(true);
//	}
//
//	
//}
