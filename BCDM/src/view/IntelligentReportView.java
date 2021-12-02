package view;

import java.awt.event.*;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;


import model.business.RegisterLoginBusiness;
import model.entities.Intelligent_Report;
import model.entities.Receipt;
import view.RevenueReportView;

@SuppressWarnings("serial")
public class IntelligentReportView extends JFrame implements ActionListener, FocusListener{

	
	
	private JButton buttonSubmit, buttonClear;

	private JLabel lblUserName, lblStartingDay, lblFinalDay;

	private JTextField txtUserName, txtStartingDay, txtFinalDay;

	private JPanel panel1, panel2, panel3;
	
	public IntelligentReportView() {
		
		this.initializeComponents();

		this.buildUI();
		
		this.txtFinalDay.setEditable(false); //initialize txtFinalDay field as false until startDay is true 
	}
	
	private void initializeComponents() {
		
		this.lblUserName = new JLabel("Username:   ");
		this.lblStartingDay =  new JLabel("Starting Day:   ");
		this.lblFinalDay = new     JLabel("Final Day:      ");
		
		this.buttonSubmit = new JButton("Submit");
		this.buttonSubmit.addActionListener(this);

		this.buttonClear = new JButton("Clear");
		this.buttonClear.addActionListener(this);
		

		this.txtUserName = new JTextField(23);
		this.txtStartingDay = new JTextField(4);
		this.txtFinalDay = new JTextField(4);
		
		this.txtStartingDay.addFocusListener(this);
		this.txtFinalDay.addFocusListener(this);
		
		this.panel1 = new JPanel();
		this.panel1.setLayout(new FlowLayout(FlowLayout.CENTER));

		this.panel2 = new JPanel();
		this.panel2.setLayout(new FlowLayout(FlowLayout.CENTER));

		this.panel3 = new JPanel();
		this.panel3.setLayout(new FlowLayout(FlowLayout.CENTER));

	}
	
	private void buildUI() {

		this.panel1.add(this.lblUserName);
		this.panel1.add(this.txtUserName);
		
		this.panel1.add(this.lblStartingDay);
		this.panel1.add(this.txtStartingDay);
		
		this.panel1.add(this.lblFinalDay);
		this.panel1.add(this.txtFinalDay);

		this.panel3.add(this.buttonSubmit);
		this.panel3.add(this.buttonClear);
		

		this.getContentPane().add(panel1, BorderLayout.NORTH);
		this.getContentPane().add(panel2, BorderLayout.CENTER);
		this.getContentPane().add(panel3, BorderLayout.SOUTH);

		this.setTitle("Intelligent Revenue Report");
		this.setBounds(350, 140, 1000, 200);
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		this.setResizable(false);
		this.setVisible(true);
	}

