import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.*;

public class barUI extends JPanel implements ActionListener {
	protected JTextField textField;
	protected JTextArea textArea;
	private final static String newline = "\n";
	private JButton getOrder = new JButton("Get Order");
	private JButton clearOrder = new JButton("Clear Order");
	static Connection conn;

	public barUI() throws ClassNotFoundException, SQLException {
		super(new GridBagLayout());
		sqlConnection();
		getOrder.addActionListener(this);
		clearOrder.addActionListener(this);
		textArea = new JTextArea(5, 20);
		textArea.setEditable(false);
		JScrollPane scrollPane = new JScrollPane(textArea);

		// Add Components to this panel.
		GridBagConstraints c = new GridBagConstraints();
		c.gridwidth = GridBagConstraints.REMAINDER;

		c.fill = GridBagConstraints.HORIZONTAL;
		add(getOrder, c);
		add(clearOrder, c);

		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1.0;
		c.weighty = 1.0;
		add(scrollPane, c);
	}

	public void actionPerformed(ActionEvent evt) {
		Object buttonPressed = evt.getSource();
		if (buttonPressed == getOrder) {
			try {
				String sql = "SELECT Top 1 * FROM drink ORDER BY drinkID desc;";
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql);

				while (rs.next()) {
					String drinkID = rs.getString("drinkID");
					String name = rs.getString("name");
					String category = rs.getString("category");
					String menuID = rs.getString("menuID");
					textArea.setText("FoodID: " + drinkID + "\n" + "Name: "
							+ name + "\n" + "Category: " + category + "\n"
							+ "MenuID: " + menuID);
				}
				rs.close();
				stmt.close();

			} catch (Exception e) {

			}
		} else if (buttonPressed == clearOrder) {
			textArea.setText("Order fulfilled ");
		}
	}

	static void createAndShowGUI() throws ClassNotFoundException, SQLException {
		// Create and set up the window.
		JFrame frame = new JFrame("Bar Orders");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(new barUI());
		frame.pack();
		frame.setVisible(true);
	}

	public static void main(String[] args) throws IOException {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				try {
					createAndShowGUI();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		});
	}

	public static void sqlConnection() throws ClassNotFoundException, SQLException {

		String userName = "BERRYPLASTICS\\srenick";
		String password = "brettanomyces";
		String url = "jdbc:sqlserver://CALLT014\\DBPANGAEA;domain=workgroup;integratedSecurity=true; database=restaurant";
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		conn = DriverManager.getConnection(url, userName, password);

	}
}
