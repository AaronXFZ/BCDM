package view;

import view.DiscountDatabaseModifierView; //updates user's discount rate on database

import model.entities.Cart;	// Use to add carted items onto list to then be pushed to DB
import model.entities.OnlineItem;
import model.entities.PriceHistory;
import model.entities.SubmitOrder;
import model.entities.User; // Associates order with the User for the DB 
import model.entities.UserAccountRetrieval;

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
import java.text.SimpleDateFormat;
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
import javax.swing.UIManager;
import javax.swing.WindowConstants;

//This class contains the GUI interface for making an order
//consult the associated UoD for inspiration
public class OrderView {
	User user = User.getSingletonObject();
	
	Cart cart_obj = new Cart(user);
	
	private Double user_discount_rate;
	
	private List<String> items_ordered = new ArrayList(); //Stores the current items in cart
	
	List<OnlineItem> local_full_menu = new ArrayList();
	
	private JFrame frame;
	private String selectionTab;
	private JPanel selectionPanel;
	private JPanel currentMenuItemsPanel;
	private JPanel selectedItemsListPanel;
	private JLabel priceLable;

	//private JCheckBox professorDiscount;
	private ActionListener menuItemActionListener;

	
	private int currentPrice;
	private HashMap<String, JButton> selectionTabMap;
	private HashMap<String, JLabel> selectedMenuItemLabels;
	private HashMap<String, Integer> menuItemNameToPrice;
	private List<String> selectedMenuItems;
	public Color bronco = new Color(40,134,65);
	
