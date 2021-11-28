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

import model.business.RegisterLoginBusiness;
import model.dataccess.LoginDataAccess;
import model.entities.MessageException;
import model.entities.User;


@SuppressWarnings("serial")
public class RegisterView  extends JFrame implements ActionListener {

	private JLabel lblUserName, lblPassword;
	
	private JLabel lblFirstName, lblLastName;
	
	private JButton buttonSubmit, buttonClear;

	private JTextField txtUserName, txtPassword;
	
	private JTextField txtFirstName, txtLastName;
	
	private JToggleButton toggle_button_professor, toggle_button_student;

	private JPanel panel1, panel2, panel3;
	
	private boolean is_professor = false;
	
	public RegisterView() {

		this.initializeComponents();

		this.buildUI();
	}
	

	private void initializeComponents() {
		
		this.lblUserName = new JLabel("Username:   ");
		this.lblPassword = new JLabel("Password:   ");
		this.lblFirstName = new JLabel("First Name:   ");
		this.lblLastName = new JLabel("Last Name:   ");

		this.buttonSubmit = new JButton("Submit");
		this.buttonSubmit.addActionListener(this);

		this.buttonClear = new JButton("Clear");
		this.buttonClear.addActionListener(this);

		this.txtUserName = new JTextField(23);
		this.txtPassword = new JTextField(23);
		
		this.txtFirstName = new JTextField(23);
		this.txtLastName = new JTextField(23);
		
		this.toggle_button_professor = new JToggleButton("Professor");
		this.toggle_button_professor.addActionListener(this);
		
		this.toggle_button_student = new JToggleButton("Student");
		this.toggle_button_student.addActionListener(this);
		
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
		
		this.panel2.add(this.lblFirstName);
		this.panel2.add(this.txtFirstName);
		
		this.panel2.add(this.lblLastName);
		this.panel2.add(this.txtLastName);
		
		this.panel2.add(this.toggle_button_professor);
		this.panel2.add(this.toggle_button_student);

		this.panel3.add(this.buttonSubmit);
		this.panel3.add(this.buttonClear);

		

		this.getContentPane().add(panel1, BorderLayout.NORTH);
		this.getContentPane().add(panel2, BorderLayout.CENTER);
		this.getContentPane().add(panel3, BorderLayout.SOUTH);

		this.setTitle("Register");
		this.setBounds(200, 140, 400, 300);
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		this.setResizable(false);
		this.setVisible(true);
		
		this.toggle_button_professor.setEnabled(true);
		this.toggle_button_professor.setEnabled(true);
	}

	public static void main(String[] args) {
		new RegisterView();
	}

	
	//Deprecated this segment has been moved to LoginBusiness.java where the business rules (i.e. validate fields + credentials are at) 
	//Validation for the Desktop part
	public void actionPerformed(ActionEvent event) {
		if (event.getSource() == this.buttonSubmit) {
			
			Boolean isWebClient = false;
			
			String userName = txtUserName.getText();
			String password = txtPassword.getText();
			
			String first_name = this.txtFirstName.getText();
			String last_name = this.txtLastName.getText();
			
			RegisterLoginBusiness register_business_obj = RegisterLoginBusiness.getSingletonObject();
			
			register_business_obj.setAttributes(userName, password, first_name, last_name, this.is_professor,
					                                 isWebClient, null, null);
			
			try {
				register_business_obj.validate_registration();
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			

			
		}
		else if (event.getSource() == this.toggle_button_professor)
		{
			this.toggle_button_professor.setEnabled(false);
			this.toggle_button_student.setEnabled(true);
			this.is_professor = true;
			System.out.println("this.is_professor = " + this.is_professor);
		}
		else if (event.getSource() == this.toggle_button_student)
		{
			this.toggle_button_student.setEnabled(false);
			this.toggle_button_professor.setEnabled(true);
			
			this.is_professor = false;
			
			System.out.println("this.is_professor = " + this.is_professor);
		}
		else {
			this.txtUserName.setText("");
			this.txtPassword.setText("");
			
			this.txtFirstName.setText("");
			this.txtLastName.setText("");
		}
		

	}
}