	public void run_unfiltered_report() 
	{
		//EVERY FIELD BLANK
		//Unfiltered
		
		//populates hashmap with items sold (not filtered)
		try {
			Intelligent_Report intel_report_obj = new Intelligent_Report();
			
			intel_report_obj.record_ordered_items_onto_map();
			intel_report_obj.get_all_items_ordered(); // hash map of entire list
			
			new RevenueReportView(
					"",
					intel_report_obj.get_all_items_ordered(),
					intel_report_obj.get_most_sold_item_all(),
					intel_report_obj.get_total_revenue_all()
					);
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		//intel_report_obj.get_most_sold_item_all(); --> gives a hashmap of all items sold (not filtered)
		//intel_report_obj.get_total_revenue_all();  --> gives the total_revenue from the hashmap above
	}
	
	public void run_filtered_report(String userName, String startingDay, String finalDay)
	{
		//populates hashmap with items sold (filtered by userName)
		try {
			Intelligent_Report intel_report_obj = new Intelligent_Report();
			
			intel_report_obj.record_ordered_items_onto_map_by_username(userName);
			
			
			new RevenueReportView(
					userName,
					intel_report_obj.get_all_items_ordered_by_username(),
					intel_report_obj.get_most_sold_item_by_username(),
					intel_report_obj.get_total_revenue_username()
					);
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void actionPerformed(ActionEvent event) {
	
		
		if (event.getSource() == this.buttonSubmit) {
			
			//populates hashmap with items sold (not filtered)
			try {
				Intelligent_Report intel_report_obj = new Intelligent_Report();
				
				intel_report_obj.record_ordered_items_onto_map();
				intel_report_obj.get_all_items_ordered(); // hash map of entire list
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			String userName = txtUserName.getText();
			String str_start_day = this.txtStartingDay.getText();
			String str_final_day = this.txtFinalDay.getText();
			
			if(userName.equals("") && str_start_day.equals("") && str_final_day.equals(""))
			{
				//Enters NOTHING
				System.out.println("Unfiltered!");
				
				run_unfiltered_report();
			}
			else if(!userName.equals("") && (str_start_day.equals("") && str_final_day.equals("")))
			{
				//Enters in username but no day
				System.out.println("!!!! just username");
				run_filtered_report(userName, "", "");
			}
			else if(userName.equals("") && (!str_start_day.equals("") && !str_final_day.equals("")))
			{	
		
				System.out.println("!!!! Days only");
				
				
				run_filtered_report("", str_start_day, str_final_day);
				
				
			}
			else if(!str_start_day.equals("") && str_final_day.equals(""))
			{
				System.out.println("!!!! Yes startDay, but NO finalDay");
				
				//prompt user that they must fill in both day
				JOptionPane.showMessageDialog(panel1, "You MUST fill in both the Starting Day and Final Day or leave them both blank.");
			}
			else if(str_start_day.equals("") && !str_final_day.equals(""))
			{
				
				System.out.println("!!!! No startDay, but Yes finalDay");
				
				//prompts user that they must fill in both day
				JOptionPane.showMessageDialog(panel1, "You MUST fill in both the Starting Day and Final Day or leave them both blank.");
			}
			
			
			else if(!userName.equals("") && (!str_start_day.equals("") && !str_final_day.equals("")))
			{
				
				System.out.println("!!!! Yes Username, YES startDay, YES finalDay");

				run_filtered_report(userName, str_start_day, str_final_day);
			}
			else
			{
				System.out.println("!!!! OTHER");
				
				run_filtered_report(userName, str_start_day, str_final_day);
				
				
				

				
				
			}
			
		}
		else 
		{
			this.txtUserName.setText("");
			this.txtStartingDay.setText("");
			this.txtFinalDay.setText("");
			this.txtFinalDay.setEditable(false);

		}

 }

	@Override
	public void focusGained(FocusEvent e)
	{
		System.out.println("Focus gained " + e);
		
		if(this.txtStartingDay.getText().isBlank())
		{
			this.txtStartingDay.setText("");
		}
		
		if(this.txtFinalDay.getText().isBlank())
		{
			this.txtFinalDay.setText("");
		}
		
		
		if(this.txtStartingDay.getText().isEmpty() == false || this.txtStartingDay.getText() != "")
		{
			System.out.println("\n\n\nA\n\n\n");
			this.txtFinalDay.setEditable(true);
		}
		else
		{
			System.out.println("\n\n\nB\n\n\n");
			this.txtFinalDay.setEditable(false);
		}
		
	}
	 
	@Override
	public void focusLost(FocusEvent e) {
		// TODO Auto-generated method stub
		
		System.out.println("Focused Lost " + e);
		
	
		if(this.txtStartingDay.getText() == "" && (txtFinalDay.getText().isBlank() == true || this.txtFinalDay.getText().isEmpty() == true))
		{
			System.out.println("\n\n\nE\n\n\n");
			this.txtFinalDay.setText("");
			this.txtFinalDay.setEditable(false);
		}
		
		if(this.txtStartingDay.getText().isBlank() == false && this.txtStartingDay.getText().isEmpty() == false && this.txtStartingDay.getText() != "")
		{
			System.out.println("\n\n\nC\n\n\n");
			this.txtFinalDay.setEditable(true);
		}
		else
		{
			System.out.println("\n\n\nD\n\n\n");
			this.txtFinalDay.setEditable(false);
		}
	}
	

//	@SuppressWarnings("deprecation")
//	public List<Receipt> get_range_receipts_by_months(String start_date, String end_date) throws ParseException
//	{
//		this.range_date_receipts.clear();
//		
//		for(int i = 0; i <= receipts.size(); i++)
//		{
//			Date receipt_date = new SimpleDateFormat("dd/MM/yyyy").parse(receipts.get(i).get_date());  
//			Date date_lowerbnd = new SimpleDateFormat("dd/MM/yyyy").parse(start_date);  
//			Date date_upperbnd = new SimpleDateFormat("dd/MM/yyyy").parse(end_date);  
//			
//			int receipt_month = receipt_date.getMonth();
//			int lowerbnd_month = date_lowerbnd.getMonth();
//			int upperbnd_month = date_upperbnd.getMonth();
//			
//			if(receipt_month >= lowerbnd_month && receipt_month <= upperbnd_month)
//			{
//				range_date_receipts.add(receipts.get(i));
//			}
//		}
//		
//		return range_date_receipts;
//	}
}
	