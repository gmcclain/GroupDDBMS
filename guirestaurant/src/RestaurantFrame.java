import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.nio.charset.Charset;

public class RestaurantFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel centerPanel;
	private JTextField orderPrice;
	private ArrayList<MenuItem> itemsOrdered;
	private BigDecimal totalCost;
	private MenuReader menuRead;
	private JPanel receipt;
	private JTextPane orderItems;
	private String itemInformation;
	private String menu = "default";

	public RestaurantFrame(File givenMenu) throws ClassNotFoundException,
			SQLException, IOException {
		barUI.sqlConnection();
		if (givenMenu.getPath() == "max-soha") {
			menu = "food";
		} else if (givenMenu.getPath() == "max-soha2") {
			menu = "drinks";
		}
		totalCost = new BigDecimal(0);
		itemInformation = "";

		itemsOrdered = new ArrayList<MenuItem>();
		menuRead = new MenuReader(givenMenu);
		menuRead.readInputFile();
		create();

		setSize(1500, 1500);
		setTitle("Distributd Restaurant Menu System");
		setBackground(Color.WHITE);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);

	}

	public void create() {
		JPanel mainPanel = (JPanel) getContentPane();
		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
				getItemButtons(), getReceipt());

		splitPane.setDividerLocation(780);
		mainPanel.setLayout(new BorderLayout());
		mainPanel.add(splitPane, BorderLayout.CENTER);

	}

	private JScrollPane getItemButtons() {
		JPanel pan = new JPanel();
		pan.setLayout(new GridLayout(0, 2));

		ArrayList<MenuItem> itemButtons = menuRead.getMenuItems();

		for (final MenuItem itemButton : itemButtons) {

			final JButton createButton = new JButton(itemButton.getName());
			createButton.setToolTipText(itemButton.getName());

			createButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					refreshPanel(itemButton);
					try {
						if (menu.equals("food")) {

							Integer val = 300 + (int) (Math.random() * ((999 - 300) + 1));
							Integer val2 = 2 + (int) (Math.random() * ((100 - 2) + 1));
							String foodId = Integer.toString(val);
							String menuID = Integer.toString(val2);
							String name = itemButton.getName();
							String sql = "INSERT INTO FOOD (foodID,name,category,menuID) VALUES("
									+ foodId
									+ ","
									+ "'"
									+ name
									+ "'"
									+ ","
									+ "'" + "food" + "'" + "," + menuID + ");";
							Statement stmt = barUI.conn.createStatement();
							stmt.executeUpdate(sql);
							stmt.close();
						} else if (menu.equals("drinks")) {

							Integer val = 300 + (int) (Math.random() * ((999 - 300) + 1));
							Integer val2 = 2 + (int) (Math.random() * ((100 - 2) + 1));
							String drinkID = Integer.toString(val);
							String menuID = Integer.toString(val2);
							String name = itemButton.getName();
							String sql = "INSERT INTO DRINK (drinkID,name,category,menuID) VALUES("
									+ drinkID
									+ ","
									+ "'"
									+ name
									+ "'"
									+ ","
									+ "'" + "drink" + "'" + "," + menuID + ");";
							Statement stmt = barUI.conn.createStatement();
							stmt.executeUpdate(sql);
							stmt.close();
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
			pan.add(createButton);
			createButton.setPreferredSize(new Dimension(30, 60));

		}

		JScrollPane scroller = new JScrollPane(pan);
		Border etchedBorder = BorderFactory.createEtchedBorder();
		Border border = BorderFactory.createTitledBorder(etchedBorder, "Items",
				TitledBorder.DEFAULT_JUSTIFICATION,
				TitledBorder.DEFAULT_POSITION,
				new Font("Lucida", Font.BOLD, 20), Color.BLACK);
		pan.setBorder(border);
		return scroller;

	}

	private JPanel getReceipt() {

		receipt = new JPanel();
		JLabel label = new JLabel("Customer Order:");
		receipt.setLayout(new BorderLayout());

		JPanel lowerPanel = new JPanel();
		lowerPanel.setLayout(new BorderLayout());

		receipt.add(lowerPanel, BorderLayout.SOUTH);
		receipt.add(label, BorderLayout.NORTH);

		centerPanel = new JPanel();
		centerPanel.setLayout(new GridLayout(0, 1));

		orderItems = new JTextPane();
		centerPanel.add(orderItems);

		orderItems.setEditable(false);

		JScrollPane centerPanelScroller = new JScrollPane(centerPanel);
		receipt.add(centerPanelScroller, BorderLayout.CENTER);

		orderPrice = new JTextField(20);
		orderPrice.setText("Total Cost = $0.00");
		orderPrice.setEditable(false);

		JButton placeOrder = new JButton("Place Order");
		JButton clearOrder = new JButton("Clear Order");

		placeOrder.setPreferredSize(new Dimension(30, 50));
		clearOrder.setPreferredSize(new Dimension(30, 50));

		centerPanel.setBackground(Color.LIGHT_GRAY);
		placeOrder.setForeground(Color.BLUE);
		clearOrder.setForeground(Color.RED);

		placeOrder.setFont(new Font("Times New Roman", Font.BOLD, 40));
		clearOrder.setFont(new Font("Times New Roman", Font.BOLD, 40));

		clearOrder.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				delete();

			}

		});

		placeOrder.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				try {
					if (!orderPrice.getText().equals("Total Cost = $0.00")) {
						menuRead.logOrder(itemsOrdered, totalCost);
						JOptionPane.showMessageDialog(getContentPane(),
								"Order has been sent to kitchen",
								"Order has been logged",
								JOptionPane.INFORMATION_MESSAGE);
						// add total to db
						int val = 100000111 + (int) (Math.random() * ((900000111 - 100000111) + 1));
						String sql = "INSERT INTO SALE (saleNumber,total) VALUES("
								+ val + "," + "'$" + totalCost + "'" + ");";
						Statement stmt = barUI.conn.createStatement();
						stmt.executeUpdate(sql);
						delete();
					} else {
						JOptionPane.showMessageDialog(null, "No items ordered",
								"Place order", JOptionPane.ERROR_MESSAGE);
					}

				} catch (IOException g) {

					JOptionPane.showMessageDialog(null,
							"Error! Program terminated", " Error",
							JOptionPane.ERROR_MESSAGE);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}

			}

		});

		lowerPanel.add(orderPrice, BorderLayout.NORTH);
		lowerPanel.add(placeOrder, BorderLayout.CENTER);
		lowerPanel.add(clearOrder, BorderLayout.SOUTH);
		lowerPanel.setBackground(Color.LIGHT_GRAY);
		receipt.setBackground(Color.WHITE);
		return receipt;

	}

	private void delete() {

		orderPrice.setText("Total Cost = $0.00");
		totalCost = new BigDecimal(0);
		itemsOrdered.clear();
		itemInformation = "";
		orderItems.setText(null);

	}

	private void refreshPanel(final MenuItem itemButton) {
		String item = itemButton.getName();
		BigDecimal itemPrice = itemButton.getCost();
		itemInformation += "\n" + item + "\n" + itemPrice + "\n";
		orderItems.setText(itemInformation);
		itemsOrdered.add(itemButton);

		totalCost = totalCost.add(itemPrice);
		orderPrice.setText("Total cost = $" + totalCost);
	}

}