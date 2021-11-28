package view;

import model.dataccess.ItemsAccess; // Obtains the menu from the DB
import model.entities.Cart;	// Use to add carted items onto list to then be pushed to DB
import model.entities.User; // Associates order with the User for the DB 

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
/**
 * @author aaronzhang
 *
 */
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

//This class contains the GUI interface for making an order
//consult the associated UoD for inspiration
public class OrderView {
	User user = User.getSingletonObject();
	
	Cart cart_obj = new Cart(user);
	
	private List<String> items_ordered = new ArrayList(); //Stores the current items in cart
	
	
	
	private JFrame frame;
	private String selectionTab;
	private JPanel selectionPanel;
	private HashMap<String, JButton> selectionTabMap;
	public Color bronco = new Color(40,134,65);
	public OrderView() {
		initializeDefaultSetting();
		frame = new JFrame("Menu");
		frame.setLayout(new BorderLayout());
		frame.setSize(800,800);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		frame.add(createOrderPanel(),BorderLayout.WEST);
		frame.add(createSelectionPanel(), BorderLayout.CENTER );
		
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
	}
	
	private void initializeDefaultSetting() {
		selectionTab = "entire_menu";
		selectionTabMap = new HashMap<>();
	}
	private JPanel createOrderPanel() {
		String orderName = "Order     Someone";
		String order1 = "Chicken Wings(5 pcs)    $11.99";
		String order2 = "Fountain Drinks         $1.49 ";
		
		//Call cart_obj in here to replace String order1 and order2

		
		JPanel orderPanel = new JPanel();
		
		orderPanel.setLayout(new BoxLayout(orderPanel, BoxLayout.PAGE_AXIS));
		orderPanel.add(new JLabel(orderName));
		orderPanel.add(new JLabel("Items"));
		orderPanel.add(new JLabel(order1));
		orderPanel.add(new JLabel(order1));
		orderPanel.add(new JLabel(order2));
		orderPanel.add(new JLabel(order2));
		
		orderPanel.add(new JLabel("Total Price: "));
		orderPanel.add(new JLabel("$26.96"));
		
		orderPanel.add(new JCheckBox("Professor Discounts", true));
		JButton submitButton = new JButton("Submit Order");
		orderPanel.add(submitButton);
		ActionListener submitOrderActionListener = new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				//Insert Access Order here to submit the order
				
				
				JOptionPane.showMessageDialog(orderPanel, "Order Submited");
				
			}
		};
		
		orderPanel.setBorder(BorderFactory.createLineBorder(bronco, 3));
		submitButton.addActionListener(submitOrderActionListener);
		return orderPanel;
	}
	
	private JPanel createSelectionPanel() {
		selectionPanel = new JPanel();
		selectionPanel.setLayout(new BorderLayout());
		selectionPanel.add(createSelectionTabTop(), BorderLayout.NORTH);
		//selectionPanel.add(createActionPanel(),BorderLayout.SOUTH);
		generateMenuItems();
		//selectionPanel.add(createMenuItems(),BorderLayout.CENTER);
		
		return selectionPanel;
	}
	
	private JPanel createSelectionTabTop() {
		JPanel selectionPanel = new JPanel();
		selectionPanel.setLayout(new GridLayout(1,4,3,3));
		ActionListener categorySelectionActionListener = new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				selectionTabMap.get(selectionTab).setEnabled(true);
				JButton o = (JButton) ae.getSource();
				String name = o.getActionCommand();
				selectionTab = name.toLowerCase();
				selectionTabMap.get(selectionTab).setEnabled(false);
				generateMenuItems();
			}
			
		};
		selectionTabMap.put("entire_menu", new JButton("Full Menu"));
		selectionPanel.add(selectionTabMap.get("entire_menu"));
		selectionTabMap.put("dishes", new JButton("Dishes"));
		selectionPanel.add(selectionTabMap.get("dishes"));
		selectionTabMap.put("beverages", new JButton("Beverages"));
		selectionPanel.add(selectionTabMap.get("beverages"));
		
		selectionTabMap.values().stream().forEach(button -> button.setActionCommand(button.getText()));
		selectionTabMap.values().stream().forEach(button -> button.addActionListener(categorySelectionActionListener));
		
		selectionTabMap.get(selectionTab).setEnabled(false);
		
		return selectionPanel;
	}
	
	private JPanel createActionPanel() {
		return null;
	}
	
	private void generateMenuItems() {
		//JPanel menuItemsPanel = new JPanel(new GridLayout(4, 8, 3, 3));
		List<String> categoryItems = getMenuItemsFromCategory(selectionTab);
		JPanel menuItemsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		Font buttonFont = new Font("Arial", Font.PLAIN, 20);
		//menuItemsPanel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		for (int x = 0; x<categoryItems.size(); x++) {
			//String itemText = "Fried Chicken";
			JButton button = new JButton("<html><center>" + categoryItems.get(x) + "</center></html>");
			button.setFont(buttonFont);
			button.setPreferredSize(new Dimension(143, 110));
			menuItemsPanel.add(button);
		}
		selectionPanel.add(menuItemsPanel, FlowLayout.CENTER);
		selectionPanel.invalidate();
		selectionPanel.validate();
		//return menuItemsPanel;
	}
	
	private List<String> getMenuItemsFromCategory(String category){
		List<String> results = new ArrayList();
		
		try {
			ItemsAccess item_access_obj = new ItemsAccess();
			
			
			for(int i = 0; i < item_access_obj.get_all_online_items().size(); i++)
			{
				String item_name = item_access_obj.get_all_online_items().get(i).get_name();
				
				
				results.add(item_name);
	
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
//		//access to the database
//		if(category.equalsIgnoreCase("entire_menu")) {
//			results.add("Fried Chicken");
//			results.add("Fried Chicken");
//			results.add("Fried Chicken");
//			results.add("Fried Chicken");
//			results.add("Fried Chicken");
//		}
//		else {
//			results.add("Fried Duck");
//			results.add("Fried Duck");
//			results.add("Fried Duck");
//			results.add("Fried Duck");
//			results.add("Fried Duck");
//		}
		
		return results;
	}
	
	public static void main(String[] args) {
		//SwingUtilities.invokeLater(OrderView::new);
	}
}
