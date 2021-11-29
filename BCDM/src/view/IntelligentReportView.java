package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;


import model.business.RegisterLoginBusiness;
import model.entities.Intelligent_Report;
import view.RevenueReportView;

@SuppressWarnings("serial")
public class IntelligentReportView extends JFrame implements ActionListener {

	
	
	private JButton buttonSubmit, buttonClear, buttonRegister;

	private JLabel lblUserName;

	private JTextField txtUserName;

	private JPanel panel1, panel2, panel3;
	
	public IntelligentReportView() {
		
		this.initializeComponents();

		this.buildUI();
	}
	
	private void initializeComponents() {
		
		this.lblUserName = new JLabel("Username:   ");

		this.buttonSubmit = new JButton("Submit");
		this.buttonSubmit.addActionListener(this);

		this.buttonClear = new JButton("Clear");
		this.buttonClear.addActionListener(this);
		

		this.txtUserName = new JTextField(23);

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

			if(userName.equals(""))
			{	
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
			else
			{
				System.out.println("dfdfdfsdfsfsdfsfsfAAA");
				//populates hashmap with items sold (filtered by userName)
				try {
					Intelligent_Report intel_report_obj = new Intelligent_Report();
					
					intel_report_obj.record_ordered_items_onto_map_by_username(userName);
					System.out.println("KLIIIL ME = " + intel_report_obj.get_all_items_ordered_by_username().get("Cheeseburger"));
					
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
			
		} 
		else 
		{
			this.txtUserName.setText("");

		}

 }
}
	