import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.*;

public class seatingUI {

	private int rows = 6;
	private int columns = 8;
	private Icon res = (UIManager.getIcon("OptionPane.errorIcon"));

	public seatingUI() throws ClassNotFoundException, SQLException {
		barUI.sqlConnection();
		JPanel panel = new JPanel(new GridLayout(columns, rows));
		for (int row = 0; row < rows; row++) {
			for (int column = 0; column < columns; column++) {
				final JToggleButton button = new JToggleButton("Table " + row
						+ " seat " + column);
				button.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent actionEvent) {
						AbstractButton abstractButton = (AbstractButton) actionEvent
								.getSource();
						boolean selected = abstractButton.getModel()
								.isSelected();
						if (selected) {
							button.setIcon(res);
							String tableID = button.getText();
							Integer val = 111 + (int) (Math.random() * ((202 - 111) + 1));
							String employeeID = Integer.toString(val);
							DateFormat dateFormat = new SimpleDateFormat(
									"yyyy/MM/dd HH:mm:ss");
							Date d2 = new Date();
							String visitDate = dateFormat.format(d2);
							int saleNumber = 100000111 + (int) (Math.random() * ((900000111 - 100000111) + 1));
							try {
								String sql = "INSERT INTO SEATING (tableID,employeeID,visitDate,saleNumber) VALUES("
										+ "'"
										+ tableID
										+ "'"
										+ ","
										+ "'"
										+ employeeID
										+ "'"
										+ ","
										+ "'"
										+ visitDate
										+ "'"
										+ ","
										+ saleNumber
										+ ");";
								Statement stmt = barUI.conn.createStatement();
								stmt.executeUpdate(sql);
								stmt.close();
							} catch (Exception e) {

							}
						} else {
							button.setIcon(null);
						}
					}
				});
				panel.add(button);
			}
		}
		final JFrame frame = new JFrame("Customer Seating Input Menu");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(panel);
		frame.pack();
		frame.setLocation(150, 150);
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {

			@Override
			public void run() {
				try {
					seatingUI seatingChart = new seatingUI();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		});
	}
}