	public OrderView() {
		System.out.println("\n\tUSERNAME = " + this.user.getUserName());
		//Set the discount rate
		UserAccountRetrieval user_db = new UserAccountRetrieval(this.user.getUserName()); //entity for retrieving user account in model.entity
		this.user = user_db.get_user(this.user.getUserName());
		this.user_discount_rate = 1.0-user_db.get_discount_from_db();
		this.user.set_discount_rate(this.user_discount_rate);
		
		System.out.println("\n\tuser_db.get_discount(); = "  + user_db.get_discount());
		System.out.println("\n\tthis.user_discount_rate = "  + this.user_discount_rate);
		
		System.out.println("\n\tthis.user.get_discount_rate() = "  + this.user.get_discount_rate());
		
		store_food_menu_locally();	//pulls food menu from the DB
		
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
	
	private void store_food_menu_locally()
	{
		try {
			for(int i = 0; i < cart_obj.get_ALL_available_items().get_all_online_items().size(); i++)
			{
				local_full_menu.add(cart_obj.get_ALL_available_items().get_all_online_items().get(i));
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void initializeDefaultSetting() {
		selectionTab = "entiremenu";
		currentPrice = 0;
		selectionTabMap = new HashMap<>();
		selectedMenuItemLabels = new HashMap<>();
		selectedMenuItems = new ArrayList<>();
		initializePriceMap();
		
	}
	
	private void initializePriceMap() {
		//connection to the db hash map here
		//the following is just fake data for testing
		
		try {
			menuItemNameToPrice = cart_obj.get_ALL_available_items().get_online_item_hash_map_INTEGER_PRICES();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
//		menuItemNameToPrice = new HashMap<>();
//		menuItemNameToPrice.put("Calamari", 699);
//		menuItemNameToPrice.put("Fries", 599);
//		menuItemNameToPrice.put("Potato", 499);
//		menuItemNameToPrice.put("Chicken Wings", 899);
//		menuItemNameToPrice.put("Onion Rings", 599);
//		menuItemNameToPrice.put("Ribeye Steak", 2099);
//		menuItemNameToPrice.put("New York Steak", 2399);
//		menuItemNameToPrice.put("Lobster", 4099);
//		menuItemNameToPrice.put("Coke", 199);
//		menuItemNameToPrice.put("Sprite", 199);
//		menuItemNameToPrice.put("Orange Juice", 299);
//		menuItemNameToPrice.put("Rice", 199);
		
	}
	
	private JPanel createOrderPanel() {
		String orderName = "Order     ";
		
		if(user.get_is_professor())
		{
			orderName += "Professor ";
		}
		else
		{
			orderName += "Student ";
		}
		
		orderName += user.get_last_name() + 
				" (" + Math.round((1.0-this.user_discount_rate) *100)+ "% off)";
		
		JPanel orderPanel = new JPanel();
		
		orderPanel.setLayout(new BoxLayout(orderPanel, BoxLayout.PAGE_AXIS));
		orderPanel.add(new JLabel(orderName));
		orderPanel.add(new JLabel("Items"));
		orderPanel.add(createSelectedItemsListPanel());
		
		orderPanel.add(new JLabel("Total Price: "));

		priceLable = new JLabel(toPriceString(currentPrice*100));

		orderPanel.add(priceLable);
		//professorDiscount = new JCheckBox("Professor Discounts (10% off)", false);
		//professorDiscount.addActionListener(menuItemActionListener);
		//orderPanel.add(professorDiscount);
		JButton submitButton = new JButton("Submit Order");
		
		submitButton.addActionListener(ae -> {
			
			submit_order();	//submits the order to the DB
			
			JOptionPane.showMessageDialog(orderPanel, "Order Submited");
			JOptionPane.showMessageDialog(orderPanel, generateReceiptString());
		});
		JButton printReceipt = new JButton("Print Receipt");
		printReceipt.addActionListener(ae -> {
			JOptionPane.showMessageDialog(orderPanel, generateReceiptString());
		});
		JButton printHistory = new JButton("Print Price History");
		printHistory.addActionListener(ae -> {
			JOptionPane.showMessageDialog(orderPanel, generateHistoryInformation());
		});
		
		JButton btn_view_intel_report = new JButton("Show Intelligent Revenue Report");
		btn_view_intel_report.addActionListener(ae -> {
			employeeProtectedServices("intel_revenue_report_view");
		});
		
		JButton btn_view_register_product = new JButton("Register Product");
		btn_view_register_product.addActionListener(ae -> {
			employeeProtectedServices("register_product_view");
		});
		
		JButton btn_view_update_discount = new JButton("Update Discount");
		btn_view_update_discount.addActionListener(ae ->{
			employeeProtectedServices("update_discount_view");
		});
		
		orderPanel.add(submitButton);
		orderPanel.add(printReceipt);
		orderPanel.add(printHistory);
		orderPanel.add(btn_view_intel_report);
		orderPanel.add(btn_view_register_product);
		orderPanel.add(btn_view_update_discount);
		
		orderPanel.setBorder(BorderFactory.createLineBorder(bronco, 3));
		return orderPanel;
	}
	
	private JPanel createSelectedItemsListPanel() {
		selectedItemsListPanel = new JPanel();
		selectedItemsListPanel.setLayout(new BoxLayout(selectedItemsListPanel, BoxLayout.PAGE_AXIS));
		selectedItemsListPanel.setPreferredSize(new Dimension(200,800));
		return selectedItemsListPanel;
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
		JPanel CategorySelectionPanel = new JPanel();
		CategorySelectionPanel.setLayout(new GridLayout(1,4,3,3));
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
		selectionTabMap.put("entiremenu", new JButton("EntireMenu"));
		CategorySelectionPanel.add(selectionTabMap.get("entiremenu"));
		selectionTabMap.put("dishes", new JButton("Dishes"));
		CategorySelectionPanel.add(selectionTabMap.get("dishes"));
		selectionTabMap.put("beverages", new JButton("Beverages"));
		CategorySelectionPanel.add(selectionTabMap.get("beverages"));
		
		selectionTabMap.values().forEach(button -> button.setActionCommand(button.getText()));
		selectionTabMap.values().forEach(button -> button.addActionListener(categorySelectionActionListener));
		
		selectionTabMap.get(selectionTab).setEnabled(false);
		
		return CategorySelectionPanel;
	}
	

	

	private void generateMenuItems() {
		menuItemActionListener = ae -> {
			JButton menuItemButton = (JButton) ae.getSource();
			String itemName = menuItemButton.getActionCommand();
			if(itemName == null) {
				System.out.println("The item name is empty");
			}
			else {
				System.out.println(itemName);
			}
		
			if(selectedMenuItems.contains(itemName)) {
				selectedMenuItems.remove(itemName);
				menuItemButton.setBorder(UIManager.getBorder("Button.border"));
				removeSelectedMenuItemLabel(itemName);
				currentPrice-=menuItemNameToPrice.get(itemName);
				//if (!professorDiscount.isSelected()) {
				if (!user.get_is_professor()) {
					//priceLable.setText(toPriceString(currentPrice*100));
					priceLable.setText(toPriceString((int)(currentPrice*this.user_discount_rate*100)));
				}
				else {
					//priceLable.setText(toPriceString((int)(currentPrice*90)));
					priceLable.setText(toPriceString((int)(currentPrice*this.user_discount_rate*100)));
				}
			}
			else {
				selectedMenuItems.add(itemName);
				menuItemButton.setBorder(BorderFactory.createLineBorder(bronco,4));
				addSelectedMenuItemButton(itemName);
				currentPrice+=menuItemNameToPrice.get(itemName);
				//if (!professorDiscount.isSelected()) {
				if(!user.get_is_professor()) {
					//priceLable.setText(toPriceString(currentPrice*100));
					priceLable.setText(toPriceString((int)(currentPrice*this.user_discount_rate*100)));
				}
				else {
					priceLable.setText(toPriceString((int)(currentPrice*this.user_discount_rate*100)));
				}
				//select the item
			}
		};
		List<String> categoryItems = getMenuItemsFromCategory(selectionTab);
		JPanel menuItemsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		menuItemsPanel.setPreferredSize(new Dimension(860,860));
		Font buttonFont = new Font("Arial", Font.PLAIN, 20);
		//menuItemsPanel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		for (int x = 0; x<categoryItems.size(); x++) {
			String menuItemName = categoryItems.get(x);
			JButton button = new JButton("<html><center>" + menuItemName + "</center></html>");
			button.setFont(buttonFont);
			button.setPreferredSize(new Dimension(143, 110));
			button.setActionCommand(menuItemName);
			button.addActionListener(menuItemActionListener);
			if(selectedMenuItems.contains(menuItemName))  
			{
				button.setBorder(BorderFactory.createLineBorder(bronco, 4));
			}
			menuItemsPanel.add(button);
		}
		
		if(currentMenuItemsPanel != null) {
			selectionPanel.remove(currentMenuItemsPanel);
		}
		
		selectionPanel.add(menuItemsPanel, FlowLayout.CENTER);
		currentMenuItemsPanel = menuItemsPanel;
		selectionPanel.invalidate();
		selectionPanel.validate();
		//return menuItemsPanel;
	}
	
	private void addSelectedMenuItemButton(String menuItemName) {
		JLabel toAdd = new JLabel("<html><center>" + menuItemName+ "..."+toPriceString(menuItemNameToPrice.getOrDefault(menuItemName, 0)*100)+ "</center></html>");
        Dimension toAddLabelSize = new Dimension(200,40);
        toAdd.setMaximumSize(toAddLabelSize);
        toAdd.setMinimumSize(toAddLabelSize);
        toAdd.setPreferredSize(toAddLabelSize);
        selectedItemsListPanel.add(toAdd);
        selectedMenuItemLabels.put(menuItemName, toAdd);
		
		//Adds item to cart container
		cart_obj.add_item(menuItemName);
		
	}
	
	private void removeSelectedMenuItemLabel(String menuItemName) {
		JLabel toRemove = selectedMenuItemLabels.getOrDefault(menuItemName, null);
		if (toRemove == null) {
			System.out.println("Tried to remove " + menuItemName + " but it is not in the list.");
		}
		else {
			selectedItemsListPanel.remove(toRemove);
			selectedMenuItemLabels.remove(menuItemName);
			selectedItemsListPanel.updateUI();
			
			//Removes item from cart container
			cart_obj.remove_item(menuItemName);
		}
	}
	
	
	private List<String> getMenuItemsFromCategory(String category) {
		List<String> results = new ArrayList<>();
		//access to the database
		if(category.equalsIgnoreCase("entiremenu")) {
			
			for(int i = 0; i < local_full_menu.size(); i++)
			{
				
				String item_name = local_full_menu.get(i).get_name();
				
				results.add(item_name);
				
			}
			
//			results.add("Calamari");
//			results.add("Fries");
//			results.add("Potato");
//			results.add("Chicken Wings");
//			results.add("Onion Rings");
		}
		else if(category.equalsIgnoreCase("Dishes")) {
			
			for(int i = 0; i < local_full_menu.size(); i++)
			{
				if(! local_full_menu.get(i).get_isBeverage())
				{
					String item_name = local_full_menu.get(i).get_name();
					
					results.add(item_name);
				}
			}

//			results.add("Ribeye Steak");
//			results.add("New York Steak");
//			results.add("Lobster");

		}
		
		else if(category.equalsIgnoreCase("Beverages")) {
			
			for(int i = 0; i < local_full_menu.size(); i++)
			{
				if(local_full_menu.get(i).get_isBeverage())
				{
					String item_name = local_full_menu.get(i).get_name();
					
					results.add(item_name);
				}
			}
//			results.add("Coke");
//			results.add("Sprite");
//			results.add("Orange Juice");
//			results.add("Rice");
		}

		
		return results;
	}
	
	private static String toPriceString(int priceCents) {
        StringBuilder sb = new StringBuilder();
        priceCents = priceCents;
        sb.append("$ ");
        sb.append(priceCents/100);
        sb.append(".");
        String cents = String.valueOf(priceCents%100);
        if(cents.length() == 1) {
            cents = "0" + cents;
        }
        sb.append(cents);
        return sb.toString();

    }
	
	private String generateReceiptString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Receipt: \n\n");
        for(String selectedItem: selectedMenuItems) {
            sb.append(selectedItem);
            sb.append(" - ");
            //if (!professorDiscount.isSelected()) {
            if(!user.get_is_professor()) {
                sb.append(toPriceString(menuItemNameToPrice.getOrDefault(selectedItem, 0)*100));
            }
            else {
                sb.append(toPriceString((int)((menuItemNameToPrice.getOrDefault(selectedItem, 0))*90)));
            }

            sb.append("\n");
        }
        //if (professorDiscount.isSelected()) {
        
        System.out.println("ABCDEF - " + user.get_is_professor());
        System.out.println("ABCDEF - user_discount_rate = " + this.user_discount_rate);
        if(user.get_is_professor()) {
            sb.append("\nThis Professor account got "+this.user_discount_rate*100+" % discount.\n");
        }
        else
        {
        	sb.append("\nThis Student account got "+this.user_discount_rate*100+" % discount.\n");
        }

        sb.append("\n");
        sb.append("Total: \n");
        //if (!professorDiscount.isSelected()) {
        if (!user.get_is_professor()) {
            //sb.append(toPriceString(currentPrice*98));
        	sb.append(toPriceString(currentPrice*(int)(this.user_discount_rate*100)));
        }
        else {
            //sb.append(toPriceString((int)(currentPrice*90)));
        	sb.append(toPriceString((int)(currentPrice*this.user_discount_rate*100)));
        }
        return sb.toString();
    }
	
	//This part is to generate the history, you can modify the for loop to get the desired data from db.
	private String generateHistoryInformation() {
		StringBuilder sb = new StringBuilder();
		sb.append("History Information: \n\n");
		
		PriceHistory price_hist_obj = new PriceHistory();
		
		List<PriceHistory> all_price_hist = price_hist_obj.get_all_price_history();
		
		for(int i = 0; i < all_price_hist.size(); i++)
		{
			
			SimpleDateFormat formatter_date = new SimpleDateFormat("dd-MM-yyyy");
			SimpleDateFormat formatter_time = new SimpleDateFormat("HH:mm:ss");
			
			final String price_change_date = formatter_date.format(all_price_hist.get(i).get_price_date());

			
			sb.append(price_change_date);
			
			sb.append("   Item_ID: " + all_price_hist.get(i).get_item_id());
			sb.append(" - ");
			sb.append("$ "+ all_price_hist.get(i).get_price() + "\n");
		}
		

		return sb.toString();
	}
	
	void employeeProtectedServices(final String feature_name)
	{
		JOptionPane.showMessageDialog(null, "Normally this window would prompt for an employee's password, but for demoing purposes just hit the button below.");
		
		if(feature_name.equals("intel_revenue_report_view"))
		{
			new IntelligentReportView();
		}
		else if(feature_name.equals("register_product_view"))
		{
			new ProductRegistrationView();
		}
		else if(feature_name.equals("update_discount_view"))
		{
			new DiscountDatabaseModifierView(this.user.getUserName());
		}
	}
	
	void submit_order()
	{
		new SubmitOrder(this.cart_obj);
	}
//	public static void main(String[] args) {
//		SwingUtilities.invokeLater(orderPage::new);
//	}
}
