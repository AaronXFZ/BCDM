package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import javax.swing.JButton;
public class PromptPriceChangeHistoryView extends JFrame implements ActionListener {

	
	private JLabel lblPromptPriceChange;
	
	private JButton btnYES, btnNO;
	
	private JPanel panel1;
	
	boolean user_says_yes = false;
	
	public PromptPriceChangeHistoryView() {}
	
	private void query_user_for_price_change()
	{
		this.btnYES = new JButton("YES");
		this.btnYES.addActionListener(this);
		
		this.btnNO = new JButton("NO");
		this.btnNO.addActionListener(this);
		
		JFrame frame = new JFrame();
		this.lblPromptPriceChange = new JLabel("Item already exist would you like to update it?");
		
		
		this.panel1 = new JPanel();
		this.lblPromptPriceChange.setFont(new Font("Arial",Font.PLAIN,25));
		this.panel1.setLayout(new FlowLayout(FlowLayout.CENTER));
		this.panel1.add(this.lblPromptPriceChange);
		this.panel1.add(this.btnYES);
		this.panel1.add(this.btnNO);
		
		frame.getContentPane().add(panel1, BorderLayout.CENTER);
		frame.setBounds(280,120,500,500);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.setVisible(true);

	}
	
	public void actionPerformed(ActionEvent event) {
		if (event.getSource() == this.btnYES) 
		{
			user_says_yes = true;
		} 
		else 
		{
			user_says_yes = false;
		}
	}
	

}
