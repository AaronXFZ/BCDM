package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import model.entities.ItemOrder;
import model.entities.OrderManagement;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

/**
 * @author aaronzhang
 *
 */
public class OrderManagementView {
	
	private OrderManagement order_mgmt_obj = new OrderManagement();
	
	private List<String> list_all_orders = order_mgmt_obj.get_list_of_all_orders_str();
	
	private JFrame frame;
	private JPanel orderListPanel;
	private JTextField orderIDInput;
	private ActionListener deleteOrderListener;
	private HashMap<String, JLabel> selectedOrderLabels;
	private List<String> fakeOrderList;
	private Dimension size = new Dimension(800,600);
	
	public OrderManagementView() {
		initializeDefaultSetting();
		frame = new JFrame("Order Management");
		frame.setLayout(new BorderLayout());
		frame.setSize(800,800);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		frame.add(createOrderListPanel(),BorderLayout.NORTH);
		frame.add(createActionPanel(), BorderLayout.SOUTH );
		
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);		
	}
	private void initializeDefaultSetting() {
		selectedOrderLabels = new HashMap<>();
		initializeOrderList();
	}
	private void initializeOrderList() {

		fakeOrderList = new ArrayList<String>();
		
		for(int i = 0; i < list_all_orders.size(); i++)
		{

			fakeOrderList.add(Integer.toString(i));

		}
		
		
//		fakeOrderList.add("10000002");
//		fakeOrderList.add("10000003");
//		fakeOrderList.add("10000004");
//		fakeOrderList.add("10000005");
//		fakeOrderList.add("10000006");
	}
	
	private JPanel createOrderListPanel() {
		String orderTitle = "Order List: ";
		JPanel orderListComplete = new JPanel();
		orderListComplete.setLayout(new BoxLayout(orderListComplete, BoxLayout.PAGE_AXIS));
		orderListComplete.add(new JLabel(orderTitle));
		orderListComplete.add(createOrderList());
		orderListComplete.setPreferredSize(size);
		orderListComplete.setMaximumSize(size);
		orderListComplete.setMinimumSize(size);
		
		
		return orderListComplete;
	}
	
	private JPanel createOrderList() {
		orderListPanel = new JPanel();
		orderListPanel.setLayout(new BoxLayout(orderListPanel, BoxLayout.PAGE_AXIS));
		//orderListPanel.setPreferredSize(new Dimension(200,800));
		//Put the order information here
		Integer index = 0;
		
		for(String orderID: fakeOrderList) {
			//search Order information
			StringBuilder sb = new StringBuilder();
			String str_item = "ID: " + index + list_all_orders.get(index) + "\n";
			sb.append(str_item);
			
			JLabel toAdd = new JLabel(sb.toString());
			orderListPanel.add(toAdd);
			selectedOrderLabels.put(orderID, toAdd);
			index++;
			if(index >= list_all_orders.size())
				break;
		}
		
		generateOrderList();
		return orderListPanel;
	}

	
	private JPanel createActionPanel() {
		JPanel actionPanel = new JPanel();
		orderIDInput = new JTextField(20);
		JButton deleteOrderBtn = new JButton("Delete Order");
		actionPanel.add(orderIDInput);
		actionPanel.add(deleteOrderBtn);
		deleteOrderBtn.addActionListener(deleteOrderListener);
		return actionPanel;
		
	}
	private void generateOrderList() {
		deleteOrderListener = ae -> {
			String orderID = orderIDInput.getText();
			System.out.print(orderID);
			removeOrder(orderID);
		};
	}
	
	private void removeOrder(String orderID) {
		if(fakeOrderList.contains(orderID)) {
			JLabel toRemove = selectedOrderLabels.getOrDefault(orderID, null);
			orderListPanel.remove(toRemove);
			fakeOrderList.remove(orderID);
			orderListPanel.updateUI();
			
			Integer index_to_delete = Integer.parseInt(orderID);
			
			if(index_to_delete >= 0 && index_to_delete < list_all_orders.size())
			{
				order_mgmt_obj.delete_order_by_index(index_to_delete);
			}
		}
		else {
			System.out.println("There is no such order with the order ID.");
		}
	}

}
