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

import view.RegisterView;

import model.business.LoginBusiness;
import model.dataccess.LoginDataAccess;
import model.entities.MessageException;
import model.entities.User;

@SuppressWarnings("serial")
public class LoginView extends JFrame implements ActionListener {

	private JLabel lblUserName, lblPassword;
	
	private JButton buttonSubmit, buttonClear, buttonRegister;

	private JTextField txtUserName, txtPassword;

	private JPanel panel1, panel2, panel3;
	
	public LoginView() {

		this.initializeComponents();

		this.buildUI();
	}

	private void initializeComponents() {
		
		this.lblUserName = new JLabel("Username:   ");
		this.lblPassword = new JLabel("Password:   ");

		this.buttonSubmit = new JButton("Submit");
		this.buttonSubmit.addActionListener(this);

		this.buttonClear = new JButton("Clear");
		this.buttonClear.addActionListener(this);
		
		this.buttonRegister = new JButton("Register");
		this.buttonRegister.addActionListener(this);

		this.txtUserName = new JTextField(23);
		this.txtPassword = new JTextField(23);

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
		
		this.panel2.add(this.lblPassword);
		this.panel2.add(this.txtPassword);

		this.panel3.add(this.buttonSubmit);
		this.panel3.add(this.buttonClear);
		this.panel3.add(this.buttonRegister);
		

		this.getContentPane().add(panel1, BorderLayout.NORTH);
		this.getContentPane().add(panel2, BorderLayout.CENTER);
		this.getContentPane().add(panel3, BorderLayout.SOUTH);

		this.setTitle("Login");
		this.setBounds(350, 140, 550, 200);
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		this.setResizable(false);
		this.setVisible(true);
	}

	public static void main(String[] args) {
		new LoginView();
	}

	
	//Deprecated this segment has been moved to LoginBusiness.java where the business rules (i.e. validate fields + credentials are at) 
	//Validation for the Desktop part
	public void actionPerformed(ActionEvent event) {
		if (event.getSource() == this.buttonSubmit) {
			
			Boolean isWebClient = false;
			
			String userName = txtUserName.getText();
			String password = txtPassword.getText();
			
			LoginBusiness login_business_obj = LoginBusiness.getSingletonObject();
			
			login_business_obj.setAttributes(userName, password, isWebClient, null, null);
			
			login_business_obj.validate();

			
		} 
		else if(event.getSource() == this.buttonRegister)
		{

			//Opens the RegisterView()
			new RegisterView();
			
		}
		else {
			this.txtUserName.setText("");
			this.txtPassword.setText("");
		}
	}
	
}
