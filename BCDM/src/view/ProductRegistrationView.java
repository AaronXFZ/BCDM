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
import javax.swing.JToggleButton;

import model.entities.ProductRegistration;

@SuppressWarnings("serial")
public class ProductRegistrationView extends JFrame implements ActionListener {
	
	private boolean is_beverage;
	
	private JLabel lblProductName, lblIsBeverage, lblPrice;
	
	private JTextField txtProductName, txtPrice;
	
	private JToggleButton toggleButtonBeverage, toggleButtonDish;
	
	private JButton buttonSubmit, buttonClear;
	
	private JPanel panel1, panel2, panel3;
		
	public ProductRegistrationView() {

		this.initializeComponents();

		this.buildUI();
		
	}	
	
private void initializeComponents() {
		
		this.lblProductName = new JLabel("Product Name:   ");
		this.lblIsBeverage = new JLabel("Product Type:   ");
		this.lblPrice = new JLabel("Price:   ");


		this.buttonSubmit = new JButton("Submit");
		this.buttonSubmit.addActionListener(this);

		this.buttonClear = new JButton("Clear");
		this.buttonClear.addActionListener(this);

		this.txtProductName = new JTextField(23);
		this.txtPrice = new JTextField(23);
		
		this.toggleButtonBeverage = new JToggleButton("Beverage");
		this.toggleButtonBeverage.addActionListener(this);
		
		this.toggleButtonDish = new JToggleButton("Dish");
		this.toggleButtonDish.addActionListener(this);
		
		this.panel1 = new JPanel();
		this.panel1.setLayout(new FlowLayout(FlowLayout.CENTER));

		this.panel2 = new JPanel();
		this.panel2.setLayout(new FlowLayout(FlowLayout.CENTER));

		this.panel3 = new JPanel();
		this.panel3.setLayout(new FlowLayout(FlowLayout.CENTER));

	}

private void buildUI() {

	this.panel1.add(this.lblProductName);
	this.panel1.add(this.txtProductName);
	
	this.panel2.add(this.lblPrice);
	this.panel2.add(this.txtPrice);
	
	this.panel2.add(this.lblIsBeverage);
	this.panel2.add(this.toggleButtonBeverage);	
	this.panel2.add(this.toggleButtonDish);


	this.panel3.add(this.buttonSubmit);
	this.panel3.add(this.buttonClear);

	

	this.getContentPane().add(panel1, BorderLayout.NORTH);
	this.getContentPane().add(panel2, BorderLayout.CENTER);
	this.getContentPane().add(panel3, BorderLayout.SOUTH);

	this.setTitle("Product Registration");
	this.setBounds(200, 140, 400, 300);
	this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
	this.setResizable(false);
	this.setVisible(true);
	
	this.toggleButtonBeverage.setEnabled(true);
	this.toggleButtonDish.setEnabled(true);
	
}

	public void actionPerformed(ActionEvent event) {
		if (event.getSource() == this.buttonSubmit) {
			
			Boolean isWebClient = false;
			
			String product_name = txtProductName.getText();
			Double price = Double.parseDouble(txtPrice.getText());
		
			
		
			ProductRegistration product_registration = new ProductRegistration();
			
			boolean item_already_exist = product_registration.register_product(product_name, price, is_beverage);
			boolean user_agrees_to_change_price;
			
			if(item_already_exist)
			{
				
			}
			new SuccessView();
			
		}
		else if (event.getSource() == this.toggleButtonBeverage)
		{
			this.toggleButtonBeverage.setEnabled(false);
			this.toggleButtonDish.setEnabled(true);
			this.is_beverage = true;
			
			System.out.println("is_beverage " + this.is_beverage);
		}
		else if (event.getSource() == this.toggleButtonDish)
		{
			this.toggleButtonDish.setEnabled(false);
			this.toggleButtonBeverage.setEnabled(true);
			
			this.is_beverage = false;
			
			System.out.println("this.is_beverage = " + this.is_beverage);
		}
		else {
			this.txtProductName.setText("");
			this.txtPrice.setText("");

		}
		
	
	}

}
