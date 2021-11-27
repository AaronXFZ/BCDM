package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;


import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class ReceiptView {
	
	private JLabel lblUserName, JLabelHeader_title, JLabelHeader_order_num, JLabelHeader_dateTime, JLabelHeader_realName;
	
	private JPanel panel1;
	
	public ReceiptView(String userName, String realName, String dateTime, String orderID) {
		JFrame frame = new JFrame();
		this.lblUserName = new JLabel("Welcome " + userName + "!");
		
		//header information such as the date, time, and customer name
		JLabel header = new JLabel("<html><style align=\"center\">Bronco Centerpointe Dining</style><br/>Order Numbr: " + orderID + "<style align=\"right\">" + dateTime + "</style><br/>Customer: " + realName + "<br/></html>");
		
		JLabel header_title = new JLabel("Bronco Centerpointe Dining", SwingConstants.CENTER);
		
		JLabel header_order_num = new JLabel("Order Numbr: " + orderID, SwingConstants.LEFT);
		
		JLabel header_dateTime = new JLabel(dateTime, SwingConstants.RIGHT);
		
		JLabel header_realName = new JLabel("Customer: " + realName, SwingConstants.RIGHT);
		
		this.JLabelHeader_title = header_title;
		this.JLabelHeader_order_num = header_order_num;
		this.JLabelHeader_dateTime = header_dateTime;
		this.JLabelHeader_realName = header_realName;
		

		this.panel1 = new JPanel();
		this.JLabelHeader_title.setFont(new Font("Arial",Font.PLAIN,25));
		this.JLabelHeader_order_num.setFont(new Font("Arial",Font.PLAIN,25));
		this.JLabelHeader_dateTime.setFont(new Font("Arial",Font.PLAIN,25));
		this.JLabelHeader_realName.setFont(new Font("Arial",Font.PLAIN,25));
		
		this.panel1.setLayout(new FlowLayout(FlowLayout.CENTER));
		this.panel1.add(this.JLabelHeader_title);
		this.panel1.add(this.JLabelHeader_order_num);
		this.panel1.add(this.JLabelHeader_dateTime);
		this.panel1.add(this.JLabelHeader_realName);
		
		frame.getContentPane().add(panel1, BorderLayout.CENTER);
		frame.setBounds(280,120,500,500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}	

}
