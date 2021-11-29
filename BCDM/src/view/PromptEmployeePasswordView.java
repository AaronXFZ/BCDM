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

public class PromptEmployeePasswordView extends JFrame implements ActionListener {
	boolean password_is_correct = false;
	
	private String incoming_password;
	
	final String defaulted_employee_password = "123";
	
	private JLabel lblPassword;
	
	private JButton buttonSubmit, buttonClear;
	
	private JTextField txtPassword;

	private JPanel panel1, panel2;
	
	public PromptEmployeePasswordView(String in_password)
	{
		this.incoming_password = in_password;
		
		this.password_is_correct = defaulted_employee_password.equals(in_password);
		
		this.initializeComponents();

		this.buildUI();
	}
	
	public void set_incoming_password(String in_password)
	{
		this.incoming_password = in_password;
		
		this.password_is_correct = defaulted_employee_password.equals(in_password);
	}
	
	public boolean get_status()
	{
		return password_is_correct;
	}
	
	private void initializeComponents() {
		
		this.lblPassword = new JLabel("Password:   ");

		this.buttonSubmit = new JButton("Submit");
		this.buttonSubmit.addActionListener(this);

		this.buttonClear = new JButton("Clear");
		this.buttonClear.addActionListener(this);
		
		this.txtPassword = new JTextField(23);

		this.panel1 = new JPanel();
		this.panel1.setLayout(new FlowLayout(FlowLayout.CENTER));

		this.panel2 = new JPanel();
		this.panel2.setLayout(new FlowLayout(FlowLayout.CENTER));


	}
	
	private void buildUI() {

		this.panel1.add(this.lblPassword);
		this.panel1.add(this.txtPassword);

		this.panel2.add(this.buttonSubmit);
		this.panel2.add(this.buttonClear);

		this.getContentPane().add(panel1, BorderLayout.NORTH);
		this.getContentPane().add(panel2, BorderLayout.CENTER);


		this.setTitle("Verify Employee");
		this.setBounds(350, 140, 550, 200);
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		this.setResizable(false);
		this.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent event) {
		if (event.getSource() == this.buttonSubmit) {
			
			if(password_is_correct)
			{
				new IntelligentReportView();
			}
			


			
		} 
		else
		{
			this.txtPassword.setText("");
		}
	}
	
}






