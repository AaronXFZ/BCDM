package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.dataccess.AccountAccess;

@SuppressWarnings("serial")
public class DiscountDatabaseModifierView extends JFrame implements ActionListener {
	AccountAccess acc_access_obj = new AccountAccess();
	
	private String username;
	
	private Double in_discount;
	
	private JLabel lblDiscount, lblInputAdvise;
	
	private JTextField txtDiscount;
	
	private JPanel panel1, panel2, panel3;
	
	private JButton buttonUpdate;
	
	public DiscountDatabaseModifierView(String username)
	{
		this.username = username;
		
		acc_access_obj.set_username(username);
		
		this.initializeComponents();

		this.buildUI();
	}
	
	private void initializeComponents() {
		this.lblDiscount = new JLabel("New Discount for this account (in decimal [0, 1]): ");
		
		this.buttonUpdate = new JButton("Update");
		this.buttonUpdate.addActionListener(this);
		
		this.txtDiscount = new JTextField(23);
		
		this.panel1 = new JPanel();
		this.panel1.setLayout(new FlowLayout(FlowLayout.CENTER));

		this.panel2 = new JPanel();
		this.panel2.setLayout(new FlowLayout(FlowLayout.CENTER));
		
		this.panel3 = new JPanel();
		this.panel3.setLayout(new FlowLayout(FlowLayout.CENTER));
	}
	
	private void buildUI()
	{
		this.panel1.add(this.lblDiscount);
		
		this.panel2.add(this.txtDiscount);
		
		this.panel3.add(this.buttonUpdate);
		
		this.getContentPane().add(panel1, BorderLayout.NORTH);
		this.getContentPane().add(panel2, BorderLayout.CENTER);
		this.getContentPane().add(panel3, BorderLayout.SOUTH);
		
		this.setTitle("Update Current Customer's Discount");
		this.setBounds(350, 140, 550, 200);
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		this.setResizable(false);
		this.setVisible(true);
	}
	
	private static boolean isNumeric(String str)
	{
		try
		{
			Double.parseDouble(str);
			return true;
		}
		catch(NumberFormatException e)
		{
			return false;
		}
	}
	
	public void actionPerformed(ActionEvent event) {
		
		if (event.getSource() == this.buttonUpdate) {
			
			String str_in_discount = this.txtDiscount.getText();
			
			if(isNumeric(str_in_discount))
			{
				this.in_discount = Double.parseDouble(str_in_discount);
				
				if(this.in_discount >= 0.000 && this.in_discount <= 1.000)
				{
					this.acc_access_obj.update_discount(in_discount);
					
					JOptionPane.showMessageDialog(panel1, "The DISCOUNT for this account has been updated."
							+ " Re-open the app to see changes.");
				}
				else
				{
					JOptionPane.showMessageDialog(panel1, "The inputted value must be [0, 1]");
				}
			}
			else
			{
				JOptionPane.showMessageDialog(panel1, "The inputted value must be decimal value.");
			}

			
		} 
	}
	
}


